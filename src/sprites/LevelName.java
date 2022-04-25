// ID: 206775074

/**
 * @author hadas eshel
 */

package sprites;

// imports
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * This class is in charge of update the score counter.
 */
public class LevelName implements Sprite {
    private String level;

    /**
     * This constructor method creates the LevelName object.
     * @param l level of the play.
     */
    public LevelName(String l) {
        this.level = l;
    }

   @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawText(570, 18, "Level Name: " + this.level, 18);
    }

    @Override
    public void timePassed() {
        // nathing.
    }
}