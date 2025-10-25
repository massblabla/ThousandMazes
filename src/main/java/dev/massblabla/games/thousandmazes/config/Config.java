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

package dev.massblabla.games.thousandmazes.config;

import java.io.InputStream;

import com.moandjiezana.toml.Toml;

/**
 * Config class for Toml4J to map.
 * 
 * @version 0.2.0-SNAPSHOT
 * @author massblabla
 */
public class Config {
	/* Instance accessor */
	public static final Config instance = new Config();
	
	public static InputStream accessResourceFile(String file) {
		InputStream is = Config.class.getClassLoader().getResourceAsStream(file);
        if (is == null) {
            throw new IllegalArgumentException("Config file is not found!");
        }
        
        return is;
	}
	final Toml toml = new Toml().read(accessResourceFile("config.toml"));
	public final RequiresRestart requiresRestart = toml.getTable("requires_restart").to(RequiresRestart.class);
    public final WorldDefault worldDefault = toml.getTable("world_default").to(WorldDefault.class);

	/* Variables, explanations in config.toml */

	/* [requires_restart]: Requires a restart. */
	public class RequiresRestart {
		private int defaultTileSize = Math.toIntExact(toml.getLong("defaultTileSize"));
		private double defaultRelativeScale = toml.getDouble("defaultRelativeScale");
		private boolean autoWindowSizing = toml.getBoolean("autoWindowSizing");
		private int totalDisplayedColumns = Math.toIntExact(toml.getLong("totalDisplayedColumns"));
		private int totalDisplayedRows = Math.toIntExact(toml.getLong("totalDisplayedRows"));

		/* Getters */
		public int getDefaultTileSize() { return defaultTileSize; }
		public double getDefaultRelativeScale() { return defaultRelativeScale; }
		public boolean getAutoWindowSizing() { return autoWindowSizing; }
		public int getTotalDisplayedColumns() { return totalDisplayedColumns; }
		public int getTotalDisplayedRows() { return totalDisplayedRows; }

		/* Setters */
		public void setDefaultTileSize(int in) { defaultTileSize = in;	}
		public void setDefaultRelativeScale(double in) { defaultRelativeScale = in; }
		public void setAutoWindowSizing(boolean in) { autoWindowSizing = in; }
		public void setTotalDisplayedColumns(int in) { totalDisplayedColumns = in; }
		public void setTotalDisplayedRows(int in) { totalDisplayedRows = in; }
	}

    /* [world_default]: The default options when world creation. */
    public class WorldDefault {
        private int worldLevels = Math.toIntExact(toml.getLong("worldLevels"));

        /* Getters */
        public long getWorldLevels() { return worldLevels; }

        /* Setters */
        public void setWorldLevels(int in) { worldLevels = in; }
    }
}
