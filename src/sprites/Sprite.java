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

import EvilCraft.Team;
import EvilCraft.Picture;
import BridgePattern.ICanvasDevice;
import EvilCraftMilestone4.*;
import java.util.ArrayList;

/**
 *
 * @author csc190
 */
public abstract class Sprite {
     /**
     * update its own data attributes
     */
    int _x;
    int _y;
    int _size;
    int _heading;
    int _destX;
    int _destY;
    int _speed;
    Team _team;
    String _bodyPic;
    
    public Sprite(int x,int y,int size, int heading, Team team){
        _x = x;
        _y = y;
        _size = size;
        _heading = heading;
        _team = team;
    }
    
    public int getX(){ return _x;}
    public int getY(){ return _y;}
    public int getSize(){ return _size;}
    public Team getTeam(){ return _team;}
    
    public boolean isEnemy(Team team) {
        return _team.compareTo(team) != 0;
    }
    
    public static boolean oneDimensionOverlap(int x1, int length1, int x2, int length2){
        return (x1 >= x2 && x1 <= x2 + length2) ||
               (x1 + length1 <= x2 + length2 && x1 + length1 >= x2) ||
               (x2 >= x1 && x2 <= x1 + length1);
    }
    
    /**
     * to tell if two sprites collide with each other
     * @param other - cannt be null
     * @return 
     */
    public boolean isCollidingWith(Sprite other){
        return oneDimensionOverlap(_x, _size, other._x, other._size) &&
                oneDimensionOverlap(_y, _size, other._y, other._size);
    }
    
    public abstract void update();
    
    /**
     * Draw itself on main view, mostly like pictures
     * @param mainview - canvas device
     */
 
    public abstract ArrayList<String> getMainPictures();
    
    public abstract boolean isDead();
}
