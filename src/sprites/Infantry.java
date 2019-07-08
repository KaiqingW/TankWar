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

import EvilCraft.GameEngine;
import EvilCraft.Team;
import java.util.ArrayList;

/**
 *
 * @author csc190
 */
public class Infantry extends ArmyUnit{
    private static final int COOLRATE = 15;
    private static final int LIFE = 20;
    private static final int SHOOTRANGE = 50;
    private static final int SPEED = 2;
    private static final int SIZE = 50;
    
    public Infantry(int x, int y, int size, int heading, Team team){
        super(x,y,SIZE,heading,team);
        _life = LIFE;
        _shootRange = SHOOTRANGE;
        _gunDir = heading;
        _speed = SPEED;
        _bodyPic = "resources/images/" + team.getName() + "/infantry/body.png";
    }
    
    @Override
    public void update(){
        GameEngine ge = GameEngine.getInstance();
        if (_target != null) navigate();
        if (_tempTarget != null){
            if (Boolean.parseBoolean(_tempTarget.split(",")[2])) {
                _tempTarget = null;
            }else gunRotation();
        } else _tempTarget = ge.isEnemyShow(this);
        if (_coolDown != 0) _coolDown --;
    }
     /**
     * Draw itself on main view, mostly like pictures
     * @param mainview - canvas device
     */
    @Override
    public ArrayList<String> getMainPictures(){
        ArrayList<String> ans = new ArrayList<String>();
        ans.add(_bodyPic);
        return ans;
    }
    
    public void gunRotation(){
        int targetX = Integer.parseInt(_target.split(",")[0]);
        int targetY = Integer.parseInt(_target.split(",")[1]);
        if (_coolDown == 0) fire(targetX,targetY);
    }
        
    private int getDegree(int x, int y){
        int degree = (int)Math.toDegrees(Math.atan2(x-_x,y-_y));
        return degree;
    }
    
    public void fire(int targetX, int targetY){
        Bullet bullet = new InfBullet(_x, _y, _heading,targetX, targetY, _team);
        GameEngine ge = GameEngine.getInstance();
        ge.addBullet(bullet);
        _coolDown = COOLRATE;
    }
}
