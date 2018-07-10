package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/* Class by David Robinson */

public class Room extends Building {
    //building with both length and width
    private int width;
    private int plus;
    private int minus;

    public Room(int width, int plus, int minus, int x, int y, String or) {
        this.x = x;
        this.y = y;
        this.orientation = or;
        this.width = width;
        this.plus = plus;
        this.minus = minus;
        this.makeRange();
    }

    //master build method
    public void build(TETile[][] world) {
        switch (orientation) {
            case "U":
                buildU(world);
                break;
            case "D":
                buildD(world);
                break;
            case "R":
                buildR(world);
                break;
            case "L":
                buildL(world);
        }
    }
    //helper build methods are called based on room orientation
    //helpers draw individual tiles to given TETile array
    private void buildR(TETile[][] world) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j <= plus + minus; j++) {
                world[x + i][y - minus + j] = Tileset.FLOOR;
            }
        }
        for (int i = 0; i <= width + 1; i++) {
            world[x - 1 + i][y + plus + 1] = Tileset.WALL;
            world[x - 1 + i][y - minus - 1] = Tileset.WALL;
        }
        for (int i = 0; i <= plus + minus; i++) {
            world[x - 1][y - minus + i] = Tileset.WALL;
            world[x + width][y - minus + i] = Tileset.WALL;
        }
        world[x - 1][y] = Tileset.FLOOR;
        world[x + width][y] = Tileset.FLOOR;
    }

    private void buildL(TETile[][] world) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j <= plus + minus; j++) {
                world[x - i][y - minus + j] = Tileset.FLOOR;
            }
        }
        for (int i = 0; i <= width + 1; i++) {
            world[x + 1 - i][y + plus + 1] = Tileset.WALL;
            world[x + 1 - i][y - minus - 1] = Tileset.WALL;
        }
        for (int i = 0; i <= plus + minus; i++) {
            world[x + 1][y - minus + i] = Tileset.WALL;
            world[x - width][y - minus + i] = Tileset.WALL;
        }
        world[x + 1][y] = Tileset.FLOOR;
        world[x - width][y] = Tileset.FLOOR;
    }

    private void buildU(TETile[][] world) {
        for (int i = 0; i < plus + minus + 1; i++) {
            for (int j = 0; j < width; j++) {
                world[x - minus + i][y + j] = Tileset.FLOOR;
            }
        }
        for (int i = 0; i <= width + 1; i++) {
            world[x - minus - 1][y - 1 + i] = Tileset.WALL;
            world[x + plus + 1][y - 1 + i] = Tileset.WALL;
        }
        for (int i = 0; i <= plus + minus; i++) {
            world[x - minus + i][y - 1] = Tileset.WALL;
            world[x - minus + i][y + width] = Tileset.WALL;
        }
        world[x][y - 1] = Tileset.FLOOR;
        world[x][y + width] = Tileset.FLOOR;
    }

    private void buildD(TETile[][] world) {
        for (int i = 0; i < plus + minus + 1; i++) {
            for (int j = 0; j < width; j++) {
                world[x - minus + i][y - j] = Tileset.FLOOR;
            }
        }
        for (int i = 0; i <= width + 1; i++) {
            world[x - minus - 1][y + 1 - i] = Tileset.WALL;
            world[x + plus + 1][y + 1 - i] = Tileset.WALL;
        }
        for (int i = 0; i <= plus + minus; i++) {
            world[x - minus + i][y + 1] = Tileset.WALL;
            world[x - minus + i][y - width] = Tileset.WALL;
        }
        world[x][y + 1] = Tileset.FLOOR;
        world[x][y - width] = Tileset.FLOOR;
    }

    //sets the range (location data)
    private void makeRange() {
        range = new Range(x, x, y, y);
        switch (orientation) {
            case "U":
                range = new Range(x - minus,x + plus, y,y + width - 1);
                break;
            case "D":
                range = new Range(x - minus, x + plus, y,  y - width + 1);
                break;
            case "R":
                range = new Range(x, x + width -1, y-minus, y + plus);
                break;
            case "L":
                range = new Range(x, x- width + 1, y - minus, y  + plus);
        }
    }
}
