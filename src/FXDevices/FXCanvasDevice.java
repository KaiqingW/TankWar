/*
 * Copyright (C) 2019 csc190
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package FXDevices;

import BridgePattern.ICanvasDevice;
import BridgePattern.IGameEngine;
import BridgePattern.IStopWatch;
import EvilCraft.GameEngine;
import EvilCraft.Picture;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Scanner;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import map.Map;

/**
 * FXVersion Implementation of ICanvasDevice
 *
 * @author csc190
 */
public class FXCanvasDevice implements ICanvasDevice {

    //--------------------------------------
    //data members
    //--------------------------------------
    protected Canvas _canvas;
    protected GraphicsContext _gc;
    int _xPress, _xRelease, _yPress, _yRelease;
    boolean _rightClick = false;
    protected Hashtable<String, Image> _pics = new Hashtable();
    protected long nPixsDrawn = 0;
    protected int _viewportX=0, _viewportY = 0;

    //--------------------------------------
    //methods
    //--------------------------------------
    public FXCanvasDevice(Canvas canvas) {
        _canvas = canvas;
        _gc = _canvas.getGraphicsContext2D();
        canvas.setCache(false);
        //canvas.setCacheHint(CacheHint.SPEED);
    }
    
    private Image getImage(String picpath) {
        if (!_pics.containsKey(picpath)) {
            //Somehow had to chop off the "resources part"
            String path2 = picpath.substring(picpath.indexOf("/") + 1);
            InputStream is = getClass().getClassLoader().getResourceAsStream(path2);

            Image img = new Image(is);
            _pics.put(picpath, img);
        }
        return _pics.get(picpath);
    }

    public void drawImg(Picture pic){
        drawImg(pic.getPath(),pic.getX(),pic.getY(),pic.getSize(),pic.getSize(),pic.getDegree());
    }
    
    @Override
    public void drawImg(String imgPath, int x, int y, int width, int height, int degree) {
        x -= _viewportX;
        y -= _viewportY;
        //1. SPEED IT UP. If not in view port, skip it
        if( (x<=-100 || x>this.getWidth()+100 || y<-100 || y>this.getHeight()+100) ||
                (x+100<-1000 || x+100>this.getWidth()+100 || y+100<-100 || y+100>this.getHeight()+100) )  {
            return; //don't draw it
        }
        //1. calculate the actual pixels to be drawn in the view port (due to clipping)
        int minX = Integer.max(x, 0);
        int maxX = Integer.min(x+width, this.getWidth());
        int minY = Integer.max(y, 0);
        int maxY = Integer.min(y+height, this.getHeight());
        int xDiff = maxX - minX;
        int yDiff = maxY - minY;
        if(xDiff<0 || yDiff<0) {
            int bp = 1; //should exception
        }
        this.nPixsDrawn += xDiff*yDiff;
        
        
        //2. Real drawing
        Image img = getImage(imgPath);
        if (degree > 0) {
            _gc.save();
            Rotate r = new Rotate(degree, x + width / 2, y + height / 2);
            _gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
            _gc.drawImage(img, x, y, width, height);
            _gc.restore();
        } else {
            _gc.drawImage(img, x, y, width, height);
        }

    }

    @Override
    public int getWidth() {
        return (int) _canvas.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) _canvas.getHeight();
    }

    @Override
    public IStopWatch createStopWatch(String name) {
        FXStopWatch watch = new FXStopWatch(name);
        return watch;
    }

    @Override
    public void setupEventHandler(IGameEngine gameEngine) {
        ICanvasDevice me = this;
        
        //2. set up mouse drag event
        _canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                _xPress = (int) event.getX();
                _yPress = (int) event.getY();
                _rightClick = event.isSecondaryButtonDown();
            }
        });

        _canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                _xRelease = (int) event.getX();
                _yRelease = (int) event.getY();
                if (_xPress != _xRelease || _yPress != _yRelease) {
                    gameEngine.onRegionSelected(me, _xPress, _yPress, _xRelease, _yRelease);
                }else{
                    if(_rightClick){
                        gameEngine.onRightClick(me, _xPress, _yPress);
                    }else{
                        gameEngine.onLeftClick(me, _xPress, _yPress);
                    }
                }
            }
        });
    }

    @Override
    public void clear() {
        _gc.clearRect(0, 0, _canvas.getWidth(), _canvas.getHeight());
    }

    @Override
    public long getTotalPixsDraw() {
        return this.nPixsDrawn;
    }

    @Override
    public void setViewPort(int x, int y) {
        _viewportX = x;
        _viewportY = y;
    }
    
    @Override
    public void drawViewPort(IGameEngine gE){
        System.out.println(_viewportX +" "+ _viewportY);
        Map m = ((GameEngine)gE).getMap();
        int height = getHeight()/Map.UNITSIZE + 2;
        int width = getWidth()/Map.UNITSIZE + 2;
        int dY = _viewportY / Map.UNITSIZE;
        int dX = _viewportX / Map.UNITSIZE;
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                int y = i + dY;
                int x = j + dX;
                drawImg(m.getMap()[y][x].getPic(), 
                        x * Map.UNITSIZE, 
                        y * Map.UNITSIZE, 
                        Map.UNITSIZE, 
                        Map.UNITSIZE, 0);
            }
        }
    }
    /**
     * Draw the given message with the font size
     * @param msg
     * @param x
     * @param y
     * @param fontsize 
     */
    
    @Override
    public void drawText(String msg, int x, int y, int fontsize){
        _gc.setFont(new Font(fontsize));
        _gc.strokeText(msg, x, y);
    }
    
    /**
     * Draw a line from (x1,y1) to (x2,y2)
     * @param x1
     * @param y1
     * @param x2
     * @param y2 
     */
    @Override
    public  void drawLine(int x1, int y1, int x2, int y2){
        _gc.strokeLine(x1, y1, x2, y2);
    }
}
