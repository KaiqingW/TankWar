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
    public int _x;
    public int _y;
    public int _size;
    int _heading;
    int _destX;
    int _destY;
    int _speed;
    Team _team;
    Picture _bodyPic;
    Picture _miniPic;
    
    public Sprite(int x,int y,int size, int heading, Team team){
        _x = x;
        _y = y;
        _size = size;
        _heading = heading;
        _team = team;
        String miniPath = "resources/images/" + team.getName() + "mini.png";
        _miniPic = new Picture(miniPath,_x,_y,1);
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
 
    public abstract ArrayList<Picture> getMainPictures();
    
    public Picture getMiniPictures(){
        return _miniPic;
    }
    
    public abstract boolean isDead();
}
