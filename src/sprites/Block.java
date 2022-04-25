// ID: 206775074

/**
 * @author hadas eshel
 */

package sprites;

// imports
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import game.GameLevel;
import primitivesgeometry.Rectangle;
import primitivesgeometry.Point;
import collision.Collidable;
import collision.HitNotifier;
import collision.HitListener;

/**
 * This class implements the collidable interface.
 * the block is actually a rectangle.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // fields: the rectangle block and color.
    private Rectangle theCollisionRectangle;
    private java.awt.Color color;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();

    /**
     * This constructor method creates the rectangle of the block.
     * @param rec the rectangle.
     */
    public Block(Rectangle rec) {
        this.theCollisionRectangle = rec;
    }

    /**
     * This method put color in the block.
     * @param theColor the wanted color/
     */
    public void setColor(Color theColor) {
        this.color = theColor;
    }

    /**
     * This method return the color of the block.
     * @return the color of the block.
     */
    public Color getColor() {
        return this.color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.theCollisionRectangle;
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
    this.hitListeners.remove(hl);
    }


    /**
     * this method notify all of the registered HitListener objects by calling their hitEvent method..
     * @param hitter the bal that hit block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity hit = currentVelocity;
        // division into cases: which edge the element get in collision.
        if (this.theCollisionRectangle.getUpperWidth().isThePointInLine(collisionPoint)) {
            hit = new Velocity(hit.getDx(), Math.abs(hit.getDy()));
        } else if (this.theCollisionRectangle.getLowWidth().isThePointInLine(collisionPoint)) {
            hit = new Velocity(hit.getDx(), -Math.abs(hit.getDy()));
        }
        if (this.theCollisionRectangle.getRightHeigt().isThePointInLine(collisionPoint)) {
            hit = new Velocity(-Math.abs(hit.getDx()), hit.getDy());
        } else if (this.theCollisionRectangle.getLeftHeigt().isThePointInLine(collisionPoint)) {
            hit = new Velocity(Math.abs(hit.getDx()), hit.getDy());
        }
        this.notifyHit(hitter);
        // return the hit.
        return hit;
    }

   @Override
    public void drawOn(DrawSurface surface) {
        /*
        This method draw the block on the given DrawSurface way.
         */
        surface.setColor(Color.BLACK);
       // draw the frame of the block.
        surface.drawRectangle((int) this.theCollisionRectangle.getUpperLeft().getX() - 1,
                (int) this.theCollisionRectangle.getUpperLeft().getY() - 1,
                (int) this.theCollisionRectangle.getWidth() + 1,
                (int) this.theCollisionRectangle.getHeight() + 1);
        // draw the fill of the block.
       surface.setColor(this.getColor());
        surface.fillRectangle((int) this.theCollisionRectangle.getUpperLeft().getX(),
                (int) this.theCollisionRectangle.getUpperLeft().getY(), (int) this.theCollisionRectangle.getWidth(),
                (int) this.theCollisionRectangle.getHeight());
    }
    @Override
    public void timePassed() {
        // nathings
    }

    /**
     * this method add the block to the game.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * this method remove the block from the game.
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
}