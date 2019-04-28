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

import EvilCraftMilestone4.GameEngineMS4;
import EvilCraftMilestone4.Picture;
import EvilCraftMilestone4.Team;
import java.util.ArrayList;

/**
 *
 * @author csc190
 */
public class Tank extends Unit{
    private static final int COOLRATE = 60;
    private static final int LIFE = 300;
    private static final int SHOOTRANGE = 100;
    private static final int SPEED = 5;
    private static final int SIZE = 50;
    
    Picture _gunPic;
    int _rotationSpeed = 1;
    
    public Tank(int x, int y, int size, int heading, Team team){
        super(x,y,SIZE,heading,team);
        _life = LIFE;
        _shootRange = SHOOTRANGE;
        _gunDir = heading;
        _speed = SPEED;
        String bodyPath = "resources/images/" + team.getName() + "/tank/body.png";
        String gunPath = "resources/images/" + team.getName() + "/tank/gun.png";
        _bodyPic = new Picture(bodyPath,_x,_y,_size);
        _bodyPic.setDegree(_heading);
        _gunPic = new Picture(gunPath,_x,_y,_size);
        _gunPic.setDegree(_gunDir);
    }
    
    @Override
    public void update(){
        GameEngineMS4 ge = GameEngineMS4.getInstance();
        if (_isBusy) navigate();
        if (_target != null){
            if (_target.isDead()) {
                _target = null;
            }else gunRotation();
        } else detect(ge._arrUnits);
        _bodyPic.update(_x, _y, _heading);
        _gunPic.update(_x, _y, _gunDir);
        if (_coolDown != 0) _coolDown --;
    }
     /**
     * Draw itself on main view, mostly like pictures
     * @param mainview - canvas device
     */
    @Override
    public ArrayList<Picture> getMainPictures(){
        ArrayList<Picture> ans = new ArrayList<Picture>();
        ans.add(_bodyPic);
        ans.add(_gunPic);
        return ans;
    }
    
    public void gunRotation(){
        int diff = _gunDir - getDegree(_target._x, _target._y);
        if (Math.abs(diff) < _rotationSpeed){
            if (_coolDown == 0) fire();
        } else if ((diff <= 180 && diff > 0) || (diff > -360 && diff <= -180)){ 
            _gunDir += _rotationSpeed;
        }
        else if ((diff < 360 && diff > 180) || (diff > -180 && diff < 0)){
            _gunDir -= _rotationSpeed;
        }
    }
        
    private int getDegree(int x, int y){
        int degree = (int)Math.toDegrees(Math.atan2(x-_x,y-_y));
        return degree;
    }
    
    public void fire(){
        Bullet bullet = new Shell(_x, _y, _gunDir,_target._x, _target._y, _team);
        GameEngineMS4 ge = GameEngineMS4.getInstance();
        ge.addBullet(bullet);
        ge.addMiniPic(bullet.getMiniPictures());
        for (Picture p: bullet.getMainPictures()){
            ge.addPic(p);
        }
        _coolDown = COOLRATE;
    }
}
