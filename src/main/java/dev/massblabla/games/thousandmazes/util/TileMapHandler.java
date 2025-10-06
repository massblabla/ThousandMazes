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
package dev.massblabla.games.thousandmazes.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Handles tilemaps so not too much boilerplate code (Java is already tons of boilerplate tho.)
 *
 * @version 0.1.0-SNAPSHOT
 * @author massblabla
 */
public class TileMapHandler {
    private final BufferedImage tilemap;
    private final int tileSize;

    public TileMapHandler(String path, int tileSize) throws IOException {
        this.tileSize = tileSize;

        // Load the image from the resources folder
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is == null) {
                throw new IOException("Resource not found: " + path);
            }
            this.tilemap = ImageIO.read(is);
        }
    }

    public BufferedImage getTile(int col, int row) {
        return tilemap.getSubimage(col * tileSize, row * tileSize, tileSize, tileSize);
    }
}
