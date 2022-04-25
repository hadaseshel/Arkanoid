// ID: 206775074

/**
 * @author hadas eshel
 */

package collision;

// imports
import java.util.ArrayList;
import primitivesgeometry.Line;

/**
 * This class collect all the objects that a Ball can collide with in list.
 * there is method in class that can return the information about the collision point if there is one.
 */
public class GameEnvironment {
    // fields: list of objects that a Ball can collide with.
    private ArrayList<Collidable> listOfCollideAble;

    /**
     * This constructor method creates the game environment.
     */
    public GameEnvironment() {
        this.listOfCollideAble = new ArrayList<Collidable>();
    }

    /**
     * This method add the given collide able to the environment.
     * @param c the given collide able.
     */
    public void addCollidable(Collidable c) {
        this.listOfCollideAble.add(c);
    }

    /**
     * This method remove the given collide from the environment.
     * @param c the given sprite.
     */
    public void removeCollidable(Collidable c) {
        this.listOfCollideAble.remove(c);
    }

    /**
     * Object moving from line.start() to line.end().
     * If this object will not collide with any of the collide ables in this collection,this method return null.
     * Else, this method return the information about the closest collision that is going to occur.
     * @param trajectory the line moving of the object.
     * @return If this object will not collide with any of the collide ables in this collection,this method return null,
     * Else, this method return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        ArrayList<Collidable> tempList = new ArrayList<Collidable>(this.listOfCollideAble);
        // go over all the potential obstacles.
        for (int i = 0; i < tempList.size(); i++) {
            Collidable theBlock = (Collidable) tempList.get(i);
            // check if there is a collision.
            if (trajectory.closestIntersectionToStartOfLine(theBlock.getCollisionRectangle()) != null) {
                // create a collisonInfo.
                CollisionInfo collisonInfo = new CollisionInfo(trajectory.
                        closestIntersectionToStartOfLine(theBlock.getCollisionRectangle()),
                        (Collidable) tempList.get(i));
                // return thr collison infromation.
                return collisonInfo;
            }
        }
        // there is no collision, return null.
        return null;
    }

}