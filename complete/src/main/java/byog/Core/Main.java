package byog.Core;

/**
 * This is the main entry point for the program. This class simply parses
 * the command line inputs, and lets the byog.Core.Game class take over
 * in either keyboard or input string mode.
 *
 * Run this with no arguments to begin, then use the keyboard to make selections.
 * Seeds are any number followed by the letter "s".
 */
public class Main {
    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println("Can only have one argument - the input string");
            System.exit(0);
        } else if (args.length == 1) {
            Game game = new Game();
            game.playWithInputString(args[0]);
            System.out.println(game.toString());
        } else {
            Game game = new Game();
            game.playWithKeyboard();
        }
    }
}
