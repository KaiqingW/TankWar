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

import EvilCraftMilestone4.Picture;
import EvilCraftMilestone4.Team;

/**
 *
 * @author csc190
 */
public class InfBullet extends Bullet{
    public InfBullet(int x, int y, int heading, int destX, int destY, Team team){
        super(x, y, 10, heading, destX, destY, team);
        _damageRange = 25;
        _damage = 20;
        _speed = 15;
        String picPath = "resources/images/" + team.getName() + "/infantry/bullet.png";
        _bodyPic = new Picture(picPath,_x,_y,_size);
    }
}
