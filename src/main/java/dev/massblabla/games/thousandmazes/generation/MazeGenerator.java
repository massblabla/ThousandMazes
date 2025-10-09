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

package dev.massblabla.games.thousandmazes.generation;

import dev.massblabla.games.thousandmazes.generation.enums.WorldDifficulties;
import dev.massblabla.games.thousandmazes.generation.enums.WorldMaterials;
import dev.massblabla.utils.worldregion.WorldGenerator;
import dev.massblabla.utils.worldregion.WorldRegion;

import java.util.*;

/**
 * Generates mazes and saves it.
 *
 * @version 0.1.0-SNAPSHOT
 * @author massblabla
 */
public class MazeGenerator implements WorldGenerator {
    private final long seed;
    private final int side;
    private final byte[] tiles;

    public MazeGenerator(WorldDifficulties difficulty, WorldMaterials material, long seed) {
        switch (difficulty) {
            case STARTER -> side = 20;
            case EASY -> side = 30;
            case MEDIUM -> side = 40;
            case INTERMEDIATE -> side = 60;
            case HARD -> side = 80;
            case VERY_HARD -> side = 100;
            case INSANE -> side = 140;
            case EXTREME -> side = 200;
            case HARDCORE -> side = 300;
            default -> throw new IllegalArgumentException("Invalid world difficulty.");
        }
        switch (material) {
            // format: wall, obstacle, path, entrance/exit
            case DEBUGMD -> tiles = new byte[]{0x01, 0x02, 0x03, 0x04};
            case EARTHLY -> tiles = new byte[]{0x05, 0x06, 0x07, 0x08};
            case HELLISH -> tiles = new byte[]{0x09, 0x0A, 0x0B, 0x0C};
            case STELLAR -> tiles = new byte[]{0x0D, 0x0E, 0x0F, 0x10};
            case DUNGEON -> tiles = new byte[]{0x11, 0x12, 0x13, 0x14};
            case COLORED -> tiles = new byte[]{0x15, 0x16, 0x17, 0x18};
            default -> throw new IllegalArgumentException("Invalid world material type.");
        }

        generate(side);

        this.seed = seed;
    }

    private static class Mulberry32 {
        private long state;

        Mulberry32(long seed) { this.state = seed; }

        double nextDouble() {
            long t = state += 0x6D2B79F5L;
            t = (t ^ (t >>> 15)) * (t | 1); // normal multiplication
            t ^= t + ((t ^ (t >>> 7)) * (t | 61));
            return ((t ^ (t >>> 14)) & 0xFFFFFFFFL) / 4294967296.0;
        }
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    private void union(int[] parent, int a, int b) {
        parent[find(parent, a)] = find(parent, b);
    }

    @Override
    public WorldRegion generate(int side) {
        Mulberry32 rng = new Mulberry32(seed); // fixed seed

        int mazeRows = side * 2 + 1;
        int mazeCols = side * 2 + 1;
        byte[][] maze2D = new byte[mazeRows][mazeCols];

        // Fill everything with walls initially
        for (byte[] row : maze2D) Arrays.fill(row, (byte) 0); // wall

        // Mark open cells (temporary)
        for (int r = 0; r < side; r++)
            for (int c = 0; c < side; c++)
                maze2D[r * 2 + 1][c * 2 + 1] = 2; // temporary for open space

        // Prepare walls for Kruskal
        record Wall(int a, int b, int wr, int wc) { }
        List<Wall> walls = new ArrayList<>();
        for (int r = 0; r < side; r++)
            for (int c = 0; c < side; c++) {
                int cell = r * side + c;
                if (r > 0) walls.add(new Wall(cell, (r - 1) * side + c, r * 2, c * 2 + 1));
                if (c > 0) walls.add(new Wall(cell, r * side + (c - 1), r * 2 + 1, c * 2));
            }

        // Shuffle walls
        for (int i = walls.size() - 1; i > 0; i--) {
            int j = (int) Math.floor(rng.nextDouble() * (i + 1));
            Collections.swap(walls, i, j);
        }

        // Union-find setup
        int[] parent = new int[side * side];
        for (int i = 0; i < parent.length; i++) parent[i] = i;

        // Kruskal's algorithm
        for (Wall w : walls) {
            int aRoot = find(parent, w.a);
            int bRoot = find(parent, w.b);
            if (aRoot != bRoot) {
                union(parent, aRoot, bRoot);
                maze2D[w.wr][w.wc] = 2; // temporary open
            }
        }

        // Entrance and exit
        maze2D[side * 2][1] = 3;      // bottom-left entrance (temporary)
        maze2D[1][side * 2] = 3;      // top-right exit (temporary)

        // Map temporary values to actual tiles
        for(int r = 0; r < mazeRows; r++) {
            for(int c = 0; c < mazeCols; c++) {
                if(maze2D[r][c] == 0) {
                    maze2D[r][c] = tiles[0];
                } else if(maze2D[r][c] == 1) {
                    maze2D[r][c] = tiles[1];
                } else if(maze2D[r][c] == 2) {
                    maze2D[r][c] = tiles[2];
                } else if(maze2D[r][c] == 3) {
                    maze2D[r][c] = tiles[3];
                }
            }
        }

        return new WorldRegion(side, maze2D);
    }

    public int getSide() {
        return side;
    }
}
