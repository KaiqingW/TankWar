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
import EvilCraft.Picture;
import EvilCraft.Team;
import FXDevices.FXCanvasDevice;

/**
 *
 * @author csc190
 */
public class Infantry extends ArmyUnit{
    //------- DATA MEMBERS ----------
    public static final int SIZE = 10;
    protected Picture _bodyPic;
    protected Picture _gunPic;
    //------- OPERATIONS -------------
    
    public Infantry(int x, int y, int team){
        super(x,y,SIZE,team);
        String bodyPath = "resources/images/" + Team.PICPATH[_team] + "/infantry/body.jpeg";
        _bodyPic = new Picture(bodyPath, _x, _y, _size);
    }
    public Infantry(Sprite other){
        super(other);
        if (other instanceof Infantry) {
            _bodyPic = ((Infantry)other)._bodyPic;
        }else throw new RuntimeException("Infantry can not copy other object!");
    }
    /**
     * update status of sprite as time goes
     */
    public void update(){
        throw new UnsupportedOperationException("Not supported yet");
    }
    
    /**
     * Draw itself on main view, mostly like pictures
     * @param mainview - canvas device
     */
    public void drawOnMainView(ICanvasDevice mainview){
        ((FXCanvasDevice)mainview).drawImg(_bodyPic);
    }
    
    /**
     * Draw itself on mini map, most likely colored squares
     * @param minimap - canvas device
     */
    public void drawOnMiniMap(ICanvasDevice minimap){
        throw new UnsupportedOperationException("Not supported yet");
    }    
}
