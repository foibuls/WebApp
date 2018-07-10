package hello;

import byog.Core.Converter;
import byog.Core.Generator;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    private long seed = 10L;
    private int[] moveCounts = new int[4];
    Generator g = new Generator(seed);
    TETile[][] teWorld = new TETile[80][40];
    Converter charverter = new Converter();
    char[][] newWorld;
    int x;
    int y;


    public GreetingController() {
        g.initialize(teWorld, teWorld.length, teWorld[0].length);
        g.generate(teWorld);
        newWorld = charverter.convert(teWorld);
        x = g.getXinit();
        y = g.getYinit();
    }


    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "yellow") String name,
                           @RequestParam(name = "seed", required = false, defaultValue = "10") long seed,
            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("j", 2);
        switch (name) {
            case "bob1000":
                //move left
                if (newWorld[x - 1][y] == Tileset.FLOOR.character()) {
                    newWorld[x - 1][y] = Tileset.PLAYER.character();
                    newWorld[x][y] = Tileset.FLOOR.character();
                    x -= 1;
                }

                break;
            case "bob0100":
                if (newWorld[x + 1][y] == Tileset.FLOOR.character()) {
                    newWorld[x + 1][y] = Tileset.PLAYER.character();
                    newWorld[x][y] = Tileset.FLOOR.character();
                    x += 1;
                }
                break;
            case "bob0010":
                //move down
                if (newWorld[x][y - 1] == Tileset.FLOOR.character()) {
                    newWorld[x][y - 1] = Tileset.PLAYER.character();
                    newWorld[x][y] = Tileset.FLOOR.character();
                    y -= 1;
                }
                break;
            case "bob0001":
                if (newWorld[x][y + 1] == Tileset.FLOOR.character()) {
                    newWorld[x][y + 1] = Tileset.PLAYER.character();
                    newWorld[x][y] = Tileset.FLOOR.character();
                    y += 1;
                }
                break;
        }
        if (seed != this.seed) {
            g = new Generator(seed);
            g.initialize(teWorld, teWorld.length, teWorld[0].length);
            g.generate(teWorld);
            x = g.getXinit();
            y = g.getYinit();
            this.seed = seed;
            newWorld = charverter.convert(teWorld);
        }
        model.addAttribute("seed", seed);
        model.addAttribute("newWorld", newWorld);
        return"greeting";
}

}
