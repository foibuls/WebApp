package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;

import static java.lang.Long.parseLong;

/* Class by David Robinson and Fady Nakhla */

public class Game {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    String keyinput = "";
    TERenderer ter = new TERenderer();
    TETile[][] world = new TETile[WIDTH][HEIGHT];
    Serializer ser = new Serializer();
    Save s;
    Menu m;
    Generator g;
    boolean run = false;
    boolean done = false;
    boolean quit = false;
    boolean hidden = false;

    /**
     * Method used for playing a game, controlled by keyboard.
     */
    public void playWithKeyboard() {
        m = new Menu(Color.white, Color.black, WIDTH, HEIGHT);
        m.homescreen();
        while (!run) {
            if (StdDraw.hasNextKeyTyped()) {
                char holder = StdDraw.nextKeyTyped();
                if (holder == 'n' || holder == 'N') {
                    m.seedPrompt();
                    run = true;
                    newGame();
                } else if (holder == 'h' || holder == 'H') {
                    m.seedPrompt();
                    run = true;
                    newHiddenGame();
                } else if (holder == 'l' || holder == 'L') {
                    run = true;
                    loadGame();
                }
                else if (holder == 'q' || holder == 'Q') {
                    System.exit(0);
                }
            }
        }
    }

    //helper called when a New Game is chosen from the menu
        private void newGame() {
            while (!done) {
                if (StdDraw.hasNextKeyTyped()) {
                    Character a = StdDraw.nextKeyTyped();
                    if (a != 's' && a != 'S') {
                        keyinput += Character.toString(a);
                    } else if (a == 's' || a == 'S') {
                        g = new Generator(parseLong(keyinput));
                        g.initialize(world, WIDTH, HEIGHT);
                        g.generate(world);
                        done = true;
                        ter.renderFrame(world);
                        play();
                    }
                }
            }
        }
        //helper called when a New Hidden Game is chosen from the menu
        private void newHiddenGame() {
            while (!done) {
                if (StdDraw.hasNextKeyTyped()) {
                    Character a = StdDraw.nextKeyTyped();
                    if (a != 's' && a != 'S') {
                        keyinput += Character.toString(a);
                    } else if (a == 's' || a == 'S') {
                        g = new Generator(parseLong(keyinput));
                        g.initialize(world, WIDTH, HEIGHT);
                        g.generate(world);
                        done = true;
                        hidden = true;
                        TETile[][] hidden = g.hide(g.x, g.y);
                        ter.renderFrame(hidden);
                        while (!quit) {
                            play();
                        }
                    }
                }
            }
        }
        //helper called when load game is chosen from the menu
            private void loadGame(){
                    s = ser.unserialize();
                    if (s.world != null) {
                        TETile[][] cat = s.world;
                        world = cat;
                        ter.renderFrame(world);
                        g = new Generator(1);
                        g.x = s.x;
                        g.y = s.y;
                        play();
                    }
                }



    /**
     * Method used for playing with command line arguments. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        world = new TETile[WIDTH][HEIGHT];
        if (input.startsWith("n") || input.startsWith("N")) {
            int a = input.indexOf("s");
            int b = input.indexOf("S");
            if (a > b && b > 0) {
                a = b;
            }
            String t = input.substring(1, a - 1);
            Generator g = new Generator(parseLong(t));
            g.initialize(world, WIDTH, HEIGHT);
            g.generate(world);
            if (!(a == input.length())) {
                t = input.substring(a + 1, input.length());
            } else {
                t = input.substring(a, a + 1);
            }
            for (int i = 0; i < t.length() - 1; i++) {
                g.move(t.charAt(i), world);
            }
            t = input.substring(input.length() - 2, input.length());
            if (t.equals(":q") || t.equals(":Q")) {
                s = new Save(g.x, g.y, world);
                ser.serialize(s);
            }
        } else if (input.startsWith("L") || input.startsWith("l")) {
            s = ser.unserialize();
            if (s.world != null) {
                world = s.world;
                Generator g = new Generator(1);
                g.x = s.x;
                g.y = s.y;
                String t = input.substring(1, input.length());
                for (int i = 0; i < t.length() - 1; i++) {
                    g.move(t.charAt(i), world);
                }
                t = input.substring(input.length() - 2, input.length());
                if (t.equals(":q") || t.equals(":Q")) {
                    s = new Save(g.x, g.y, world);
                    ser.serialize(s);
                }
            }
        }
        return world;
    }
//moves character and displays world
    private void play() {
        while (!quit) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                g.move(c, world);
                if (!hidden) {
                    ter.renderFrame(world);
                } else {
                    ter.renderFrame(g.hide(g.x,g.y));
                }
                if (c == ':') {
                    while (true) {
                        if (StdDraw.hasNextKeyTyped()) {
                            c = StdDraw.nextKeyTyped();
                            if (c == 'q' || c == 'Q') {
                                s = new Save(g.x, g.y, world);
                                ser.serialize(s);
                                System.out.println("World Saved");
                                System.exit(0);
                            } else {
                                System.exit(0);
                            }
                        }
                    }
                }
            }
        }
    }
}

