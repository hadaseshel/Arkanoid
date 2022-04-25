// ID: 206775074

/**
 * @author hadas eshel
 */

package animation;

//imports
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * This class is in charge of run the animation in the game.
 */
public class AnimationRunner {
    // fields.
    private GUI gui;
    private int framesPerSecond = 60;
    private int millisecondsPerFrame;
    private Sleeper sleeper = new Sleeper();

    /**
     * This constructor method creates the animation runner.
     * @param gui the gui of the game.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
    }

    /**
     * This method set framesPerSecond the number of frames per second.
     * @param millisecondsPerFra the number of frames per second.
     */
    public void setMillisecondsPerFrame(int millisecondsPerFra) {
        this.millisecondsPerFrame = millisecondsPerFra;
    }

    /**
     * This method set framesPerSecond the number of frames per second.
     */
    public void setMillisecondsPerFrame() {
        // we want a smooth animations that displays 60 different frames in a second, if possible.
        this.millisecondsPerFrame = 1000 / this.framesPerSecond;
    }

    /**
     * This methodis in charge of run the animation in the game..
     * @param animation the given animation details.
     */
    public void run(Animation animation) {
        // check if the animation should stop.
        while (!animation.shouldStop()) {
            // timing
            long startTime = System.currentTimeMillis();
            // get drawing permission.
            DrawSurface d = gui.getDrawSurface();

            // draw all the objects of sprites.
            animation.doOneFrame(d);

            gui.show(d);

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = this.millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}