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
public class Bullet extends Sprite{
    int _damage;
    int _damageRange;
    private int _damageCount = 1;
    
    public Bullet(int x, int y, int size,int destX, int destY,Team team){
        super(x,y,size,team);
        _destX = destX;
        _destY = destY;
    }
    
    @Override
    public void update(){
        int distance = (int)(Math.sqrt(Math.pow(_destX-_x,2) + Math.pow(_destY-_y,2)));
        int mX = _speed * (_destX-_x)/distance;
        int mY = _speed * (_destY-_y)/distance;
        _x += mX;
        _y += mY;
    }
    
    @Override
    public ArrayList<Picture> getMainPictures(){
        ArrayList<Picture> myList = new ArrayList<>();
        myList.add(_bodyPic);
        return myList;
    }

    @Override
    public boolean isDead(){
        return _x == _destX && _y == _destY;
    }
}
