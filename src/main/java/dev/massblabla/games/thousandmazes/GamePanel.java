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

package dev.massblabla.games.thousandmazes;

import java.awt.*;
import java.io.Serial;

import javax.swing.JPanel;

import dev.massblabla.games.thousandmazes.config.Config;
import dev.massblabla.games.thousandmazes.entity.Player;
import dev.massblabla.games.thousandmazes.misc.Variables;
import dev.massblabla.games.thousandmazes.tile.TileManager;
import dev.massblabla.games.thousandmazes.util.CollisionChecker;
import dev.massblabla.games.thousandmazes.util.KeyHandler;

/**
 * ThousandMazes' game panel.
 * 
 * @version 0.1.0-SNAPSHOT
 * @author massblabla
 */
public class GamePanel extends JPanel implements Runnable {
	@Serial
    private static final long serialVersionUID = -3544079111988284342L;
	
	/* Variables+Config shortcut */
	public static final Variables var = Variables.INSTANCE;
	public static final Config conf = Config.instance;

	/* Gets screen width and height for autoWindowSizing */
    final Toolkit toolkit = Toolkit.getDefaultToolkit();
	final Dimension screenSize = toolkit.getScreenSize();

	/* Local variables */
	public final long tileSize = Math.round(conf.requiresRestart.getDefaultTileSize() * conf.requiresRestart.getDefaultRelativeScale());
	public long windowWidth = conf.requiresRestart.getTotalDisplayedColumns() * tileSize; /* Default: 1152 */
	public long windowHeight = conf.requiresRestart.getTotalDisplayedRows() * tileSize;   /* Default: 672 */

	/* KeyHandler */
    final KeyHandler kh = new KeyHandler();
	/* The game's thread */
	Thread gameThread;
	/* Player entity class */
    public final Player player = new Player(this, kh);
    /* CollisionChecker */
    public final CollisionChecker cc = new CollisionChecker(this);
    /* TileManager */
    public final TileManager tm = new TileManager(this);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension((int) windowWidth, (int) windowHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(kh);
		this.setFocusable(true);

		if(conf.requiresRestart.getAutoWindowSizing()) {
			windowWidth = (long) Math.ceil(screenSize.width / 2.4);
			windowHeight = (long) Math.ceil(screenSize.height / 2.4);
		} else {
			windowWidth = conf.requiresRestart.getTotalDisplayedColumns() * tileSize;
			windowHeight = conf.requiresRestart.getTotalDisplayedRows() * tileSize;
		}
	}
	public void startThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		double drawInterval = (double)1000000000 / var.tickRateCap;
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
				System.out.println("Tick Rate: " + drawCount + ", X: " + player.worldX + " Y: " + player.worldY + " Direction: " + player.direction);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	public void update() {
		player.update();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;

        tm.draw(g2);
		player.draw(g2);

		g2.dispose();
	}
}
