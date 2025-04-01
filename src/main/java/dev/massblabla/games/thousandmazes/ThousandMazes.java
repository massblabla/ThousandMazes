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

import javax.swing.JFrame;

import dev.massblabla.games.thousandmazes.misc.Variables;

/**
 * ThousandMazes' main class.
 * 
 * @version 0.0.1-SNAPSHOT
 * @author massblabla
 */
public class ThousandMazes {
	/* Variables shortcut */
	public static Variables var = Variables.INSTANCE;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(var.title + var.space + var.version);
		
		GamePanel panel = new GamePanel();
		frame.add(panel);
		
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		panel.startThread();
	}
}
