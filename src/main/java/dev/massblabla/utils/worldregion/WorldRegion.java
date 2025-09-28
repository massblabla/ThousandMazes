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

package dev.massblabla.utils.worldregion;

import java.io.*;
import java.util.Arrays;

/**
 * WorldRegion (.dat) is a world format for 2D multi-square level world games that tries to have a small file size.
 * WorldRegion files contain this in the following:
 * Header, which contains the magic number (0xABC3DE), followed immediately by the magic number, which is derived from
 * the version number (now, 0.0.1), and then calculated using this scheme: 2-3-3 (2 bits for major, 3 bits for minor,
 * and 3 bits for patch), with the maximum being 3.7.7. The number for 0.0.1 is 0x01. Then it is followed by 4 null
 * bytes, and then by ASCII S followed by the 3-number side length, for example S001 (0x53303031).
 * Example: AB C3 DE 01 00 00 00 00 00 00 00 00 53 30 30 31
 * That is a world using WorldRegion version 0.0.1, with the side length of 1 tile.
 * Then there is data, which contains the entire world data. 1 tile takes 8 bits, which is at maximum 256 tiles.
 *
 * @version 0.0.1-SNAPSHOT
 * @author massblabla
 */
public class WorldRegion {
    private static final int MAGIC = 0xABC3DE;
    private static final byte VERSION = 0x01; // 0.0.1 (2-3-3 scheme collapsed into one byte)
    private static final int HEADER_NULLS = 8;

    private int side;
    private byte[] tiles; // 1 byte = 1 tile

    public WorldRegion(int side, byte[] tiles) {
        this.side = side;
        this.tiles = tiles;
    }

    // ====== Save ======
    public void save(String path) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(path))) {
            // Magic number (3 bytes)
            out.writeByte((MAGIC >> 16) & 0xFF);
            out.writeByte((MAGIC >> 8) & 0xFF);
            out.writeByte(MAGIC & 0xFF);

            // Version (1 byte)
            out.writeByte(VERSION);

            // 8 null bytes
            for (int i = 0; i < HEADER_NULLS; i++) out.writeByte(0);

            // S### (ASCII)
            out.writeBytes(String.format("S%03d", side));

            // ====== Tile data (1 byte each) ======
            out.write(tiles);
        }
    }

    private static String versioner(byte version) {
        int major = (version >> 6) & 0b11;
        int minor = (version >> 3) & 0b111;
        int patch = (version) & 0b111;

        return major + "." + minor + "." + patch;
    }

    // ====== Load ======
    public static WorldRegion load(String path) throws IOException {
        try (DataInputStream in = new DataInputStream(new FileInputStream(path))) {
            // Magic
            int magic = ((in.readUnsignedByte() << 16) |
                    (in.readUnsignedByte() << 8) |
                    in.readUnsignedByte());
            if (magic != MAGIC) throw new IOException("Invalid magic number");

            // Version
            byte version = in.readByte();
            if (version != VERSION) throw new IOException("Detected version " + versioner(version) + ", wanted " + versioner(VERSION));

            // Skip 8 null bytes
            in.skipBytes(HEADER_NULLS);

            // Side length
            String sStr = "" + (char) in.readByte() + (char) in.readByte() + (char) in.readByte() + (char) in.readByte();

            int side = Integer.parseInt(sStr.substring(1));

            // Read tiles
            int tileCount = side * side;
            byte[] tiles = new byte[tileCount];
            in.readFully(tiles);

            return new WorldRegion(side, tiles);
        }
    }

    @Override
    public String toString() {
        return "WorldRegion(" + side + ", tiles=" + Arrays.toString(tiles) + ")";
    }
    public int getSide() { return side; }
    public byte[] getTiles() { return tiles; }
}