# ThousandMazes
### Version 0.2.0-SNAPSHOT
**Copyright (C) 2025 massblabla**  
A Free (libre) game that features 1,024 randomly-generated mazes that get harder every level.
  
## Licensing
ThousandMazes is licensed under the [GNU General Public License v3](https://www.gnu.org/licenses/gpl-3.0.en.html) or later.

## Special Thanks & Libraries Used
Special Thanks:
* [RyiSnow](https://youtube.com/@RyiSnow) for the [tutorial](https://youtube.com/playlist?list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq) :)

Libraries Used:
* [Toml4j](https://github.com/mwanji/toml4j) (Licensed [MIT](https://mit-license.org/)),  for TOMLs

## Changelog
### [Pre-releases](https://github.com/massblabla/ThousandMazes/releases/tag/v0.x.x)
[0.2.0-SNAPSHOT](https://github.com/massblabla/ThousandMazes/commit/) - 25 October 2025
- Additions
- - `keybind.toml` for keybinds instead of it being hard-coded
- - Add a tile checker
- - Prepare objects
- Modifications
- - Resize player's hitbox from 12x12 to 8x8
- - Change entrance/exit tiles texture
- - Use WorldRegion 0.0.2-SNAPSHOT
- - Maze generation logic for some reason
- - Use `WorldMaterials.DEBUGMD` instead of `WorldMaterials.EARTHLY` for now
- - Use `int` instead of `long` in config variables
- - Outside map boundaries now have collision instead of crashing
- - Scale tile directly from `TileMapHandler` instead of classes manually handling the scaling
- - Resize world size per difficulty again
- - Rename and change stellar textures into metallic textures
- - Retexture colored tiles
- Deletions

[0.1.0-SNAPSHOT](https://github.com/massblabla/ThousandMazes/commit/fc3ba1e843a9188d55367ba8014a7f22d3e5389d) - 5 October 2025
- Additions
- - Tiles and textures corresponding to each material
- - Maze generation logic (Kruskal's)
- - Collisions
- - Difficulties and materials
- - Use WorldRegion 0.0.1-SNAPSHOT
- - WorldDefault class in Config
- Modifications
- - Center player into window
- - Resize world size per difficulty
- Deletions
- - Difficulty and material classes