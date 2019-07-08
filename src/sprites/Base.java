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
public class Base extends ConstUnit{
    private static final int LIFE = 1000;
    private static final int SIZE = 100;
    
    public Base(int x, int y, int size, int heading, Team team){
        super(x,y,size,heading,team);
    }
    
    public void update(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<String> getMainPictures(){
        ArrayList<String> myList = new ArrayList<>();
        myList.add(_bodyPic);
        return myList;
    }

    @Override
    public void fire(int targetX, int targetY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
