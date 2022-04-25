// ID: 206775074

/**
 * @author hadas eshel
 */

//imports
import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import game.Level4;
import game.Level3;
import game.Level2;
import game.Level1;
import game.LevelInformation;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a the main class which run the game.
 */
public class Ass6Game {
    static final String NAME_OF_WINDOW = "Arkanoid";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private GUI gui = new GUI(NAME_OF_WINDOW, WIDTH, HEIGHT);;
    private AnimationRunner runner = new AnimationRunner(this.gui);
    private KeyboardSensor keyboardSensor = this.gui.getKeyboardSensor();
    /**
     * This method run the game.
     * @param lev the levels.
     */
    public void run(List<LevelInformation> lev) {
        GameFlow gameFlow = new GameFlow(this.runner, this.keyboardSensor, this.gui);
        gameFlow.runLevels(lev);
        this.gui.close();
    }

    /**
     * This main method run the game.
     * @param args the arguments from the command line.
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        Ass6Game ass6Game = new Ass6Game();
        if (args.length == 0) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        } else {
            List<Integer> numOfLev = new ArrayList<Integer>();
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("1")) {
                    levels.add(new Level1());
                } else if (args[i].equals("2")) {
                    levels.add(new Level2());
                } else if (args[i].equals("3")) {
                    levels.add(new Level3());
                } else if (args[i].equals("4")) {
                    levels.add(new Level4());
                }
            }
            if (levels.size() == 0) {
                levels.add(new Level1());
                levels.add(new Level2());
                levels.add(new Level3());
                levels.add(new Level4());
            }
        }
        ass6Game.run(levels);
    }
}