// ID: 206775074

/**
 * @author hadas eshel
 */

package sprites;

// imports
import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * This class hold a collection of sprites,
 * and can Instruct class to act according to the implementation of the interface method.
 */
public class SpriteCollection {

    // fields: list of objects that are sprites.
    private ArrayList<Sprite> listOfSprit;

    /**
     * This constructor method creates the sprite collection.
     */
    public SpriteCollection() {
        this.listOfSprit = new ArrayList<Sprite>();
    }

    /**
     * This method add the given sprite to the sprite collection.
     * @param s the given sprite.
     */
    public void addSprite(Sprite s) {
        this.listOfSprit.add(s);
    }

    /**
     * This method remove the given sprite from the sprite collection.
     * @param s the given sprite.
     */
    public void removeSprite(Sprite s) {
        this.listOfSprit.remove(s);
    }

    /**
     * This method call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> tempList = new ArrayList<Sprite>(this.listOfSprit);
        for (int i = 0; i < tempList.size(); i++) {
            Sprite s = (Sprite) tempList.get(i);
            s.timePassed();
        }
    }

    /**
     * This method call drawOn(d) on all sprites.
     * @param d the given DrawSurface way.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.listOfSprit.size(); i++) {
            Sprite s = (Sprite) this.listOfSprit.get(i);
            s.drawOn(d);
        }
    }
}