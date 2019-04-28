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
import BridgePattern.*;
import javafx.scene.image.Image;
/**
 *
 * @author csc190
 */
public class Map {
    private static final String COMMON = "resources/images/common/";
    private static final String MINI = "resources/images/mini/";
    
    public Image _minimap;
    protected Picture[][] _map;
    protected String _mapPath;
    
    public Map(String mapPath){
        _mapPath = mapPath;
    }
    public void generatePics(ICanvasDevice canvas){
        String input = canvas.readFile(_mapPath);
        String[] lines = input.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String[] arrNames = line.split(" +");
            for (int j = 0; j < arrNames.length; j++) {
                String name = arrNames[j];
                String commonPath =  COMMON + name + ".png";
                String miniPath = MINI + name +".png";
                _map[i][j] = new Picture(commonPath, j * 100, i * 100, 100);
                
            }
        }
    }
    
    public Picture[][] getMap(){ return _map;}
    
    
    
}
