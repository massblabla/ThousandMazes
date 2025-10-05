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

import dev.massblabla.games.thousandmazes.GamePanel;
import dev.massblabla.games.thousandmazes.entity.Entity;

/**
 * Checks the collision.
 *
 * @version 0.0.1-SNAPSHOT
 * @author massblabla
 */
public class CollisionChecker {
    GamePanel panel;

    public CollisionChecker(GamePanel panel) {
        this.panel = panel;
    }

    public void checkTile(Entity entity) {
        int entityWestWorldX = entity.worldX + entity.hitbox.x;
        int entityEastWorldX = entity.worldX + entity.hitbox.x + entity.hitbox.width;
        int entityNorthWorldY = entity.worldY + entity.hitbox.y;
        int entitySouthWorldY = entity.worldY + entity.hitbox.y + entity.hitbox.height;

        int entityWestCol = entityWestWorldX / (int)panel.tileSize;
        int entityEastCol = entityEastWorldX / (int)panel.tileSize;
        int entityNorthRow = entityNorthWorldY / (int)panel.tileSize;
        int entitySouthRow = entitySouthWorldY / (int)panel.tileSize;

        int tileNum1, tileNum2;
        byte[][] tiles = unflatten(panel.tm.region.getTiles(), panel.tm.region.getSide() * 2 + 1, panel.tm.region.getSide() * 2 + 1);

        switch(entity.direction) {
            case "north" -> {
                entityNorthRow = (entityNorthWorldY - entity.speed) / (int)panel.tileSize;
                tileNum1 = tiles[entityNorthRow][entityWestCol];
                tileNum2 = tiles[entityNorthRow][entityEastCol];
                if(panel.tm.tile[tileNum1].collision || panel.tm.tile[tileNum2].collision) {
                    entity.isCollisionOn = true;
                }
            }
            case "east" -> {
                entityEastCol = (entityEastWorldX + entity.speed) / (int)panel.tileSize;
                tileNum1 = tiles[entityNorthRow][entityEastCol];
                tileNum2 = tiles[entitySouthRow][entityEastCol];
                if(panel.tm.tile[tileNum1].collision || panel.tm.tile[tileNum2].collision) {
                    entity.isCollisionOn = true;
                }
            }
            case "south" -> {
                entitySouthRow = (entitySouthWorldY - entity.speed) / (int)panel.tileSize;
                tileNum1 = tiles[entitySouthRow][entityWestCol];
                tileNum2 = tiles[entitySouthRow][entityEastCol];
                if(panel.tm.tile[tileNum1].collision || panel.tm.tile[tileNum2].collision) {
                    entity.isCollisionOn = true;
                }
            }
            case "west" -> {
                entityWestCol = (entityWestWorldX - entity.speed) / (int)panel.tileSize;
                tileNum1 = tiles[entityNorthRow][entityWestCol];
                tileNum2 = tiles[entitySouthRow][entityWestCol];
                if(panel.tm.tile[tileNum1].collision || panel.tm.tile[tileNum2].collision) {
                    entity.isCollisionOn = true;
                }
            }
        }
    }

    public static byte[][] unflatten(byte[] flat, int rows, int cols) {
        byte[][] result = new byte[rows][cols];
        for (int r = 0; r < rows; r++) {
            System.arraycopy(flat, r * cols, result[r], 0, cols);
        }
        return result;
    }
}
