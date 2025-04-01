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
import dev.massblabla.games.thousandmazes.util.KeyHandler;
import dev.massblabla.games.thousandmazes.util.TileMapHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The class for the entity player.
 *
 * @version 0.0.1-SNAPSHOT
 * @author massblabla
 */
public class Player extends Entity {
    GamePanel panel;
    KeyHandler kh;
    TileMapHandler player;
    Config conf;

    /* Constructor */
    public Player(GamePanel panel, KeyHandler kh) {
        this.panel = panel;
        this.kh = kh;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;

        direction = "south";
    }

    public void getPlayerImage() {
        try {
            player = new TileMapHandler("/assets/thousandmazes/entities/default/thomas.png", 16);

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
                y -= speed;
            } else if(kh.rightPressed) {
                direction = "east";
                x += speed;
            } else if(kh.downPressed) {
                direction = "south";
                y += speed;
            } else if(kh.leftPressed) {
                direction = "west";
                x -= speed;
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

        g2.drawImage(image, x, y, (int)panel.tileSize, (int)panel.tileSize, null);
    }
}
