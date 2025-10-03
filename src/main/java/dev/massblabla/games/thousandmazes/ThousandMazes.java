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

import dev.massblabla.games.thousandmazes.generation.MazeGenerator;
import dev.massblabla.games.thousandmazes.generation.enums.WorldDifficulties;
import dev.massblabla.games.thousandmazes.generation.enums.WorldMaterials;
import dev.massblabla.games.thousandmazes.misc.Variables;
import dev.massblabla.utils.worldregion.WorldRegion;

import java.io.IOException;

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
		String seed = "talithazalfanaifah:)))";
		MazeGenerator gen = new MazeGenerator(WorldDifficulties.MEDIUM, WorldMaterials.DEBUGMD, seed.hashCode());
		WorldRegion region = gen.generate(gen.getSide());

		try {
			region.save("world_region.dat");
			System.out.println("Saved world_region.dat successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}

        JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(var.title + var.space + var.version);
		
		GamePanel panel = new GamePanel();
		frame.add(panel);
		
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		panel.startThread();
		String path = "world_region.dat";

		try {
			// Load the world region from file
			WorldRegion region1 = WorldRegion.load(path);

			// Access its properties
			System.out.println("Loaded world with side: " + region1.getSide());
			System.out.println("Tile count: " + region1.getTiles().length);

			// Optional: print the maze as ASCII
			printMaze(region1);

		} catch (IOException e) {
			System.err.println("Failed to load WorldRegion: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Simple ASCII visualization of the 1D maze
	private static void printMaze(WorldRegion region) {
		int side = region.getSide();
		int mazeRows = side * 2 + 1;
		int mazeCols = side * 2 + 1;
		byte[] tiles = region.getTiles();

		for (int r = 0; r < mazeRows; r++) {
			for (int c = 0; c < mazeCols; c++) {
				System.out.print(tiles[r * mazeCols + c] == 1 ? "█" : " ");
			}
			System.out.println();
		}
	}
}
