package byog.Core;

import byog.TileEngine.TETile;

public class Converter {
    public char[][] convert(TETile[][] world) {
        char[][] newWorld = new char[world.length][world[0].length];
        for (int i = 0; i < world.length; i++){
            for (int j = 0; j < world[0].length; j++){
                newWorld[i][j] = world[i][j].character();
            }
        }
        return newWorld;
    }
}
