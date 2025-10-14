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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KeyHandler so that the keyboard works.
 * 
 * @version 0.1.0-SNAPSHOT
 * @author massblabla
 */
public class KeyHandler implements KeyListener {
	/* Variables for moving */
	public boolean upPressed, leftPressed, downPressed, rightPressed;
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_W) {
			upPressed = true;
		} else if(keyCode == KeyEvent.VK_A) {
			leftPressed = true;
		} else if(keyCode == KeyEvent.VK_S) {
			downPressed = true;
		} else if(keyCode == KeyEvent.VK_D) {
			rightPressed = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_W) {
			upPressed = false;
		} else if(keyCode == KeyEvent.VK_A) {
			leftPressed = false;
		} else if(keyCode == KeyEvent.VK_S) {
			downPressed = false;
		} else if(keyCode == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}
}
