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
import BridgePattern.IGameEngine;
import java.util.ArrayList;
/**
 *
 * @author csc190
 */
public class ButtonController implements IGameEngine{
    Team _team;
    ArrayList<ShopButton> _buttons;
    ICanvasDevice _canvas;
    
    public ButtonController(ICanvasDevice canvas,Team team){
        _canvas = canvas;
        _team = team;
        _buttons = new ArrayList<>();
        init();
    }
    
    public void init(){
        int h = _canvas.getHeight()/3;
         ShopButton infantry = new ShopButton(0, 0, 0, _canvas.getWidth(), h, _team);
         ShopButton tank = new ShopButton(1, 0, h, _canvas.getWidth(), h, _team);
         ShopButton plane = new ShopButton(2, 0, h*2, _canvas.getWidth(), h, _team);
         _buttons.add(infantry);
         _buttons.add(tank);
         _buttons.add(plane);
         for (ShopButton b: _buttons){
             b.draw(_canvas);
         }
    }

    public void onTick(){
        for (ShopButton b: _buttons){
            b.update();
            b.draw(_canvas);
        }
    }

    public void onRightClick(ICanvasDevice canvas, int x, int y){}
    
    public void onLeftClick(ICanvasDevice canvas, int x, int y){
        int h = _canvas.getHeight();
        if (y < h/3) _buttons.get(0).onClick();
        else if (y > h/3*2) _buttons.get(2).onClick();
        else _buttons.get(1).onClick();
    }

    public void onRegionSelected(ICanvasDevice canvas, int x1, int y1, int x2, int y2){}
     
    
}
