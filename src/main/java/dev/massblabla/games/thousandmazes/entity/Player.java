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

package dev.massblabla.games.thousandmazes.entity;

import dev.massblabla.games.thousandmazes.GamePanel;
import dev.massblabla.games.thousandmazes.config.Config;
import dev.massblabla.games.thousandmazes.tile.TileManager;
import dev.massblabla.games.thousandmazes.util.KeyHandler;
import dev.massblabla.games.thousandmazes.util.TileMapHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The class for the entity player.
 *
 * @version 0.1.0-SNAPSHOT
 * @author massblabla
 */
public class Player extends Entity {
    final GamePanel panel;
    final KeyHandler kh;
    TileMapHandler player;
    public static final Config conf = Config.instance;
    final TileManager tm;

    public final int screenX;
    public final int screenY;

    /* Constructor */
    public Player(GamePanel panel, KeyHandler kh) {
        this.panel = panel;
        this.kh = kh;
        this.tm = new TileManager(panel);

        screenX = ((int)panel.windowWidth / 2) - ((int)panel.tileSize / 2);
        screenY = ((int)panel.windowHeight / 2) - ((int)panel.tileSize / 2);

        hitbox = new Rectangle();
        hitbox.x = 5 * (int)conf.requiresRestart.getDefaultRelativeScale();
        hitbox.y = 5 * (int)conf.requiresRestart.getDefaultRelativeScale();
        hitbox.width = 6 * (int)conf.requiresRestart.getDefaultRelativeScale();
        hitbox.height = 6 * (int)conf.requiresRestart.getDefaultRelativeScale();

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = (int)panel.tileSize;
        worldY = (int)panel.tileSize * (tm.region.getSide() * 2 - 1);
        speed = 4;

        direction = "south";
    }

    public void getPlayerImage() {
        try {
            player = new TileMapHandler("/assets/thousandmazes/entities/default/thomas.png", (int)conf.requiresRestart.getDefaultTileSize());

            north1 = player.getTile(0, 0);
            north2 = player.getTile(1, 0);
            east1 = player.getTile(0, 1);
            east2 = player.getTile(1, 1);
            south1 = player.getTile(0, 2);
            south2 = player.getTile(1, 2);
            west1 = player.getTile(0, 3);
            west2 = player.getTile(1, 3);
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }

    public void update() {
        if(kh.upPressed || kh.rightPressed || kh.downPressed || kh.leftPressed) {
            if(kh.upPressed) {
                direction = "north";
            } else if(kh.rightPressed) {
                direction = "east";
            } else if(kh.downPressed) {
                direction = "south";
            } else if(kh.leftPressed) {
                direction = "west";
            }

            // check tile collision
            isCollisionOn = false;
            panel.cc.checkTile(this);

            // if !collision player can move
            if(!isCollisionOn) {
                switch(direction) {
                    case "north" -> worldY -= speed;
                    case "east" -> worldX += speed;
                    case "south" -> worldY += speed;
                    case "west" -> worldX -= speed;
                }
            }

            textureCounter++;
            if(textureCounter > 12) {
                if(textureNum == 1) {
                    textureNum = 2;
                } else if(textureNum == 2) {
                    textureNum = 1;
                }
                textureCounter = 0;
            }
        } else {
            textureCounter++;
            if(textureCounter > 12) {
                textureNum = 1;
            }
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "north":
                if(textureNum == 1) {
                    image = north1;
                } else if(textureNum == 2) {
                    image = north2;
                }
                break;
            case "east":
                if(textureNum == 1) {
                    image = east1;
                } else if(textureNum == 2) {
                    image = east2;
                }
                break;
            case "south":
                if(textureNum == 1) {
                    image = south1;
                } else if(textureNum == 2) {
                    image = south2;
                }
                break;
            case "west":
                if(textureNum == 1) {
                    image = west1;
                } else if(textureNum == 2) {
                    image = west2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, (int)panel.tileSize, (int)panel.tileSize, null);
    }
}
