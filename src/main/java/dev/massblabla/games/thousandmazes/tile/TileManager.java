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
    Tile[] tile;
    public static Config conf = Config.instance;
    TileMapHandler texture;
    int mapTileNum[][];

    public TileManager(GamePanel panel) {
        this.panel = panel;
        tile = new Tile[51] /* 7 materials (4 tiles), chest, 7 obstacles per material */;
        mapTileNum = new int[(int)conf.requiresRestart.getTotalDisplayedColumns()][(int)conf.requiresRestart.getTotalDisplayedRows()];

        getTileTexture();
    }

    public void getTileTexture() {
        try {
            texture = new TileMapHandler("/assets/thousandmazes/tilemap.png", (int)conf.requiresRestart.getDefaultTileSize());

            /* Tile 0 (nothingness, null) */
            tile[0] = new Tile();
            tile[0].image = texture.getTile(0, 0);

            /* Tile 1 (default wall) */
            tile[1] = new Tile();
            tile[1].image = texture.getTile(1, 0);

            /* Tile 2 (default path) */
            tile[2] = new Tile();
            tile[2].image = texture.getTile(2, 0);

        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < conf.requiresRestart.getTotalDisplayedColumns() && row < conf.requiresRestart.getTotalDisplayedRows()) {
            g2.drawImage(tile[1].image, x, y, (int)panel.tileSize, (int)panel.tileSize, null);
            col++;
            x += panel.tileSize;

            if(col == conf.requiresRestart.getTotalDisplayedColumns()) {
                col = 0;
                x = 0;

                row++;
                y += panel.tileSize;
            }
        }
    }
}
