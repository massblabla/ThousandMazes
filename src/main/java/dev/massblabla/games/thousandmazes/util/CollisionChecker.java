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

package dev.massblabla.games.thousandmazes.util;

import dev.massblabla.games.thousandmazes.GamePanel;
import dev.massblabla.games.thousandmazes.entity.Entity;

/**
 * Checks the collision.
 *
 * @version 0.1.0-SNAPSHOT
 * @author massblabla
 */
public class CollisionChecker {
    private final GamePanel panel;

    public CollisionChecker(GamePanel panel) {
        this.panel = panel;
    }

    public void checkTile(Entity entity) {
        int entityWestWorldX = entity.worldX + entity.hitbox.x;
        int entityEastWorldX = entity.worldX + entity.hitbox.x + entity.hitbox.width;
        int entityNorthWorldY = entity.worldY + entity.hitbox.y;
        int entitySouthWorldY = entity.worldY + entity.hitbox.y + entity.hitbox.height;

        int entityWestCol = entityWestWorldX / panel.tileSize;
        int entityEastCol = entityEastWorldX / panel.tileSize;
        int entityNorthRow = entityNorthWorldY / panel.tileSize;
        int entitySouthRow = entitySouthWorldY / panel.tileSize;

        int tileNum1, tileNum2;
        byte[][] tiles = panel.tm.region.tiles();

        switch(entity.direction) {
            case "north" -> {
                try {
                    entityNorthRow = (entityNorthWorldY - entity.speed) / panel.tileSize;
                    tileNum1 = tiles[entityNorthRow][entityWestCol];
                    tileNum2 = tiles[entityNorthRow][entityEastCol];
                    if (panel.tm.tile[tileNum1].collision || panel.tm.tile[tileNum2].collision) {
                        entity.isCollisionOn = true;
                    }
                } catch (ArrayIndexOutOfBoundsException aioobex) {
                    entity.isCollisionOn = true;
                }
            }
            case "east" -> {
                try {
                    entityEastCol = (entityEastWorldX + entity.speed) / panel.tileSize;
                    tileNum1 = tiles[entityNorthRow][entityEastCol];
                    tileNum2 = tiles[entitySouthRow][entityEastCol];
                    if (panel.tm.tile[tileNum1].collision || panel.tm.tile[tileNum2].collision) {
                        entity.isCollisionOn = true;
                    }
                } catch (ArrayIndexOutOfBoundsException aioobex) {
                    entity.isCollisionOn = true;
                }
            }
            case "south" -> {
                try {
                    entitySouthRow = (entitySouthWorldY - entity.speed) / panel.tileSize;
                    tileNum1 = tiles[entitySouthRow][entityWestCol];
                    tileNum2 = tiles[entitySouthRow][entityEastCol];
                    if (panel.tm.tile[tileNum1].collision || panel.tm.tile[tileNum2].collision) {
                        entity.isCollisionOn = true;
                    }
                } catch (ArrayIndexOutOfBoundsException aioobex) {
                    entity.isCollisionOn = true;
                }
            }
            case "west" -> {
                try {
                    entityWestCol = (entityWestWorldX - entity.speed) / panel.tileSize;
                    tileNum1 = tiles[entityNorthRow][entityWestCol];
                    tileNum2 = tiles[entitySouthRow][entityWestCol];
                    if (panel.tm.tile[tileNum1].collision || panel.tm.tile[tileNum2].collision) {
                        entity.isCollisionOn = true;
                    }
                } catch (ArrayIndexOutOfBoundsException aioobex) {
                    entity.isCollisionOn = true;
                }
            }
        }
    }
}
