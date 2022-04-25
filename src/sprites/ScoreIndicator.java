// ID: 206775074

/**
 * @author hadas eshel
 */

package sprites;

//imports
import auxiliary.Counter;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * This class is in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * This constructor method creates the ScoreIndicator object.
     * @param score the score of the game we play.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawText(360, 18, "Score: " + this.score.getValue(), 18);
    }

    @Override
    public void timePassed() {
        //empty
    }
}