// ID: 206775074

/**
 * @author hadas eshel
 */

package collision;

// imports
import primitivesgeometry.Point;
import primitivesgeometry.Rectangle;
import sprites.Velocity;
import sprites.Ball;

/**
 * This interface will be used by things that can be collided with.
 */
public interface Collidable {
    /**
     * This method return the "collision shape" of the object.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * This method return the new velocity expected after the object hit the block.
     * (based on the force the object inflicted on us)
     * @param collisionPoint the collision Point of the object with the block.
     * @param currentVelocity the given velocity of object with the collision Point.
     * @param hitter the given velocity of object with the collision Point.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}