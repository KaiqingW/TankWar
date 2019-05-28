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
package map;

import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author csc190
 */
public class Map {
    public static int UNITSIZE = 100;
    public static final String ROOTPATH = "resources/images/common/";
    protected Node[][] MAP;
    protected String _mapPath;
    
    public Map(String mapPath){
        _mapPath = mapPath;
        genMap(_mapPath);
    }
    
    private String readFile(String filepath) {
        int idx = filepath.indexOf("resources/");
        filepath = filepath.substring(idx+"resources/".length());
        InputStream is = getClass().getClassLoader().getResourceAsStream(filepath);
        Scanner sc = new Scanner(is);
        String sContent = sc.useDelimiter("\\Z").next();  
        return sContent;
    }
    
    private void genMap(String filePath){
        String content = readFile(filePath);
        String[] lines = content.split("\n");
        int height = lines.length;
        int width = lines[0].split(" ").length;
        MAP = new Node[height][width];
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                MAP[i][j] = new Node(ROOTPATH + lines[i].split(" ")[j] + ".png");
            }
        }
    }
    
    public Node[][] getMap(){ return MAP;}
}
