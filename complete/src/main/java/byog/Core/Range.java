package byog.Core;

//class by David Robinson
//stores location information for buildings and
//allows checks for intersection
public class Range {
    int x1;
    int x2;
    int y1;
    int y2;

    public Range(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    //returns true if range r intersects the current range
    public boolean intersect(Range r) {
        boolean inter = this.x1 >= r.x1 && this.x1 <= r.x2;
        boolean sect = this.x1 <= r.x1 && this.x1 >= r.x2;
        boolean inter1 = this.x2 >= r.x1 && this.x2 <= r.x2;
        boolean sect1 = this.x2 <= r.x1 && this.x2 >= r.x2;

        boolean intery = this.y1 >= r.y1 && this.y1 <= r.y2;
        boolean secty = this.y1 <= r.y1 && this.y1 > r.y2;
        boolean inter1y = this.y2 >= r.y1 && this.y2 <= r.y2;
        boolean sect1y = this.y2 <= r.y1 && this.y2 >= r.y2;

        return ((inter || sect || inter1 || sect1) && (intery || secty || inter1y || sect1y));
    }
}
