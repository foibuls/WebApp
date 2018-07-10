package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/* Class by David Robinson */
public class Hall extends Building {
    int width;
    public Hall(int width, int x, int y, String or) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.orientation = or;
        this.makeRange();
    }

    public void build(TETile[][] world) {
        switch (orientation) {
            case "D":
                buildD(world);
                break;
            case "U":
                buildU(world);
                break;
            case "R":
                buildR(world);
                break;
            case "L":
                buildL(world);
                break;
        }
    }

    public void buildD(TETile[][] world) {
        for (int i = 0; i < width; i++) {
            world[x + 1][y - i] = Tileset.WALL;
            world[x][y - i] = Tileset.FLOOR;
            world[x - 1][y - i] = Tileset.WALL;
        }
    }

    public void buildU(TETile[][] world) {
        for (int i = 0; i < width; i++) {
            world[x + 1][y + i] = Tileset.WALL;
            world[x][y + i] = Tileset.FLOOR;
            world[x - 1][y + i] = Tileset.WALL;
        }
    }

    public void buildR(TETile[][] world) {
        for (int i = 0; i < width; i++) {
            world[x + i][y + 1] = Tileset.WALL;
            world[x + i][y] = Tileset.FLOOR;
            world[x + i][y - 1] = Tileset.WALL;
        }
    }

    public void buildL(TETile[][] world) {
        for (int i = 0; i < width; i++) {
            world[x - i][y + 1] = Tileset.WALL;
            world[x - i][y] = Tileset.FLOOR;
            world[x - i][y - 1] = Tileset.WALL;
        }
    }

    private void makeRange() {
        range = new Range(x, x, y, y);
        switch (orientation) {
            case "U":
                range.y2 = y + width - 1;
                break;
            case "D":
                range.y2 = y - width + 1;
                break;
            case "L":
                range.x2 = x + width - 1;
                break;
            case "R":
                range.x2 = x - width + 1;
                break;
        }
    }
}
