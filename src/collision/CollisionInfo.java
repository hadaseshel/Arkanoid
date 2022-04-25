// ID: 206775074

/**
 * @author hadas eshel
 */

package collision;

// imports
import primitivesgeometry.Point;

/**
 * This class have information about the collision point, the value of the collision Point and
 * the collide able object involved in the collision.
 */
public class CollisionInfo {
    //fields
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * This constructor method creates the CollisionInfo class.
     * @param collisionObject the object that will be collide with.
     * @param collisionPoint the collision point.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }

    /**
     * This method return the point at which the collision occurs.
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * This method return collidable object involved in the collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}