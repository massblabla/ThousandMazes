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
    STARTER, /* 30x30, 5% */
    EASY, /* 50x50, 10% */
    MEDIUM, /* 70x70, 15%, default */
    INTERMEDIATE, /* 100x100, 20% */
    HARD, /* 150x150, 25% */
    VERY_HARD, /* 200x200, 30% */
    INSANE, /* 300x300, 35% */
    EXTREME, /* 500x500, 40% */
    HARDCORE, /* 750x750, 50%, one-life */
    DEBUG_MODE, /* Customisable, customisable (--debug only) */
    CUSTOM /* Customisable (on world generation) */
}
