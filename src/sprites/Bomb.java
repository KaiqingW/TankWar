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
import EvilCraft.Team;
import EvilCraft.Picture;
import EvilCraftMilestone4.*;

/**
 *
 * @author csc190
 */
public class Bomb extends Bullet{
    private static final int DAMAGERANGE = 50;
    private static final int DAMAGE = 100;
    private static final int SPEED = 15;
    private static final int SIZE = 10;
    public Bomb(int x, int y, int heading,int destX, int destY, Team team){
        super(x, y, SIZE, heading, destX, destY, team);
        _damageRange = DAMAGERANGE;
        _damage = DAMAGE;
        _speed = SPEED;
        _bodyPic = "resources/images/" + team.getName() + "/plane/bullet.png";
    }
}
