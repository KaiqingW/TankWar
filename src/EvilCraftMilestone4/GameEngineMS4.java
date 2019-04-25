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
package EvilCraftMilestone4;

import BridgePattern.*;
import sprites.*;
import java.util.ArrayList;
/**
 *
 * @author csc190
 */
public class GameEngineMS4 implements IGameEngine{
    protected Map _map;
    public ArrayList<Unit> _arrUnits;
    protected ArrayList<Bullet> _arrBullets;
    protected ArrayList<Picture> _arrPictures;
    protected ICanvasDevice _mainview;
    protected ICanvasDevice _minimap;
    protected ICanvasDevice _factoryPanel;
    protected ISoundDevice _soundDevice;
    protected Team[] _arrTeam;
    protected static GameEngineMS4 inst;
    
    //constructor
    public GameEngineMS4(String mapPath,
            ICanvasDevice mainview,
            ICanvasDevice minimap,
            ICanvasDevice factoryPanel,
            ISoundDevice soundDevice){
        _map = new Map(mapPath);
        _mainview = mainview;
        _minimap = minimap;
        _factoryPanel = factoryPanel;
        _soundDevice = soundDevice;
        inst = this;
    }
       /**
     * Initialization. maybe used to load game sprites.
     */
    private void initGame(){
        
    }
    @Override
    public void init(){
        
    }
    
    public static GameEngineMS4 getInstance(){ return inst;}
    
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
