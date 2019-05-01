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
package sprites;

import EvilCraftMilestone4.GameEngineMS4;
import EvilCraftMilestone4.Picture;
import EvilCraftMilestone4.Team;
import java.util.ArrayList;

/**
 *
 * @author csc190
 */
public abstract class Unit extends Sprite{
    int _life;
    int _shootRange;
    int _gunDir;
    int _coolDown;
    String _target;
    String _tempTarget;
    
    // constructor
    public Unit(int x, int y, int size, int heading, Team team){
        super(x,y,size,heading,team);
    }
    
    public int getShootRange(){ return _shootRange;}
    
    @Override
    public abstract void update();
    
    public abstract void fire(int targetX, int targetY);
    /**
     * Draw itself on main view, mostly like pictures
     * @param mainview - canvas device
     */
    @Override
    public abstract ArrayList<Picture> getMainPictures();
        
    @Override
    public boolean isDead(){
        return _life <= 0;
    }
    
    public void navigate(){}
    
    
    public String isTargeted(){
        return "" + _x + "," + _y + "," + isDead();
    }
    
    public boolean havingTarget(){ return _target != null;}
    public boolean havingTempTarget(){
        return _tempTarget != null;
    }
    
    public boolean inShootRange(Unit other){
        int rangeX = _x - _shootRange/2 - _size/2;
        int rangeY = _y - _shootRange/2 - _size/2;
        return  super.oneDimensionOverlap(rangeX, _shootRange, other._x, other._size) && 
                super.oneDimensionOverlap(rangeY, _shootRange, other._y, other._size);
    }
    
    public void getDamage(int damage){ _life -= damage;}

    public void setTempTarget(String enemy){
        _tempTarget = enemy;
    }
             
    public void setTargetTo(String enemy){
        _target = enemy;
    }
}
