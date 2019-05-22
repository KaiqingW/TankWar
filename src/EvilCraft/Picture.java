package EvilCraft;

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


import EvilCraftMilestone3.*;

/**
 *
 * @author csc190
 */
public class Picture {

    //* data members *
    protected String _path;
    protected int _x, _y;
    protected int _size;
    protected int _degree;

    //*********** operations ***************
    public Picture(String filepath, int x, int y, int size) {
        _path = filepath;
        _x = x;
        _y = y;
        _size = size;
    }
    
    public int getX() { return _x;}
    
    public int getY() { return _y;}
    
    public int getSize() { return _size;}
    
    public String getPath() { return _path;}

    public int getDegree() { return _degree;}
    
    public void setDegree(int d){ _degree = d;}
    
    public void setPath(String path){ _path = path;}
    
    public void setX(int x){ _x = x;}
    
    public void setY(int y){ _y = y;}

}
