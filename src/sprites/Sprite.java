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
import EvilCraft.Point;

/**
 *
 * @author csc190
 */
public abstract class Sprite {
    int _x;
    int _y;
    public int _size;
    public int _team;
    
    
    
    public boolean isCollidingWith(int x, int y, int dX, int dY){
        throw new RuntimeException("not implemented yet");
    }
    
    public boolean isCollidingWith(Point p){
        throw new RuntimeException("not implemented yet");
    }
    /**
     * update its own data attributes
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
