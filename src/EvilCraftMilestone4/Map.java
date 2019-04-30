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
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
/**
 *
 * @author csc190
 */
public class Map {
    private static final String COMMON = "resources/images/common/";
    private static final String MINI = "resources/images/mini/";
    private static final int _miniSize = 1;
    
    public Image _minimap;
    protected Picture[][] _map;
    protected String _mapPath;
    
    public Map(String mapPath){
        _mapPath = mapPath;
    }
    public void init(ICanvasDevice canvas){
        String input = canvas.readFile(_mapPath);
        String[] lines = input.split("\n");
        String[] width = lines[0].split(" +");
        Canvas tempC = new Canvas(_miniSize*width.length,_miniSize*lines.length);
        GraphicsContext gc = tempC.getGraphicsContext2D();
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String[] arrNames = line.split(" +");
            for (int j = 0; j < arrNames.length; j++) {
                String name = arrNames[j];
                String commonPath =  COMMON + name + ".png";
                String miniPath = MINI + name +".png";
                _map[i][j] = new Picture(commonPath, j * 100, i * 100, 100);
                Image img = canvas.getImage(miniPath);
                gc.drawImage(img,j*_miniSize,i*_miniSize); 
            }
        }
        WritableImage img = tempC.snapshot(new SnapshotParameters(), null);
        _minimap = img;
    }
    
    public Picture[][] getMap(){ return _map;}
    
    public Image getMini(){ return _minimap;}
    
    public String getPath(){ return _mapPath;}
    
}
