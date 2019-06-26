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

import EvilCraft.Point;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author csc190
 */
public class ArmyUnit extends Unit{
    static int[] DIRX = {0,1,1,1,0,-1,-1,-1};
    static int[] DIRY = {1,1,0,-1,-1,-1,0,1};
    
    Point _dest;
    
    int[][] _nMap;
    
    int _tileX;
    int _tileY;
    int _destTX;
    int _destTY;
    
    public void setTargetTo(Unit u){}
    
    void setDest(Point pt){
        for (int i=0;i<_nMap.length;i++){
            for (int j=0;j<_nMap[0].length;j++){
                _nMap[i][j] = _nMap[i][j] == -1? -1:0;
            }
        }
        
        _dest = pt;
        _destTX = _dest._x % 100;
        _destTY = _dest._y % 100;
        
        Queue<Integer> sX = new LinkedList();
        Queue<Integer> sY = new LinkedList();
        sX.add(_destTX);
        sY.add(_destTY);
        
        while (sX.peek() != _tileX || sY.peek() != _tileY){
            for (int i=0;i<8;i++){
                int curX = sX.poll();
                int curY = sY.poll();
                int neighboorX = curX + DIRX[i];
                int neighboorY = curY + DIRY[i];
                
                if (neighboorX >= 0 && neighboorX < _nMap[0].length &&
                    neighboorY >= 0 && neighboorY < _nMap.length &&
                    _nMap[neighboorY][neighboorX] == 0){
                
                    _nMap[neighboorY][neighboorX] = _nMap[curY][curX]+1;
                    sX.add(neighboorX);
                    sY.add(neighboorY);
                }
            }
        }
    }
    
    void navigate(){
        int nextX = _tileX;
        int nextY = _tileY;
        
        int min = Integer.MAX_VALUE;
        for (int i=0;i<8;i++){
            int neighboorX = _tileX + DIRX[i];
            int neighboorY = _tileY + DIRY[i];
            if (neighboorX == _destTX && neighboorY == _destTY) {
                if (_nMap[neighboorY][neighboorX] != -1){
                    nextX = _dest._x;
                    nextY = _dest._y;
                }
                break;
            }
            if (neighboorX >= 0 && neighboorX < _nMap[0].length &&
                neighboorY >= 0 && neighboorY < _nMap.length &&
                _nMap[neighboorY][neighboorX] != -1 && 
                _nMap[neighboorY][neighboorX] < min){
                
                nextX = neighboorX * 100 + 50;
                nextY = neighboorY * 100 + 50;
            }
        }

        int distance = (int)(Math.sqrt(
                        (nextX - _x) * (nextX - _x) + 
                        (nextY - _y) * (nextY - _y)));
        int dX = (int)((nextX - _x)/distance);
        int dY = (int)((nextY - _y)/distance);
        _x += dX;
        _y += dY;
        _tileX = _x % 100;
        _tileY = _y % 100;
    }
}
