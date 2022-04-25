// ID: 206775074

/**
 * @author hadas eshel
 */

package collision;

// imports.
import sprites.Block;
import sprites.Ball;

/**
 * This interface indicate that objects that implement it send notifications when they are being hit.
 */
public interface HitListener {
    /**
     * This method called whenever the beingHit object is hit..
     * @param beingHit the block that have bein hit.
     * @param hitter The hitter parameter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}