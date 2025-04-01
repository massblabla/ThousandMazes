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
package dev.massblabla.games.thousandmazes.generation.difficulties;

import dev.massblabla.games.thousandmazes.generation.enums.WorldDifficulties;

/**
 * Class for the Very Hard difficulty
 *
 * @version 0.0.1-SNAPSHOT
 * @author massblabla
 */
public class VeryHardDifficulty extends Difficulty {
    public VeryHardDifficulty() {
        mazeWidth = 200;
        mazeHeight = 200;
        monsterSpawningLevel = 0.3d;
        totalLives = -1;
        debugOnly = false;
        difficulty = WorldDifficulties.VERY_HARD;
    }
}
