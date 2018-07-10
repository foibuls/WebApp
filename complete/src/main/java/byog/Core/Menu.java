package byog.Core;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;

public class Menu {
    private final Color textColor;
    private final Color backgroundColor;
    private int width;
    private int height;

    public Menu(Color textColor, Color backgroundColor, int width, int height) {
        this.width = width;
        this.height = height;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.setPenColor(textColor);
        StdDraw.clear(backgroundColor);
        StdDraw.enableDoubleBuffering();
    }

    public void homescreen() {
        StdDraw.text(width / 2, 4 * height / 5, "CS 61B: THE GAME");
        Font font = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.text(width / 2, height / 2, "New Game (N)");
        StdDraw.text(width / 2, height / 2 + 2, "New Hidden Game (H)");
        StdDraw.text(width / 2, height / 2 - 2, "Load Game (L)");
        StdDraw.text(width / 2, height / 2 - 4, "Quit (Q)");
        StdDraw.show();
    }

    public void seedPrompt() {
        StdDraw.clear(backgroundColor);
        StdDraw.text(width / 2, height / 2, "Input Seed");
        StdDraw.show();
    }
}
