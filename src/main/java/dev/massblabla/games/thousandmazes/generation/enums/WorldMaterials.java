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
 * Contains materials per world can use. If changed can lead to world corruption.
 *
 * @version 0.2.0-SNAPSHOT
 * @author massblabla
 */
public enum WorldMaterials {
    DEBUGMD, /* Customisable (--debug only) */
    EARTHLY, /* Uses #thousandmazes:earthly materials, default */
    HELLISH, /* Uses #thousandmazes:hellish materials */
    METALLIC, /* Uses #thousandmazes:metallic materials */
    DUNGEON, /* Uses #thousandmazes:dungeon materials */
    COLORED//, /* Uses #thousandmazes:colored materials */
    //CUSTOM /* Customisable (on world generation) */
}
