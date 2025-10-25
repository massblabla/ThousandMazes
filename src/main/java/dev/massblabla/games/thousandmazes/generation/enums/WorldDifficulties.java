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
package dev.massblabla.games.thousandmazes.generation.enums;

/**
 * Contains difficulties per world can use. If changed can lead to world corruption.
 *
 * @version 0.2.0-SNAPSHOT
 * @author massblabla
 */
public enum WorldDifficulties {
    STARTER, /* 21x21, 5% */
    EASY, /* 31x31, 8% */
    MEDIUM, /* 41x41, 12%, default */
    INTERMEDIATE, /* 61x61, 16% */
    HARD, /* 81x81, 20% */
    VERY_HARD, /* 101x101, 25% */
    INSANE, /* 141x141, 30% */
    EXTREME, /* 201x201, 35% */
    HARDCORE//, /* 301x301, 40%, one-life */
    //CUSTOM /* Customisable (on world generation) */
}
