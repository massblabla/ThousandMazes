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

package dev.massblabla.games.thousandmazes.config;

import java.io.InputStream;

import com.moandjiezana.toml.Toml;

/**
 * Config class for Toml4J to map.
 * 
 * @version 0.0.1-SNAPSHOT
 * @author massblabla
 */
public class Config {
	/* Instance accessor */
	public static Config instance = new Config();
	
	public static InputStream accessResourceFile(String file) {
		InputStream is = Config.class.getClassLoader().getResourceAsStream(file);
        if (is == null) {
            throw new IllegalArgumentException("Config file is not found!");
        }
        
        return is;
	}
	Toml toml = new Toml().read(accessResourceFile("config.toml"));
	public RequiresRestart requiresRestart = toml.getTable("requires_restart").to(RequiresRestart.class);
    public WorldDefault worldDefault = toml.getTable("world_default").to(WorldDefault.class);

	/* Variables, explanations in config.toml */

	/* [requires_restart]: Requires a restart. */
	public class RequiresRestart {
		private long defaultTileSize = toml.getLong("defaultTileSize");
		private double defaultRelativeScale = toml.getDouble("defaultRelativeScale");
		private boolean autoWindowSizing = toml.getBoolean("autoWindowSizing");
		private long totalDisplayedColumns = toml.getLong("totalDisplayedColumns");
		private long totalDisplayedRows = toml.getLong("totalDisplayedRows");

		/* Getters */
		public long getDefaultTileSize() { return defaultTileSize; }
		public double getDefaultRelativeScale() { return defaultRelativeScale; }
		public boolean getAutoWindowSizing() { return autoWindowSizing; }
		public long getTotalDisplayedColumns() { return totalDisplayedColumns; }
		public long getTotalDisplayedRows() { return totalDisplayedRows; }

		/* Setters */
		public void setDefaultTileSize(long in) { defaultTileSize = in;	}
		public void setDefaultRelativeScale(double in) { defaultRelativeScale = in; }
		public void setAutoWindowSizing(boolean in) { autoWindowSizing = in; }
		public void setTotalDisplayedColumns(long in) { totalDisplayedColumns = in; }
		public void setTotalDisplayedRows(long in) { totalDisplayedRows = in; }
	}

    /* [world_default]: The default options when world creation. */
    public class WorldDefault {
        private long worldLevels = toml.getLong("worldLevels");

        /* Getters */
        public long getWorldLevels() { return worldLevels; }

        /* Setters */
        public void setWorldLevels(long in) { worldLevels = in; }
    }
}
