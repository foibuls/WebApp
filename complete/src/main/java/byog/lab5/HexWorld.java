package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    static TETile[][] world = new TETile[100][100];
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(100, 100);
        for (int x = 0; x < 100; x += 1) {
            for (int y = 0; y < 100; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        addHexagon(5, 30, 40);
        addHexagon(5, 40, 40);
        addHexagon(5, 50, 40);
        addHexagon(5, 60, 40);
        addHexagon(5, 30, 50);
        addHexagon(5, 40, 50);
        addHexagon(5, 50, 50);
        addHexagon(5, 60, 50);
        addHexagon(5, 40, 60);
        addHexagon(5, 50, 60);
        addHexagon(5, 40, 30);
        addHexagon(5, 50, 30);


        ter.renderFrame(world);
    }

    //draws a hexagon of given size
    public static void addHexagon(int s, int x, int y) {
        for (int i = 0; i < 2 * s; i++) {
            fill(x, y, i, s);
        }
    }

    public int calculator(int s) {
        return 0;
        /*row 1 has width s
         if (x <= s) {row x has width s + 2x)}
         if (x > s) { row x has width 2(s-x)}
                    */
    }

    public static void fill(int x, int y, int i, int s) {
        if (i < s) {
            for (int j = 0; j < s + 2*i; j += 1) {
                world[x+j-i][y + i] = Tileset.WALL;
            }
        } else if (i >= s) {
            for (int j = 0; j <= 3*s-3-2*(i-s); j += 1) {
                world[x+j-2*s+i+1][y + i] = Tileset.WALL;
            }
        }
    }

}
