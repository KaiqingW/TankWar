/*
 * Copyright (C) 2019 h701819588
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

import EvilCraft.Picture;
import EvilCraft.Team;
import java.util.ArrayList;

/**
 *
 * @author h701819588
 */
public abstract class ConstUnit extends Unit{
    
    public ConstUnit(int x, int y, int size, int heading, Team team){
        super(x,y,size,heading,team);
    }
        /**
     * Draw itself on main view, mostly like pictures
     * @param mainview - canvas device
     */
    
        @Override
    public abstract void update();
    
    @Override
    public abstract ArrayList<String> getMainPictures();
    
    public String isTargeted(){
        return "" + _x + "," + _y + "," + isDead();
    }
    
    public void getDamage(int damage){ _life -= damage;}
}
