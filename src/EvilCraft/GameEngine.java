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

import javafx.scene.image.Image;
import BridgePattern.*;
import map.Map;
import sprites.*;
import java.util.ArrayList;
/**
 *
 * @author csc190
 */
public class GameEngine implements IGameEngine{
    protected int _viewportX = 0, _viewportY = 0;
    protected Map _map;
    protected Image _minimap;
    public ArrayList<Unit> _arrUnits;
    protected ArrayList<Bullet> _arrBullets;
    protected ArrayList<Picture> _arrPictures;
    protected ArrayList<Picture> _arrMiniPics;
    protected ICanvasDevice _mainview;
    protected ICanvasDevice _miniview;
    protected ICanvasDevice _factoryPanel;
    protected ISoundDevice _soundDevice;
    protected Team[] _arrTeam;
    protected static GameEngine _inst;
    
    //constructor
    public GameEngine(String mapPath,
                          ICanvasDevice mainview,
                          ICanvasDevice minimap,
                          ICanvasDevice factoryPanel,
                          ISoundDevice soundDevice)
    {
        _map = new Map(mapPath);
        _mainview = mainview;
        _miniview = minimap;
        _factoryPanel = factoryPanel;
        _soundDevice = soundDevice;
        _inst = this;
    }
       /**
     * Initialization. maybe used to load game sprites.
     */
    private void initGame(){
        _map.init(_mainview);
    }
    
    @Override
    public void init(){
        initGame();
    }
    
    public void update(){
        _mainview.clear();
        _miniview.clear(_minimap);
        drawMap();
        
        updateAllSprites();
        drawAllPics();
    }
    
    public String isEnemyShow(Unit unit){
        for (Unit u: _arrUnits){
            if (unit.isEnemy(u.getTeam())){
                int rangeX = unit.getX() - unit.getShootRange()/2 - unit.getSize()/2;
                int rangeY = unit.getY() - unit.getShootRange()/2 - unit.getSize()/2;
                
                if (Sprite.oneDimensionOverlap(rangeX, unit.getShootRange(), u.getX(), u.getSize()) && 
                Sprite.oneDimensionOverlap(rangeY, unit.getShootRange(), u.getY(), u.getSize())){
                    return u.isTargeted();
                }   
            }
        }
        return null;
    }
    
    public void updateAllSprites(){
        for (Unit u: _arrUnits) {
            u.update();
            if (u.isDead()){
                removeUnit(u);
                for (Picture p: u.getMainPictures())
                    removePic(p);
                removeMiniPic(u.getMiniPictures());
            }
        }
        for (Bullet b: _arrBullets) {
            b.update();
            if (b.isDead()){
                damage(b);
                for (Picture p: b.getMainPictures())
                    removePic(p);
                removeMiniPic(b.getMiniPictures());
            }
        }
    }
    public void drawMap(){
        
    }
    
    public void drawAllPics(){
        for(Picture pic: _arrPictures){
            _mainview.drawImg(pic.getImg(), pic.getX()-_viewportX, pic.getY()-_viewportY,
                    pic.getSize(), pic.getSize(), pic.getDegree());
        }
        for(Picture pic: _arrMiniPics){
            _miniview.drawImg(pic.getImg(), pic.getX()-_viewportX, pic.getY()-_viewportY,
                    pic.getSize(), pic.getSize(), pic.getDegree());
        }
    }
    
    public static GameEngine getInstance(){ return _inst;}
    
    /**
     * Will be expected every tick. (e.g., 30 ticks per second for 30 FPS). Perform operations
     * such as update all sprites and redraw them.
     */
    @Override
    public void onTick(){

    }
    
    public void addUnit(Unit unit){ _arrUnits.add(unit);}
    
    public void addBullet(Bullet bullet){ _arrBullets.add(bullet);}
    
    public void addPic(Picture pic){ _arrPictures.add(pic);}
    
    public void addMiniPic(Picture pic){ _arrMiniPics.add(pic);}
    
    public void removeUnit(Unit unit){ _arrUnits.remove(unit);}
    
    public void removeBullet(Unit bullet){ _arrBullets.remove(bullet);}
    
    public void removePic(Picture pic){ _arrPictures.remove(pic);}
    
    public void removeMiniPic(Picture pic){ _arrMiniPics.remove(pic);}
    
    
    public void damage(Bullet bullet){
        for (Unit u: _arrUnits){
            if (bullet.isEnemy(u.getTeam()) && 
                    Sprite.oneDimensionOverlap(bullet.getX(),bullet.getSize(),u.getX(),u.getSize()) && 
                    Sprite.oneDimensionOverlap(bullet.getY(),bullet.getSize(),u.getY(),u.getSize())){
                u.getDamage(bullet._damage);
            }
        }
    }
    
    /**
     * Handles the mouse right button click. This operation may be substituted by finger ops on mobile devices.
     * Note that game engine needs to keep track of canvas size and perform translation of coordinates (relative to map)
     * @param canvas - the canvas device which raises the event
     * @param x - x coordinate of the event IN the canvas device
     * @param y - y coordinate of the event IN the canvas device
     */
    @Override
    public void onRightClick(ICanvasDevice canvas, int x, int y){
    
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
    }
    
    
    
    
}
