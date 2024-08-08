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

package massblabla.games.thousandmazes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import massblabla.games.thousandmazes.config.Config;
import massblabla.games.thousandmazes.misc.Variables;
import massblabla.games.thousandmazes.util.KeyHandler;

/**
 * ThousandMazes' game panel.
 * 
 * @version 0.0.1-SNAPSHOT
 * @author massblabla
 */
public class GamePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = -3544079111988284342L;
	
	/* Variables+Config shortcut */
	public static Variables var = Variables.INSTANCE;
	public static Config conf = Config.instance;
	
	/* Local variables */
	final long tileDisplaySize = conf.getDefaultTileSize() * conf.getDefaultRelativeScale();
	final long screenWidth = conf.getTotalDisplayedColumns() * tileDisplaySize; /* Default: 1152 */
	final long screenHeight = conf.getTotalDisplayedRows() * tileDisplaySize;   /* Default: 672 */
																			    /* That means, by default, the aspect ratio is 12:7. */
	
	/* KeyHandler */
	KeyHandler kh = new KeyHandler();
	/* The game's thread */
	Thread gameThread;
	
	/* Set player's default position */
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 6;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension((int)screenWidth, (int)screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(kh);
		this.setFocusable(true);
	}
	public void startThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		double drawInterval = 1000000000/conf.getFPSCap();
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	public void update() {
		if(kh.upPressed) {
			playerY -= playerSpeed;
		} else if(kh.leftPressed) {
			playerX -= playerSpeed;
		} else if(kh.downPressed) {
			playerY += playerSpeed;
		} else if(kh.rightPressed) {
			playerX += playerSpeed;
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.WHITE);
		g2.fillRect(playerX, playerY, (int)tileDisplaySize, (int)tileDisplaySize);
		g2.dispose();
	}
}
