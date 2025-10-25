/*
 * Copyright (C) 2025 massblabla
 *
 * This file is part of ThousandMazes.
 * ThousandMazes is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * ThousandMazes is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * ThousandMazes. If not, see <https://www.gnu.org/licenses/>.
 */

package dev.massblabla.games.thousandmazes.tile;

import dev.massblabla.games.thousandmazes.GamePanel;
import dev.massblabla.games.thousandmazes.config.Config;
import dev.massblabla.games.thousandmazes.util.TileMapHandler;
import dev.massblabla.utils.worldregion.WorldRegion;

import java.awt.*;
import java.io.IOException;

/**
 * Manages tiles and its rendering.
 *
 *
 * @version 0.2.0-SNAPSHOT
 * @author massblabla
 */
public class TileManager {
    GamePanel panel;
    public Tile[] tile;
    public static final Config conf = Config.instance;
    TileMapHandler texture;

    public WorldRegion region;
    {
        try {
            region = WorldRegion.load("world_region.dat");
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }

    public TileManager(GamePanel panel) {
        this.panel = panel;
        tile = new Tile[0x19] /* null, 6 materials (4 tiles), including: wall, obstacle, path, and entrance/exit */;

        getTileTexture();
    }

    public void getTileTexture() {
        try {
            texture = new TileMapHandler("/assets/thousandmazes/tilemap.png", conf.requiresRestart.getDefaultTileSize());

            /* Use setupTile for all tiles (index, tmCol, tmRow, collision) */
            setupTile(0x00, 0x0, 0x0, true); // null

            /* debug materials */
            setupTile(0x01, 0x1, 0x0, true); // debug wall
            setupTile(0x02, 0x2, 0x0, true); // debug obstacle
            setupTile(0x03, 0x3, 0x0, false); // debug path
            setupTile(0x04, 0x4, 0x0, false); // debug en/ex

            /* earthly */
            setupTile(0x05, 0x5, 0x0, true); // earthly wall
            setupTile(0x06, 0x6, 0x0, true); // earthly obstacle
            setupTile(0x07, 0x7, 0x0, false); // earthly path
            setupTile(0x08, 0x8, 0x0, false); // earthly en/ex

            /* hellish */
            setupTile(0x09, 0x9, 0x0, true); // hellish wall
            setupTile(0x0A, 0xA, 0x0, true); // hellish obstacle
            setupTile(0x0B, 0xB, 0x0, false); // hellish path
            setupTile(0x0C, 0xC, 0x0, false); // hellish en/ex

            /* stellar */
            setupTile(0x0D, 0xD, 0x0, true); // stellar wall
            setupTile(0x0E, 0xE, 0x0, true); // stellar obstacle
            setupTile(0x0F, 0xF, 0x0, false); // stellar path
            setupTile(0x10, 0x0, 0x1, false); // stellar en/ex

            /* dungeon */
            setupTile(0x11, 0x1, 0x1, true); // dungeon wall
            setupTile(0x12, 0x2, 0x1, true); // dungeon obstacle
            setupTile(0x13, 0x3, 0x1, false); // dungeon path
            setupTile(0x14, 0x4, 0x1, false); // dungeon en/ex

            /* colored */
            setupTile(0x15, 0x5, 0x1, true); // colored wall
            setupTile(0x16, 0x6, 0x1, true); // colored obstacle
            setupTile(0x17, 0x7, 0x1, false); // colored path
            setupTile(0x18, 0x8, 0x1, false); // colored en/ex
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }

    public void setupTile(int index, int tmCol, int tmRow, boolean collision) {
        tile[index] = new Tile();
        tile[index].image = texture.getTile(tmCol, tmRow, conf.requiresRestart.getDefaultRelativeScale());

        tile[index].collision = collision;
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int mazeRows = region.side() * 2 + 1;
        int mazeCols = region.side() * 2 + 1;
        byte[][] tiles = region.tiles();

        while(col < mazeCols && row < mazeRows) {
            int worldX = col * panel.tileSize;
            int worldY = row * panel.tileSize;
            int screenX = worldX - panel.player.worldX + panel.player.screenX;
            int screenY = worldY - panel.player.worldY + panel.player.screenY;

            if(worldX + panel.tileSize > panel.player.worldX - panel.player.screenX && worldX - panel.tileSize < panel.player.worldX + panel.player.screenX &&
                worldY + panel.tileSize > panel.player.worldY - panel.player.screenY && worldY - panel.tileSize < panel.player.worldY + panel.player.screenY) {
                g2.drawImage(tile[tiles[row][col]].image, screenX, screenY, null);
            }
            col++;

            if(col == mazeCols) {
                col = 0;
                row++;
            }
        }
    }
}
