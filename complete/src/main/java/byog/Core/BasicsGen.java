package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/* Antiquated file. Code no longer in use, but shows original 
(non-object-oriented) approach to solution. */

public class BasicsGen {
    //draws 16 basic shapes (4 shapes of 4 orientations)

    public void hallR(int length, int x, int y, TETile[][] world) {
        for (int i = 0; i < length; i++) {
            world[x + i][y + 1] = Tileset.WALL;
            world[x + i][y] = Tileset.FLOOR;
            world[x + i][y - 1] = Tileset.WALL;
        }
    }

    public void hallL(int length, int x, int y, TETile[][] world) {
        for (int i = 0; i < length; i++) {
            world[x - i][y + 1] = Tileset.WALL;
            world[x - i][y] = Tileset.FLOOR;
            world[x - i][y - 1] = Tileset.WALL;
        }
    }

    public void hallU(int length, int x, int y, TETile[][] world) {
        for (int i = 0; i < length; i++) {
            world[x + 1][y + i] = Tileset.WALL;
            world[x][y + i] = Tileset.FLOOR;
            world[x - 1][y + i] = Tileset.WALL;
        }
    }

    public void hallD(int length, int x, int y, TETile[][] world) {
        for (int i = 0; i < length; i++) {
            world[x + 1][y - i] = Tileset.WALL;
            world[x][y - i] = Tileset.FLOOR;
            world[x - 1][y - i] = Tileset.WALL;
        }
    }

    public void roomR(int width, int yadd, int ydown, int x, int y, TETile[][] world) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j <= yadd + ydown; j++) {
                world[x + i][y - ydown + j] = Tileset.FLOOR;
            }
        }
        for (int i = 0; i <= width + 1; i++) {
            world[x - 1 + i][y + yadd + 1] = Tileset.WALL;
            world[x - 1 + i][y - ydown - 1] = Tileset.WALL;
        }
        for (int i = 0; i <= yadd + ydown; i++) {
            world[x - 1][y - ydown + i] = Tileset.WALL;
            world[x + width][y - ydown + i] = Tileset.WALL;
        }
        world[x - 1][y] = Tileset.FLOOR;
    }

    public void roomL(int width, int yadd, int ydown, int x, int y, TETile[][] world) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j <= yadd + ydown; j++) {
                world[x - i][y - ydown + j] = Tileset.FLOOR;
            }
        }
        for (int i = 0; i <= width + 1; i++) {
            world[x + 1 - i][y + yadd + 1] = Tileset.WALL;
            world[x + 1 - i][y - ydown - 1] = Tileset.WALL;
        }
        for (int i = 0; i <= yadd + ydown; i++) {
            world[x + 1][y - ydown + i] = Tileset.WALL;
            world[x - width][y - ydown + i] = Tileset.WALL;
        }
        world[x + 1][y] = Tileset.FLOOR;
    }

    public void roomU(int height, int xadd, int xdown, int x, int y, TETile[][] world) {
        for (int i = 0; i < xadd + xdown + 1; i++) {
            for (int j = 0; j < height; j++) {
                world[x - xdown + i][y + j] = Tileset.FLOOR;
            }
        }
        for (int i = 0; i <= height + 1; i++) {
            world[x - xdown - 1][y - 1 + i] = Tileset.WALL;
            world[x + xadd + 1][y - 1 + i] = Tileset.WALL;
        }
        for (int i = 0; i <= xadd + xdown; i++) {
            world[x - xdown + i][y - 1] = Tileset.WALL;
            world[x - xdown + i][y + height] = Tileset.WALL;
        }
        world[x][y - 1] = Tileset.FLOOR;
    }

    public void roomD(int height, int xadd, int xdown, int x, int y, TETile[][] world) {
        for (int i = 0; i < xadd + xdown + 1; i++) {
            for (int j = 0; j < height; j++) {
                world[x - xdown + i][y - j] = Tileset.FLOOR;
            }
        }
        for (int i = 0; i <= height + 1; i++) {
            world[x - xdown - 1][y + 1 - i] = Tileset.WALL;
            world[x + xadd + 1][y + 1 - i] = Tileset.WALL;
        }
        for (int i = 0; i <= xadd + xdown; i++) {
            world[x - xdown + i][y + 1] = Tileset.WALL;
            world[x - xdown + i][y - height] = Tileset.WALL;
        }
        world[x][y + 1] = Tileset.FLOOR;
    }

    public void cornerRD(int x, int y, TETile[][] world) {
        world[x + 1][y] = Tileset.WALL;
        world[x + 1][y - 1] = Tileset.WALL;
        world[x + 1][y + 1] = Tileset.WALL;
        world[x][y + 1] = Tileset.WALL;
        world[x][y] = Tileset.FLOOR;
        world[x][y - 1] = Tileset.FLOOR;
    }

    public void cornerRU(int x, int y, TETile[][] world) {
        world[x + 1][y] = Tileset.WALL;
        world[x + 1][y - 1] = Tileset.WALL;
        world[x + 1][y + 1] = Tileset.WALL;
        world[x][y - 1] = Tileset.WALL;
        world[x][y] = Tileset.FLOOR;
        world[x][y + 1] = Tileset.FLOOR;
    }

    public void cornerLD(int x, int y, TETile[][] world) {
        world[x - 1][y] = Tileset.WALL;
        world[x - 1][y - 1] = Tileset.WALL;
        world[x - 1][y + 1] = Tileset.WALL;
        world[x][y + 1] = Tileset.WALL;
        world[x][y] = Tileset.FLOOR;
        world[x][y - 1] = Tileset.FLOOR;
    }

    public void cornerLU(int x, int y, TETile[][] world) {
        world[x - 1][y] = Tileset.WALL;
        world[x - 1][y - 1] = Tileset.WALL;
        world[x - 1][y + 1] = Tileset.WALL;
        world[x][y - 1] = Tileset.WALL;
        world[x][y] = Tileset.FLOOR;
        world[x][y + 1] = Tileset.FLOOR;
    }

    public void cornerDR(int x, int y, TETile[][] world) {
        world[x][y - 1] = Tileset.WALL;
        world[x - 1][y - 1] = Tileset.WALL;
        world[x + 1][y - 1] = Tileset.WALL;
        world[x - 1][y] = Tileset.WALL;
        world[x][y] = Tileset.FLOOR;
        world[x + 1][y] = Tileset.FLOOR;
    }

    public void cornerDL(int x, int y, TETile[][] world) {
        world[x][y - 1] = Tileset.WALL;
        world[x - 1][y - 1] = Tileset.WALL;
        world[x + 1][y - 1] = Tileset.WALL;
        world[x + 1][y] = Tileset.WALL;
        world[x][y] = Tileset.FLOOR;
        world[x - 1][y] = Tileset.FLOOR;
    }

    public void cornerUR(int x, int y, TETile[][] world) {
        world[x][y + 1] = Tileset.WALL;
        world[x - 1][y + 1] = Tileset.WALL;
        world[x + 1][y + 1] = Tileset.WALL;
        world[x - 1][y] = Tileset.WALL;
        world[x][y] = Tileset.FLOOR;
        world[x + 1][y] = Tileset.FLOOR;
    }

    public void cornerUL(int x, int y, TETile[][] world) {
        world[x][y + 1] = Tileset.WALL;
        world[x - 1][y + 1] = Tileset.WALL;
        world[x + 1][y + 1] = Tileset.WALL;
        world[x + 1][y] = Tileset.WALL;
        world[x][y] = Tileset.FLOOR;
        world[x - 1][y] = Tileset.FLOOR;
    }
}
