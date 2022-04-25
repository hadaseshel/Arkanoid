// ID: 206775074

/**
 * @author hadas eshel
 */

package game;

// import
import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import auxiliary.Counter;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * This class in charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private Counter score = new Counter();

    /**
     * This constructor in charge of creating the object.
     * @param ar the AnimationRunner.
     * @param ks the Input write permission.
     * @param gui the screen.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
    }

    /**
     * This in charge of creating the different levels, and moving from one level to the next.
     * @param levels the leveles.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean noMoreBalls = false;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, this.score, this.gui);
            level.initialize();
            while (level.getCounterOfBalls().getValue() != 0 && level.getCounterOfBlock().getValue() != 0) {
                level.run();
            }
            if (level.getCounterOfBalls().getValue() == 0) {
                noMoreBalls = true;
                break;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(this.score, noMoreBalls)));
        this.gui.close();
    }
}