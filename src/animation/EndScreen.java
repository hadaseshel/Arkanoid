// ID: 206775074

/**
 * @author hadas eshel
 */

package animation;

//imports
import auxiliary.Counter;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * This class is in charge of pause the screen when the user wants.
 */
public class EndScreen implements Animation {
    private boolean thereNoBalls;
    private Counter score;

    /**
     * This constructor method creates the object of the PauseScreen.
     * @param b the vule if there is ball in game.
     * @param score the score of the game.
     */
    public EndScreen(Counter score, Boolean b) {
        this.score = score;
        this.thereNoBalls = b;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        if (this.thereNoBalls) {
            d.drawText(d.getWidth() * 2 / 8, d.getHeight() / 2,
                    "Game Over. Your score is " + this.score.getValue(), 32);
        } else {
            d.drawText(d.getWidth() * 2 / 8, d.getHeight() / 2,
                    "You Win! Your score is " + this.score.getValue(), 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return true;
    }
}