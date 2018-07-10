package byog.Core;

import byog.TileEngine.TETile;

//Class by David Robinson
//Made as abstract class to allow extension with nonfinal instance variables
public abstract class Building {
    String orientation;
    Range range;
      int x;
      int y;

    public abstract void build(TETile[][] world);
}
