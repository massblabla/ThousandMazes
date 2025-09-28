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
import dev.massblabla.utils.worldregion.WorldGenerator;
import dev.massblabla.utils.worldregion.WorldRegion;

import java.util.*;

/**
 * Generates mazes and saves it.
 *
 * @version 0.0.1-SNAPSHOT
 * @author massblabla
 */
public class MazeGenerator implements WorldGenerator {
    private long seed;
    private int side;

    public MazeGenerator(WorldDifficulties difficulty, long seed) {
        switch(difficulty) {
            case WorldDifficulties.STARTER -> side = 10;
            case WorldDifficulties.EASY -> side = 15;
            case WorldDifficulties.MEDIUM -> side = 20;
            case WorldDifficulties.INTERMEDIATE -> side = 30;
            case WorldDifficulties.HARD -> side = 40;
            case WorldDifficulties.VERY_HARD -> side = 50;
            case WorldDifficulties.INSANE -> side = 70;
            case WorldDifficulties.EXTREME -> side = 100;
            case WorldDifficulties.HARDCORE -> side = 150;
            default -> throw new IllegalArgumentException("Invalid world difficulty.");
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
        int rows = side;
        int cols = side;
        Mulberry32 rng = new Mulberry32(seed); // fixed seed

        int mazeRows = rows * 2 + 1;
        int mazeCols = cols * 2 + 1;
        byte[][] maze2D = new byte[mazeRows][mazeCols];
        for (byte[] row : maze2D) Arrays.fill(row, (byte)1);

        // open cells
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                maze2D[r * 2 + 1][c * 2 + 1] = 0;

        // walls
        class Wall { int a, b, wr, wc; Wall(int a, int b, int wr, int wc) { this.a=a; this.b=b; this.wr=wr; this.wc=wc; } }
        List<Wall> walls = new ArrayList<>();
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                int cell = r * cols + c;
                if (r > 0) walls.add(new Wall(cell, (r-1)*cols+c, r*2, c*2+1));
                if (c > 0) walls.add(new Wall(cell, r*cols+(c-1), r*2+1, c*2));
            }

        // shuffle walls
        for (int i = walls.size() - 1; i > 0; i--) {
            int j = (int) Math.floor(rng.nextDouble()*(i+1));
            Collections.swap(walls, i, j);
        }

        // Kruskal
        int[] parent = new int[mazeRows * mazeCols];
        for (int i = 0; i < parent.length; i++) parent[i] = i;

        for (Wall w : walls) {
            int a = find(parent, w.a);
            int b = find(parent, w.b);
            if (a != b) {
                union(parent, a, b);
                maze2D[w.wr][w.wc] = 0;
            }
        }

        // entrance/exit
        maze2D[rows*2][1] = 0;      // bottom-left
        maze2D[1][cols*2] = 0;      // top-right

        // Flatten to 1D
        byte[] maze1D = new byte[mazeRows * mazeCols];
        for (int r = 0; r < mazeRows; r++)
            System.arraycopy(maze2D[r], 0, maze1D, r * mazeCols, mazeCols);

        return new WorldRegion(side, maze1D);
    }

    public int getSide() {
        return side;
    }
}
