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
package EvilCraft;

import BridgePattern.ICanvasDevice;
import BridgePattern.IGameEngine;
import BridgePattern.ISoundDevice;
import java.util.ArrayList;
import map.Map;
import sprites.Bullet;
import sprites.Unit;

/**
 *
 * @author csc190
 */
public class GameEngine implements IGameEngine{
    // -------------- DATA MEBERS ------------------
    protected Map _map;
    protected ArrayList<Unit> _arrUnits;
    protected ArrayList<Bullet> _arrBullets;
    protected ArrayList<Unit> _arrSelected;
    protected ArrayList<Picture> _arrPictures;
    protected ICanvasDevice _mainview;
    protected ICanvasDevice _minimap;
    protected ICanvasDevice _factoryPanel;
    protected ISoundDevice _soundDevice;
    protected Team[] _arrTeam;
    protected static GameEngine _instance = null;
    protected static int _miniSize;
    protected static int _mainSize;
    //---------------- OPERATIONS ------------------
    /**
     * Constructor.
     * An evil craft game engine has 3 canvases: main view, mini map and a panel for manufacturing units
     * @param map
     * @param mainview
     * @param minimap
     * @param factoryPanel
     * @param sound 
     */
    
    //constructor
    public GameEngine(String mapPath,
            ICanvasDevice mainview,
            ICanvasDevice minimap,
            ICanvasDevice factoryPanel,
            ISoundDevice soundDevice){
        _map = new Map(mapPath);
        _mainview = mainview;
        _minimap = minimap;
        _factoryPanel = factoryPanel;
        _soundDevice = soundDevice;
        _instance = this;
        //_miniSize = _minimap.getHeight();
        _mainSize = _mainview.getHeight();
    }
       /**
     * Initialization. maybe used to load game sprites.
     */
    
    public static GameEngine getInstance(){
        return _instance;
    }
    
    //public static int getMiniSize(){ return _miniSize;}
    
    public static int getMainSize(){ return _mainSize;}
    
    private void initGame(){
        
    }
    @Override
    public void init(){
        
    }
    
    /**
     * Will be expected every tick. (e.g., 30 ticks per second for 30 FPS). Perform operations
     * such as update all sprites and redraw them.
     */
    @Override
    public void onTick(){

    }
    
    public void addUnit(Unit unit){}
    
    public void addBullet(Bullet bullet){}
    
    public void addPic(Picture pic){}
    
    public void removeUnit(Unit unit){}
    
    public void removeBullet(Unit bullet){}
    
    public void removePic(Unit pic){}
    
    public void detectRange(int x, int y, int range){}
    
    public void damage(int damage){}
    
    /**
     * Handles the mouse right button click. This operation may be substituted by finger ops on mobile devices.
     * Note that game engine needs to keep track of canvas size and perform translation of coordinates (relative to map)
     * @param canvas - the canvas device which raises the event
     * @param x - x coordinate of the event IN the canvas device
     * @param y - y coordinate of the event IN the canvas device
     */
    @Override
    public void onRightClick(ICanvasDevice canvas, int x, int y){
        Point pt = getGlobalCoordinate(canvas, x, y);
        for (Unit u: _arrSelected){
            u.navigateTo(pt);
        }
    }
    
    
    /**
     * Handles the mouse left button click. This operation may be substituted by finger ops on mobile devices.
     * Note that game engine needs to keep track of canvas size and perform translation of coordinates (relative to map)
     * @param canvas - the canvas device which raises the event
     * @param x - x coordinate of the event IN the canvas device
     * @param y - y coordinate of the event IN the canvas device
     */
    @Override
    public void onLeftClick(ICanvasDevice canvas, int x, int y){
        _arrSelected = null;
        if(canvas == _minimap){
            Point pt = getGlobalCoordinate(canvas, x, y);
            _mainview.setViewPort(pt._x-_mainview.getWidth()/2, pt._y-_mainview.getHeight()/2);
        }else if (canvas == _mainview) 
            _arrSelected = getArrUnits(new Point(x-25,y-25),new Point(x+25,y+25),0);
    }
    
    /**
     * Handles the mouse drag and then release event. This operation may be substituted by finger ops on mobile devices.
     * Note that game engine needs to keep track of canvas size and perform translation of coordinates (relative to map)
     * @param canvas - the canvas device which raises the event
     * @param x1 - x coordinate of the DRAG_START event IN the canvas device. 
     * @param y1 - y coordinate of the DRAG_START event IN the canvas device
     * @param x2 - x coordinate of the DRAG_END event IN the canvas device. 
     * @param y2 - y coordinate of the DRAG_END event IN the canvas device
     */
    @Override
    public void onRegionSelected(ICanvasDevice canvas, int x1, int y1, int x2, int y2){
        Point pt1 = getGlobalCoordinate(canvas, x1, y1);
        Point pt2 = getGlobalCoordinate(canvas, x2, y2);
        _arrSelected = getArrUnits(pt1, pt2, 0);
    }
    
    @Override
    public void mouseMove(ICanvasDevice canvas, int x, int y){
        
    }
    
    public ArrayList<Unit> getArrUnits(Point pt1, Point pt2, int team){
         //slow version
        ArrayList<Unit> inRange = new ArrayList<Unit>();
        for(Unit u: _arrUnits){

            if(u._team == -1 || u._team == team){
                int x = pt1._x - u._size;
                int y = pt1._y - u._size;
                int dX = pt2._x - x - u._size;
                int dY = pt2._y - y - u._size;
                if (u.isCollidingWith(x,y,dX,dY)){
                    inRange.add(u);
                }
            } 
        }
        return inRange;
    }
    
    public Point getGlobalCoordinate(ICanvasDevice canvas, int x, int y){
        int cw = canvas.getWidth();
        int ch = canvas.getHeight();
        int mw = _map.getNumCols() * 100;
        int mh = _map.getNumRows() * 100;
        int x2 = canvas == _mainview? canvas.viewX() + x: canvas.viewX() + x * mw/cw;
        int y2 = canvas == _mainview? canvas.viewY() + y: canvas.viewY() + y * mh/ch;
        return new Point(x2,y2); 
    }
    
    public Point getNewLeftTopCoordinates(Point center, ICanvasDevice mainview){
        Point nl = new Point(center._x- mainview.getWidth()/2, center._y - mainview.getHeight()/2);
        return nl;
    }
}
