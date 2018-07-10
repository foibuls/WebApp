package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class GenTest {
    static int WIDTH = 80;
    static int HEIGHT = 40;

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // Game g = new Game();
        // g.playWithInputString("n7685817615627686380s");
        ter.renderFrame(world);
        Generator g = new Generator(534);
        g.generate(world);
        ter.renderFrame(world);
    }
}

