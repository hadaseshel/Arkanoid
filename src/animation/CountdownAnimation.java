// ID: 206775074

/**
 * @author hadas eshel
 */

package animation;

// imports
import biuoop.DrawSurface;
import sprites.SpriteCollection;
import java.awt.Color;


/**
 * This class is will display the given gameScreen, for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will appear on the screen for
 * (numOfSeconds / countFrom) seconds, before it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;

    /**
     * This class hold the sprites and the collidables, and will be in charge of the animation.
     * @param numOfSeconds .
     * @param countFrom .
     * @param gameScreen .
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.red);
        d.drawText(d.getWidth() / 4,  4 * d.getHeight() / 7, "The game will starts in: " + this.countFrom, 32);
        this.countFrom = this.countFrom - 1;
    }
    @Override
    public boolean shouldStop() {
        if (this.countFrom == 0) {
            return true;
        }
        return false;
    }
}