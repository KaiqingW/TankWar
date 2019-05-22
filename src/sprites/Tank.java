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

/**
 *
 * @author csc190
 */
public class Tank extends ArmyUnit{
    //------- DATA MEMBERS ----------
    protected Picture _bodyPic;
    protected Picture _gunPic;
    //------- OPERATIONS -------------
    
    public Tank(int x, int y, int size, int team){
        super(x,y,size,team);
        String bodyPath = "resources/images/" + Team.PICPATH[_team] + "/tank/body.png";
        String gunPath = "resources/images/" + Team.PICPATH[_team] + "/tank/gun.png";
        _bodyPic = new Picture(bodyPath, x, y, size);
        _gunPic = new Picture(gunPath, x, y, size);
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
        mainview.drawImg(_bodyPic);
        mainview.drawImg(_gunPic);
    }
    
    /**
     * Draw itself on mini map, most likely colored squares
     * @param minimap - canvas device
     */
    public void drawOnMiniMap(ICanvasDevice minimap){
        throw new UnsupportedOperationException("Not supported yet");
    }
}
