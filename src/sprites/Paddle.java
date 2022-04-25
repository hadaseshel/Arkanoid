// ID: 206775074

/**
 * @author hadas eshel
 */

package sprites;

// imports
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

import game.GameLevel;
import primitivesgeometry.Rectangle;
import primitivesgeometry.Line;
import primitivesgeometry.Point;
import collision.Collidable;

/**
 * This class hold the the player in the game.
 */
public class Paddle implements Sprite, Collidable {
    // fields: the rectangle block ,color and the different part of the paddle.
    private KeyboardSensor keyboard;
    private Rectangle rec;
    private Line part1ofPad;
    private Line part2ofPad;
    private Line part3ofPad;
    private Line part4ofPad;
    private Line part5ofPad;
    private Color color;
    private int startWidth;
    private int endWidth;
    private int numOfParts = 5;
    private double angle1 = 300;
    private double angle2 = 330;
    private double angle4 = 30;
    private double angle5 = 60;
    private int speed;

    /**
     * This constructor method creates the Paddle.
     * @param rectangle the rectengle of the paddle.
     * @param c the color of the paddle.
     * @param speed the speed of the paddle.
     */
    public Paddle(Rectangle rectangle, Color c, int speed) {
        this.color = c;
        this.rec = rectangle;
        this.speed = speed;
    }

    /**
     * This method set the KeyboardSensor object in the Paddle.
     * @param k the KeyboardSensor object
     */
    public void setKeyboardSensor(KeyboardSensor k) {
        this.keyboard = k;
    }

    /**
     * This method put the size of the screen in the Paddle.
     * @param startWid the start of the screen.
     * @param endWid the end of the screen.
     */
    public void setSizeOfScreen(int startWid, int endWid) {
        this.startWidth = startWid;
        this.endWidth = endWid;
    }

    /**
     * this method move the paddle to left.
     */
    public void moveLeft() {
        // check if the paddle is in the end of the screen.
        if (this.rec.getUpperLeft().getX() - this.speed <= this.startWidth) {
            this.rec = new Rectangle(new Point(this.startWidth, this.rec.getUpperLeft().getY()),
                    this.rec.getWidth(), this.rec.getHeight());
            return;
        }
        // move the paddle left
        this.rec = new Rectangle(new Point(this.rec.getUpperLeft().getX() - this.speed,
                this.rec.getUpperLeft().getY()), this.rec.getWidth(), this.rec.getHeight());
    }

    /**
     * this method move the paddle to right.
     */
    public void moveRight() {
        // check if the paddle is in the end of the screen.
        if (this.rec.getUpperLeft().getX() + this.rec.getWidth() + this.speed >= this.endWidth) {
            this.rec = new Rectangle(new Point(this.endWidth - this.rec.getWidth(), this.rec.getUpperLeft().getY()),
                    this.rec.getWidth(), this.rec.getHeight());
            return;
        }
        // move the paddle right
        this.rec = new Rectangle(new Point(this.rec.getUpperLeft().getX() + this.speed,
                this.rec.getUpperLeft().getY()), this.rec.getWidth(), this.rec.getHeight());
    }

    @Override
    public void timePassed() {
        // if the user press left
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        // if the user press right
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        // draw the frame of the rectangle.
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rec.getUpperLeft().getX() - 1, (int) this.rec.getUpperLeft().getY() - 1,
                (int) this.rec.getWidth() + 1, (int) this.rec.getHeight() + 1);
        // fill the rectangle.
        d.setColor(this.color);
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                       (int) this.rec.getWidth(), (int) this.rec.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // create the Special zone of the paddle.
        double sizeOFWidh = this.rec.getWidth() / numOfParts;
        this.part1ofPad = new Line(this.rec.getUpperLeft(), new Point(this.rec.getUpperLeft().getX() + sizeOFWidh,
                this.rec.getUpperLeft().getY()));
        this.part2ofPad = new Line(this.part1ofPad.end(), new Point(this.part1ofPad.end().getX() + sizeOFWidh,
                this.rec.getUpperLeft().getY()));
        this.part3ofPad = new Line(this.part2ofPad.end(), new Point(this.part2ofPad.end().getX() + sizeOFWidh,
                this.rec.getUpperLeft().getY()));
        this.part4ofPad = new Line(this.part3ofPad.end(), new Point(this.part3ofPad.end().getX() + sizeOFWidh,
                this.rec.getUpperLeft().getY()));
        this.part5ofPad = new Line(this.part4ofPad.end(), new Point(this.part4ofPad.end().getX() + sizeOFWidh,
                this.rec.getUpperLeft().getY()));
        Velocity hit = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        // division into cases: which edge the element get in collision.
        if (this.rec.getLowWidth().isThePointInLine(collisionPoint)) {
            // put the appropriate speed for hitting special areas
            if (this.part1ofPad.isThePointInLine(collisionPoint)) {
                hit = Velocity.fromAngleAndSpeed(angle1,
                        Math.sqrt(hit.getDx() * hit.getDx() + hit.getDy() * hit.getDy()));
            } else if (this.part2ofPad.isThePointInLine(collisionPoint)) {
                hit = Velocity.fromAngleAndSpeed(angle2,
                        Math.sqrt(hit.getDx() * hit.getDx() + hit.getDy() * hit.getDy()));
            } else if (this.part4ofPad.isThePointInLine(collisionPoint)) {
                hit = Velocity.fromAngleAndSpeed(angle4,
                        Math.sqrt(hit.getDx() * hit.getDx() + hit.getDy() * hit.getDy()));
            } else if (this.part5ofPad.isThePointInLine(collisionPoint)) {
                hit = Velocity.fromAngleAndSpeed(angle5,
                        Math.sqrt(hit.getDx() * hit.getDx() + hit.getDy() * hit.getDy()));
            } else if (this.part3ofPad.isThePointInLine(collisionPoint)) {
                hit = new Velocity(hit.getDx(), -(hit.getDy()));
            }
        } else if (this.rec.getUpperWidth().isThePointInLine(collisionPoint)) {
            hit = new Velocity(hit.getDx(), -(hit.getDy()));
        }
        // if it hit the sides of paddle,
        if (this.rec.getRightHeigt().isThePointInLine(collisionPoint)) {
            hit = new Velocity(-Math.abs(hit.getDx()), hit.getDy());
        } else if (this.rec.getLeftHeigt().isThePointInLine(collisionPoint)) {
            hit = new Velocity(Math.abs(hit.getDx()), hit.getDy());
        }
        // return the hit.
        return hit;
    }

    /**
     * this method add the paddle to the game.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}