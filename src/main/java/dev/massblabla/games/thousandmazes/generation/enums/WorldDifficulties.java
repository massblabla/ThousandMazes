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
package dev.massblabla.games.thousandmazes.generation.enums;

/**
 * Contains difficulties per world can use. If changed can lead to world corruption.
 *
 * @version 0.0.1-SNAPSHOT
 * @author massblabla
 */
public enum WorldDifficulties {
    STARTER, /* 10x10, 5% */
    EASY, /* 15x15, 8% */
    MEDIUM, /* 20x20, 12%, default */
    INTERMEDIATE, /* 30x30, 16% */
    HARD, /* 40x40, 20% */
    VERY_HARD, /* 50x50, 25% */
    INSANE, /* 70x70, 30% */
    EXTREME, /* 100x100, 35% */
    HARDCORE//, /* 150x150, 40%, one-life */
    //CUSTOM /* Customisable (on world generation) */
}
