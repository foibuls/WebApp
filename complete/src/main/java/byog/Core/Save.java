package byog.Core;

import byog.TileEngine.TETile;

public class Save implements java.io.Serializable {
    int x;
    int y;
    TETile[][] world;

    public Save(int x, int y, TETile[][] world) {
        this.x = x;
        this.y = y;
        this.world = world;
    }
}

