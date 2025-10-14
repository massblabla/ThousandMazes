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

package dev.massblabla.games.thousandmazes.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Parent class for any entities.
 *
 * @version 0.1.0-SNAPSHOT
 * @author massblabla
 */
public class Entity {
    /* Entity's coordinates and speed */
    public int worldX, worldY;
    public int speed;

    /* The textures of the entity */
    public BufferedImage north1, north2, east1, east2, south1, south2, west1, west2;
    /* Direction the entity is heading */
    public String direction;

    public int textureCounter = 0;
    public int textureNum = 1;

    /* Hitbox */
    public Rectangle hitbox;
    public boolean isCollisionOn = false;
}
