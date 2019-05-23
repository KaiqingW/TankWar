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
    protected ArrayList<Bullet> _arrBullet = new ArrayList<Bullet>();
    
    // ---- OPERATIONS
    
    public Team(int id, int cash, Base base){
        _id = id;
        _cash = cash;
        _base = base;
        _genX = _base.getX() + Base.SIZE;
        _genY = _base.getY() + Base.SIZE;
    }
    
    private void vacuumGenPlace(int size){
        for (ArmyUnit aU: _arrAUnit){
            if (!(aU instanceof Plane) && aU.isCollidingWith(_genX,_genY,size)){
                aU.moveOutCurrentPos();
            }
        }
    }
    
    public void purchase(int s){
        if (_cash >= COST[s]){ 
            _cash -= COST[s];
            generate(s);
        }
    }
    
    private void generate(int s){
        ArmyUnit aU;
        switch(s){
            case INFANTRY: aU = new Infantry(_genX,_genY,_id);
                    break;
            case TANK: aU = new Tank(_genX,_genY,_id);
                    break;
            case PLANE: aU = new Plane(_genX,_genY,_id);
                    break;
            default: throw new RuntimeException("No Army Unit type exist!");
        }
        _arrAUnit.add(aU);
    }
    
    public void playAsHumen(){ _isHumanPlayer = true;}
    
    public int getID(){return _id;}
    
    public int getCash(){return _cash;}
    
}
