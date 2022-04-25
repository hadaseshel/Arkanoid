// ID: 206775074

/**
 * @author hadas eshel
 */

package animation;

//imports
import biuoop.DrawSurface;

/**
 * This interface hold the sprites and the collidables, and will be in charge of the animation.
 */
public interface Animation {
    /**
     * This method is in charge of the logic of the draw on the frames.
     * @param d the given permission of draw.
     */
    void doOneFrame(DrawSurface d);
    /**
     * This method shouldStop() is in charge of stopping condition.
     * @return true if we need to stop the loop, false if we the loop need to continue.
     */
    boolean shouldStop();
}