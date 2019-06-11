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
package EvilCraft;

import BridgePattern.ICanvasDevice;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author csc190
 */
public class ShopButton {
    public static final String ROOTPATH = "resources/images/common/button/";
    public static final String[] PICPATH = {"infantry.png","tank.png","plane.png"};
    public static final int COOLDOWN = 50;
    int _unit;
    String _picPath;
    int _x;
    int _y;
    int _h;
    int _w;
    Team _team;
    int _cooldown = 0;
    
    
    public ShopButton(int type, int x, int y, int w, int h, Team t){
        _unit = type;
        _x = x;
        _y = y;
        _w = w;
        _h = h;
        _team = t;
        _picPath = ROOTPATH + PICPATH[_unit];
    }
    
    public void update(){
        if (_cooldown <= 0) return;
        _cooldown--;
    }
    
    public void draw(ICanvasDevice canvas){
        canvas.drawImg(_picPath, _x, _y, _w, _h, 0);
    }
    
    public void onClick(){
        if (_cooldown > 0) {
            System.out.println("not ready!");
            return;
        }
        _team.purchase(_unit);
        _cooldown = COOLDOWN;
    }
    
    
}
