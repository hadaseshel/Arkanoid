// ID: 206775074

/**
 * @author hadas eshel
 */

package animation;

//imports
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * This class is in charge of pause the screen when the user wants.
 */
public class PauseScreen implements Animation {

    /**
     * This constructor method creates the object of the PauseScreen.
     */
    /*
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
    }
    */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 5, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return true;
    }
}