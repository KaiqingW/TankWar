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

import BridgePattern.ICanvasDevice;
import EvilCraft.Team;

/**
 *
 * @author csc190
 */
public abstract class Sprite {
     //------- DATA MEMBERS ----------
    protected int _x, _y, _size;
    protected int _team;
    protected boolean _dead = false;
    //------- OPERATIONS -------------
    
     /**
     * Constructor
     * @param x - coordinate
     * @param y - coordinate
     * @param size
     * @param team
     */
    public Sprite(int x, int y, int size, int team){
        _x = x;
        _y = y;
        _size = size;
        _team = team;
    }
    
     /**
     * tell if a sprite is dead
     */
    public boolean isDead(){ return _dead;}
    
    /**
     * to tell if two lines are overlap with each other
     * @param x1 - 1st line start 
     * @param length1 - 1st line length
     * @param x2 - 2nd line start
     * @param length2 - 2nd line length
     */
    public boolean oneDimensionOverlap(int x1, int length1, int x2, int length2){
        return (x1 >= x2 && x1 <= x2 + length2) ||
               (x1 + length1 <= x2 + length2 && x1 + length1 >= x2) ||
               (x2 >= x1 && x2 <= x1 + length1);
    }
    
    /**
     * to tell if two sprites collide with each other
     * @param other - can not be null
     * @return 
     */
    public boolean isCollidingWith(Sprite other){
        return oneDimensionOverlap(_x, _size, other._x, other._size) &&
                oneDimensionOverlap(_y, _size, other._y, other._size);
    }
    
     /**
     * update status of sprite as time goes
     */
    public abstract void update();
    
    /**
     * Draw itself on main view, mostly like pictures
     * @param mainview - canvas device
     */
    public abstract void drawOnMainView(ICanvasDevice mainview);
    
    /**
     * Draw itself on mini map, most likely colored squares
     * @param minimap - canvas device
     */
    public abstract void drawOnMiniMap(ICanvasDevice minimap);
}
