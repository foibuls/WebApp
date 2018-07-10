package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/* class by David Robinson */

public class Generator {
    Random R;
    int width = 80;
    int height = 40;
    int x;
    int y;
    private Map<Long, Building> buildingMap = new HashMap<>();
    private long seedCount;
    private long seed;
    private int d = 4;
    private int nextLength;
    private int nextPlus;
    private int nextMinus;

    public int getXinit() {
        return xinit;
    }

    public int getYinit() {
        return yinit;
    }

    private String or;
    private String turn;
    private String[] turnarr = new String[]{"U", "D", "L", "R"};
    private int xinit;
    private int yinit;
    private boolean restart = false;

    //constructs a generator and picks a random starting position
    public Generator(long seed) {
        this.seedCount = seed;
        this.seed = seed;
        this.R = new Random(seedCount);
        x = RandomUtils.uniform(R, width / 3, 2 * width / 3);
        y = RandomUtils.uniform(R, height / 3, 2 * height / 3);
        xinit = x;
        yinit = y;
        or = "R";
    }
//Core method and algorithm to pseudorandomly create a world
    public void generate(TETile[][] world) {
        seedCount += 1;
        R.nextLong();
        d = RandomUtils.uniform(R, 1, 4);
        if (seedCount - seed > 500) {
            world[xinit][yinit] = Tileset.PLAYER;
            x = xinit;
            y = yinit;
            return;
        }
        nextMinus = RandomUtils.uniform(R, 1, 4);
        nextPlus = RandomUtils.uniform(R, 1, 5);
        nextLength = RandomUtils.uniform(R, 1, 10);
        try {
            this.Decide(world);
        } catch (ArrayIndexOutOfBoundsException exception) {
            //if (seedCount - seed < 20) {
            x = xinit - 1;
            y = yinit;
            or = "L";
            if (restart) {
                or = "D";
            }
            restart = true;
            System.out.println("c");
            if (seedCount - seed > 500) {
                world[xinit][yinit] = Tileset.PLAYER;
                return;
            }
        }
        generate(world);
    }
//picks which building to draw based on generated numbers and current orientation
    public void Decide(TETile[][] world) {
        switch (d) {
            case 1:
                Hall s = new Hall(nextLength, x, y, or);
                if (!checkIntersect(s)) {
                    buildingMap.put(seedCount - seed, s);
                    s.build(world);
                    switch (or) {
                        case "R":
                            x += nextLength;
                            break;
                        case "L":
                            x -= nextLength;
                            break;
                        case "U":
                            y += nextLength;
                            break;
                        case "D":
                            y -= nextLength;
                            break;
                    }
                }
                break;
            case 2:
                nextLength = Integer.max(1,nextLength * 2 / 3);
                Room t = new Room(nextLength, nextPlus, nextMinus, x, y, or);
                if (!checkIntersect(t)) {
                    buildingMap.put(seedCount - seed, t);
                    t.build(world);
                    switch (or) {
                        case "R":
                            x += nextLength + 1;
                            break;
                        case "L":
                            x -= nextLength + 1;
                            break;
                        case "U":
                            y += nextLength + 1;
                            break;
                        case "D":
                            y -= nextLength + 1;
                    }
                }
                break;

            case 3:
                if (or.equals("L") || or.equals("R")) {
                    turn = turnarr[RandomUtils.uniform(R, 0, 2)];
                } else if (or.equals("U") || or.equals("D")) {
                    turn = turnarr[RandomUtils.uniform(R, 2, 4)];
                }
                Corner u = new Corner(x, y, or, turn);
                if (!checkIntersect(u)) {
                    buildingMap.put(seedCount - seed, u);
                    u.build(world);
                    switch (turn) {
                        case "R":
                            x += 2;
                            break;
                        case "L":
                            x -= 2;
                            break;
                        case "U":
                            y += 2;
                            break;
                        case "D":
                            y -= 2;
                            break;
                    }
                    or = turn;
                }
        }
    }

    //ensures no building has been built where building b will be added
    public boolean checkIntersect(Building b) {
        boolean intersect = false;
        for (Building built : buildingMap.values()) {
            if (b.range.intersect(built.range)) {
                intersect = true;
            }
        }
        return intersect;
    }
//initializes the TETile array to black tiles
    public void initialize(TETile[][] world, int w, int h) {
        for (int i = 0; i < w; i += 1) {
            for (int j = 0; j < h; j += 1) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    public void move(char a, TETile[][] world) {
        int tempx = x;
        int tempy = y;
        switch (a) {
            case 'w':
                if (world[x][y + 1].description().equals("floor")) {
                    world[x][y + 1] = Tileset.PLAYER;
                    y += 1;
                }
                break;
            case 's':
                if (world[x][y - 1].description().equals("floor")) {
                    world[x][y - 1] = Tileset.PLAYER;
                    y -= 1;
                }
                break;
            case 'd':
                if (world[x + 1][y].description().equals("floor")) {
                    world[x + 1][y] = Tileset.PLAYER;
                    x += 1;
                }
                break;
            case 'a':
                if (world[x - 1][y].description().equals("floor")) {
                    world[x - 1][y] = Tileset.PLAYER;
                    x -= 1;
                }
                break;
        }
        if (x != tempx || y != tempy) {
            world[tempx][tempy] = Tileset.FLOOR;
        }
    }

    public TETile[][] hide(int x, int y) {
        Range r = new Range(x, x, y, y);
        TETile[][] hidden = new TETile[width][height];
        initialize(hidden, width, height);
        for (Building built : buildingMap.values()) {
            if (r.intersect(built.range)) {
                built.build(hidden);
            }
        }
        hidden[x][y] = Tileset.PLAYER;
        return hidden;
    }
}

