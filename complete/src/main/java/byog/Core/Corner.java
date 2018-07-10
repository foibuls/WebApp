package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/* Class by David Robinson */
public class Corner extends Building {

    /**
    Corners are the primary way to change the orientation
    or build direction while generating a world.
     **/

    String prev; //previous direction before turned by the corner

    public Corner(int x, int y, String prev, String or) {
        this.x = x;
        this.y = y;
        this.prev = prev;
        this.orientation = or;
        makeRange();
    }
    
    private void makeRange() {
        range = new Range(x, x, y, y);
        switch (orientation) {
            case "U":
                this.range.y2 += 1;
                break;
            case "D":
                this.range.y2 -= 1;
                break;
            case "R":
                this.range.x2 += 1;
                break;
            case "L":
                this.range.x2 -= 1;
        }
    }
    
    //master build method

    public void build(TETile[][] world) {
        switch (prev) {
            case "R":
                if (orientation.equals("D")) {
                    buildRD(world);
                } else if (orientation.equals("U")) {
                    buildRU(world);
                }
                break;
            case "L":
                if (orientation.equals("D")) {
                    buildLD(world);
                } else if (orientation.equals("U")) {
                    buildLU(world);
                }
                break;
            case "U":
                if (orientation.equals("R")) {
                    buildUR(world);
                } else if (orientation.equals("L")) {
                    buildUL(world);
                }
                break;
            case "D":
                if (orientation.equals("R")) {
                    buildDR(world);
                } else if (orientation.equals("L")) {
                    buildDL(world);
                }
                break;
        }
    }
    
    //build helpers, called depending on orientation

    public void buildRD(TETile[][] world) {
        world[x + 1][y] = Tileset.WALL;
        world[x + 1][y - 1] = Tileset.WALL;
        world[x + 1][y + 1] = Tileset.WALL;
        world[x][y + 1] = Tileset.WALL;
        world[x][y] = Tileset.FLOOR;
        world[x][y - 1] = Tileset.FLOOR;
    }

    public void buildRU(TETile[][] world) {
        world[x + 1][y] = Tileset.WALL;
        world[x + 1][y - 1] = Tileset.WALL;
        world[x + 1][y + 1] = Tileset.WALL;
        world[x][y - 1] = Tileset.WALL;
        world[x][y] = Tileset.FLOOR;
        world[x][y + 1] = Tileset.FLOOR;
    }

    public void buildLD(TETile[][] world) {
        world[x - 1][y] = Tileset.WALL;
        world[x - 1][y - 1] = Tileset.WALL;
        world[x - 1][y + 1] = Tileset.WALL;
        world[x][y + 1] = Tileset.WALL;
        world[x][y] = Tileset.FLOOR;
        world[x][y - 1] = Tileset.FLOOR;
    }

    public void buildLU(TETile[][] world) {
        world[x - 1][y] = Tileset.WALL;
        world[x - 1][y - 1] = Tileset.WALL;
        world[x - 1][y + 1] = Tileset.WALL;
        world[x][y - 1] = Tileset.WALL;
        world[x][y] = Tileset.FLOOR;
        world[x][y + 1] = Tileset.FLOOR;
    }

    public void buildDR(TETile[][] world) {
        world[x][y - 1] = Tileset.WALL;
        world[x - 1][y - 1] = Tileset.WALL;
        world[x + 1][y - 1] = Tileset.WALL;
        world[x - 1][y] = Tileset.WALL;
        world[x][y] = Tileset.FLOOR;
        world[x + 1][y] = Tileset.FLOOR;
    }

    public void buildDL(TETile[][] world) {
        world[x][y - 1] = Tileset.WALL;
        world[x - 1][y - 1] = Tileset.WALL;
        world[x + 1][y - 1] = Tileset.WALL;
        world[x + 1][y] = Tileset.WALL;
        world[x][y] = Tileset.FLOOR;
        world[x - 1][y] = Tileset.FLOOR;
    }

    public void buildUR(TETile[][] world) {
        world[x][y + 1] = Tileset.WALL;
        world[x - 1][y + 1] = Tileset.WALL;
        world[x + 1][y + 1] = Tileset.WALL;
        world[x - 1][y] = Tileset.WALL;
        world[x][y] = Tileset.FLOOR;
        world[x + 1][y] = Tileset.FLOOR;
    }

    public void buildUL(TETile[][] world) {
        world[x][y + 1] = Tileset.WALL;
        world[x - 1][y + 1] = Tileset.WALL;
        world[x + 1][y + 1] = Tileset.WALL;
        world[x + 1][y] = Tileset.WALL;
        world[x][y] = Tileset.FLOOR;
        world[x - 1][y] = Tileset.FLOOR;
    }
}

