// ID: 206775074

/**
 * @author hadas eshel
 */

package sprites;

// imports
import biuoop.DrawSurface;

/**
 * This interface can be drawn on the screen, and can be notified that time has passed.
 * (so that they know to change their position / shape / appearance / etc).
 */
public interface Sprite {

    /**
     * This method draw the sprite to the screen on the given DrawSurface way.
     * @param d the given DrawSurface way.
     */
    void drawOn(DrawSurface d);

    /**
     * This method notify the sprite that time has passed..
     */
    void timePassed();
}