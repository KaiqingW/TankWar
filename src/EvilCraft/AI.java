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
import sprites.*;
/**
 *
 * @author csc190
 */
public class AI {
    static int PlaneAmt = 2;
    static int TankAmt = 6;
    Team _team;
    Point _targetPoint;
    Unit _target;
    public ShopButton[] _arrShopB;
    int _tick = 0;

    public AI(Team t){
        _team = t;
        _arrShopB = new ShopButton[3];
        for (int i=0;i<_arrShopB.length;i++) 
            _arrShopB[i] = new ShopButton(_team, i);
    }
    
    public void setTarget(Unit u){ _target = u;}
    
    void update(){
        
        if (_tick % 50 == 0) {
            if (_team._arrUnitAmt[1] < TankAmt) _arrShopB[1].onClick();
            if (_team._arrUnitAmt[2] < PlaneAmt) _arrShopB[2].onClick();
            _arrShopB[0].onClick();
        }
        _tick ++;
        
        if (_target != null){
            for (ArmyUnit aU: _team._armyUnitModule){
                aU.setTargetTo(_target);
            }
        }
        
    }
}
