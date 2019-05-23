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
import FXDevices.FXCanvasDevice;
import java.util.ArrayList;
import map.Map;
import sprites.Base;
import sprites.Bullet;
import sprites.Unit;

/**
 *
 * @author csc190
 */
public class GameEngine implements IGameEngine{
    // -------------- DATA MEBERS ------------------
    protected Map _map;   
    protected ArrayList<Unit> _arrUnits = new ArrayList<Unit>();
    protected ArrayList<Bullet> _arrBullets = new ArrayList<Bullet>();
    protected ArrayList<Picture> _arrPictures = new ArrayList<Picture>();
    protected ICanvasDevice _mainview;
    protected ICanvasDevice _minimap;
    protected ICanvasDevice _factoryPanel;
    protected ISoundDevice _soundDevice;
    protected Team[] _arrTeam = new Team[2];
    protected static GameEngine _instance = null;
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
        Base base0 = new Base(0,0,0);
        Base base1 = new Base(400,400,1);
        Team t0 = new Team(0,10000,base0);
        Team t1 = new Team(1,10000,base1);
        _arrTeam[0] = t0;
        _arrTeam[1] = t1;
        _arrUnits.add(base0);
        _arrUnits.add(base1);
    }
     /**
     * Initialization. maybe used to load game sprites.
     */
    @Override
    public void init(){
        
    }
    
    public static GameEngine getInstance(){
        return _instance;
    }
    
    public Map getMap(){ return _map;}
    
    public void update(){
        
    }
    
    /**
     * Will be expected every tick. (e.g., 30 ticks per second for 30 FPS). Perform operations
     * such as update all sprites and redraw them.
     */
    @Override
    public void onTick(){
        update();
        _mainview.drawViewPort(getInstance());
        for (Unit u: _arrUnits){
            u.drawOnMainView(_mainview);
        }
    }
    
    public void addUnit(Unit unit){}
    
    public void addBullet(Bullet bullet){}
    
    public void addPic(Picture pic){}
    
    public void removeUnit(Unit unit){}
    
    public void removeBullet(Unit bullet){}
    
    public void removePic(Unit pic){}
    
    public void detectRange(int x, int y, int range){}

    
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
