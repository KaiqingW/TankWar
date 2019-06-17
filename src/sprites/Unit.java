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
import EvilCraft.GameEngine;
import EvilCraft.Point;
import EvilCraft.Team;
/**
 *
 * @author csc190
 */
public abstract class Unit extends Sprite{

    public void update(){}
    
    /**
     * Draw itself on main view, mostly like pictures
     * @param mainview - canvas device
     */
    public abstract void drawOnMainView(ICanvasDevice mainview);
    
    /**
     * Draw itself on mini map, most likely colored squares
     * @param minimap - canvas device
     */
    public void drawOnMiniMap(ICanvasDevice minimap){
        int mainSize = GameEngine.getInstance().getMainSize();
        int miniSize = minimap.getHeight();
        String color =_team >= 0 ? "#FF0000" : "#0000FF";
        minimap.drawRectangle(_x * miniSize / mainSize, 
                _y * miniSize / mainSize, 
                _size * miniSize / mainSize, 
                _size * miniSize / mainSize,
                color);
    }
    
    public abstract void navigateTo(Point pt);
}
