/*
 * Copyright (c) [insert release year] massblabla
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
 * @version 0.0.1-SNAPSHOT
 * @author massblabla
 */
public class TileManager {
    GamePanel panel;
    public Tile[] tile;
    public static Config conf = Config.instance;
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
            texture = new TileMapHandler("/assets/thousandmazes/tilemap.png", (int)conf.requiresRestart.getDefaultTileSize());

            /* Tile 0 (null) */
            tile[0x00] = new Tile();
            tile[0x00].image = texture.getTile(0x0, 0x0);

            /* thousandmazes:debugmd materials */
            /* Tile 1 (debug wall) */
            tile[0x01] = new Tile(); tile[0x01].image = texture.getTile(0x1, 0x0); tile[0x01].collision = true;
            /* Tile 2 (debug obstacle) */
            tile[0x02] = new Tile(); tile[0x02].image = texture.getTile(0x2, 0x0); tile[0x02].collision = true;
            /* Tile 3 (debug path) */
            tile[0x03] = new Tile(); tile[0x03].image = texture.getTile(0x3, 0x0);
            /* Tile 4 (debug en/ex) */
            tile[0x04] = new Tile(); tile[0x04].image = texture.getTile(0x4, 0x0);

            /* thousandmazes:earthly materials */
            /* Tile 5 (earthly wall) */
            tile[0x05] = new Tile(); tile[0x05].image = texture.getTile(0x5, 0x0); tile[0x05].collision = true;
            /* Tile 6 (earthly obstacle) */
            tile[0x06] = new Tile(); tile[0x06].image = texture.getTile(0x6, 0x0); tile[0x06].collision = true;
            /* Tile 7 (earthly path) */
            tile[0x07] = new Tile(); tile[0x07].image = texture.getTile(0x7, 0x0);
            /* Tile 8 (earthly en/ex) */
            tile[0x08] = new Tile(); tile[0x08].image = texture.getTile(0x8, 0x0);

            /* thousandmazes:hellish materials */
            /* Tile 9 (hellish wall) */
            tile[0x09] = new Tile(); tile[0x09].image = texture.getTile(0x9, 0x0); tile[0x09].collision = true;
            /* Tile 10 (hellish obstacle) */
            tile[0x0A] = new Tile(); tile[0x0A].image = texture.getTile(0xA, 0x0); tile[0x0A].collision = true;
            /* Tile 11 (hellish path) */
            tile[0x0B] = new Tile(); tile[0x0B].image = texture.getTile(0xB, 0x0);
            /* Tile 12 (hellish en/ex) */
            tile[0x0C] = new Tile(); tile[0x0C].image = texture.getTile(0xC, 0x0);

            /* thousandmazes:stellar materials */
            /* Tile 13 (stellar wall) */
            tile[0x0D] = new Tile(); tile[0x0D].image = texture.getTile(0xD, 0x0); tile[0x0D].collision = true;
            /* Tile 14 (stellar obstacle) */
            tile[0x0E] = new Tile(); tile[0x0E].image = texture.getTile(0xE, 0x0); tile[0x0E].collision = true;
            /* Tile 15 (stellar path) */
            tile[0x0F] = new Tile(); tile[0x0F].image = texture.getTile(0xF, 0x0);
            /* Tile 16 (stellar en/ex) */
            tile[0x10] = new Tile(); tile[0x10].image = texture.getTile(0x0, 0x1);

            /* thousandmazes:dungeon materials */
            /* Tile 17 (dungeon wall) */
            tile[0x11] = new Tile(); tile[0x11].image = texture.getTile(0x1, 0x1); tile[0x11].collision = true;
            /* Tile 18 (dungeon obstacle) */
            tile[0x12] = new Tile(); tile[0x12].image = texture.getTile(0x2, 0x1); tile[0x12].collision = true;
            /* Tile 19 (dungeon path) */
            tile[0x13] = new Tile(); tile[0x13].image = texture.getTile(0x3, 0x1);
            /* Tile 20 (dungeon en/ex) */
            tile[0x14] = new Tile(); tile[0x14].image = texture.getTile(0x4, 0x1);

            /* thousandmazes:colored materials */
            /* Tile 21 (colored wall) */
            tile[0x15] = new Tile(); tile[0x15].image = texture.getTile(0x5, 0x1); tile[0x15].collision = true;
            /* Tile 22 (colored obstacle) */
            tile[0x16] = new Tile(); tile[0x16].image = texture.getTile(0x6, 0x1); tile[0x16].collision = true;
            /* Tile 23 (colored path) */
            tile[0x17] = new Tile(); tile[0x17].image = texture.getTile(0x7, 0x1);
            /* Tile 24 (colored en/ex) */
            tile[0x18] = new Tile(); tile[0x18].image = texture.getTile(0x8, 0x1);
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int mazeRows = region.getSide() * 2 + 1;
        int mazeCols = region.getSide() * 2 + 1;
        byte[] tiles = region.getTiles();

        while(col < mazeCols && row < mazeRows) {

            int worldX = col * (int)panel.tileSize;
            int worldY = row * (int)panel.tileSize;
            int screenX = worldX - panel.player.worldX + panel.player.screenX;
            int screenY = worldY - panel.player.worldY + panel.player.screenY;

            if(worldX + panel.tileSize > panel.player.worldX - panel.player.screenX && worldX - panel.tileSize < panel.player.worldX + panel.player.screenX &&
                worldY + panel.tileSize > panel.player.worldY - panel.player.screenY && worldY - panel.tileSize < panel.player.worldY + panel.player.screenY) {
                g2.drawImage(tile[tiles[row * mazeCols + col]].image, screenX, screenY, (int)panel.tileSize, (int)panel.tileSize, null);
            }
            col++;

            if(col == mazeCols) {
                col = 0;
                row++;
            }
        }
    }
}
