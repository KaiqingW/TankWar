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

import java.util.ArrayList;
import sprites.*;

/**
 *
 * @author csc190
 */
public class Team {

    // ---- DATA MEMBERS ---------
    public static String[] PICPATH = {"team_red","team_yellow"};
    public static final int INFANTRY = 0;
    public static final int TANK = 1;
    public static final int PLANE = 2;
    public static final int[] COST = {100,500,1000};
    public ArmyUnit[] _armyUnitModule;
    protected boolean _isHumanPlayer;
    int _id;
    int _genX;
    int _genY;
    protected int _cash;
    protected Base _base;
    protected ArrayList<ArmyUnit> _arrAUnit = new ArrayList<ArmyUnit>();
    
    // ---- OPERATIONS
    
    public Team(int id, int cash, Base base){
        _id = id;
        _cash = cash;
        _base = base;
        _genX = _base.getX() + Base.SIZE;
        _genY = _base.getY() + Base.SIZE;
        ArmyUnit inf = new Infantry(_genX,_genY,_id);
        ArmyUnit tank = new Tank(_genX,_genY,_id);
        ArmyUnit plane = new Plane(_genX,_genY,_id);
        _armyUnitModule = new ArmyUnit[3];
        _armyUnitModule[0] = inf;
        _armyUnitModule[1] = tank;
        _armyUnitModule[2] = plane;
    }
    
    private void vacuumGenPlace(){
        
    }
    
    public void purchase(int s){
        if (_cash >= COST[s]){ 
            _cash -= COST[s];
            Sprite sprite;
            if (s == INFANTRY) sprite = new Infantry()
            }
        }
    
    public void playAsHumen(){ _isHumanPlayer = true;}
    
    
    
}
