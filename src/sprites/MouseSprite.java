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

import BridgePattern.ICanvasDevice;
import EvilCraft.GameEngine;
import EvilCraft.MouseEvent;
import EvilCraft.Point;
import java.util.ArrayList;
import static sprites.MouseSprite.ROOTPATH;

/**
 * Represent the object of Mouse. Display mouse shapes at different situation
 *
 * @author csc190
 */
public class MouseSprite extends Sprite {

    //int clientx = -1, clienty = -1;
    
    public static String ROOTPATH = "resources/images/common/";
    enum MODE { NONE, LEFT, RIGHT, TOP, BOTTOM, MOVE, ATTACK };
    protected MODE _mode;
    ArrayList<Unit> _arrTargets = null;
    protected String _pic = null;
    protected ICanvasDevice _mainview;
    protected ICanvasDevice _minimap;

    public MouseSprite(ICanvasDevice mainview, ICanvasDevice minimap) {
        _mainview = mainview;
        _minimap = minimap;
    }

    /**
     * *
     * Handle many cases: (1) MouseMove (not close to boundary of canvas): when
     * there are units selected: if arrSprites is empty or null, take the "Move"
     * mode; otherwise "Attack Mode" (2) MouseMove (close to canvas): take the
     * "Arrow" mode. Arrow direction depending on location in canvas (3)
     * LeftClick: set the state to no units selected so that even Mouse Move, it
     * will not show "Move" or "Attack" mode
     *
     * @param eventType
     * @param canvas
     * @param x
     * @param y
     * @param arrSprites
     */
    public void handleEvnet(MouseEvent eventType, ICanvasDevice canvas, int x, int y, ArrayList<Unit> arrSprites) {
        GameEngine ge = GameEngine.getInstance();
        if (eventType == MouseEvent.LeftClick) {
            _arrTargets = null;
            _mode = MODE.NONE;
        } else if (eventType == MouseEvent.RightClick) {
            if (arrSprites != null) {
                _mode = MODE.ATTACK;
                _arrTargets = arrSprites;
            }else _mode = MODE.MOVE;
        } else if (eventType == MouseEvent.MouseMove && canvas == _mainview) {

            Point mappoint = ge.getGlobalCoordinate(_mainview, x, y);
            _x = mappoint._x;
            _y = mappoint._y;
//            this.clientx = x;
//            this.clienty = y;
            if (x >= 0 && x <= 20) {
                _mode = MODE.LEFT;
            } else if (x > canvas.getWidth() - 50) {
                _mode = MODE.RIGHT;
            } else if (y >= 0 && y <= 20) {
                _mode = MODE.TOP;
            } else if (y > canvas.getHeight() - 50) {
                _mode = MODE.BOTTOM;
            }

        } else {
            _mode = MODE.NONE;
        }
    }

    protected int ticks = 0;

    @Override
    public void update() {
        ticks++;
        GameEngine ge = GameEngine.getInstance();

        if (_mode == MODE.BOTTOM) {
            _pic = ROOTPATH + "down.png";
            _mainview.setViewPort(_mainview.viewX(), _mainview.viewY()+ 2);
        } else if (_mode == MODE.LEFT) {
            _pic = ROOTPATH + "left.png";
            this._mainview.setViewPort(_mainview.viewX()- 2, _mainview.viewY());
        } else if (_mode == MODE.RIGHT) {
            _pic = ROOTPATH + "right.png";
            this._mainview.setViewPort(_mainview.viewX()+ 2, _mainview.viewY());
        } else if (_mode == MODE.TOP) {
            _pic = ROOTPATH + "up.png";
            this._mainview.setViewPort(_mainview.viewX(), _mainview.viewY()- 2);
        } else if (_mode == MODE.MOVE) {
            _pic = ticks / 10 % 2 == 0 ? ROOTPATH + "move.png" : null;
        } else if (_mode == MODE.ATTACK) {
            _pic = ticks / 10 % 2 == 0 ? ROOTPATH + "attack.png" : null;
        }
    }

    @Override
    public void drawOnMainView(ICanvasDevice mainview) {
        if (_pic != null) {
            _mainview.drawImg(_pic, _x - 25, _y - 25, 50, 50, 0);
        }
    }

    @Override
    public void drawOnMiniMap(ICanvasDevice minimap) {
        //do nothing
    }

}
