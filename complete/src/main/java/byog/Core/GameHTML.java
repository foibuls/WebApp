/* package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

import static java.lang.Long.parseLong;

public class GameHTML {
    package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;

import static java.lang.Long.parseLong;

    /* Class by David Robinson and Fady Nakhla */ /*

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
         *//*
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


        private void play() {
            while (!quit) {
        //       char c = greeeting
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

*/