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
package EvilCraftMilestone4;

import java.util.Hashtable;
/**
 *
 * @author csc190
 */
public class Team {
    static final Hashtable<Integer,String> idName = new Hashtable<Integer,String>(){{
        put(1,"red");
        put(2,"yellow");
    }};
    
    int _id;
    String _name;
    
    public Team(int id) {
        _id = id;
        _name = idName.get(_id);
    }
    
    public String getName(){ return _name;}
}
