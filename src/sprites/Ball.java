// ID: 206775074

/**
 * @author hadas eshel
 */

package sprites;

// imports
import biuoop.DrawSurface;
import game.GameLevel;
import primitivesgeometry.Point;
import primitivesgeometry.Line;
import collision.GameEnvironment;
import collision.CollisionInfo;
import java.awt.Color;

/**
 * This class is a ball.
 * the ball have a center point, a radios and a color.
 * the ball have a method that can draw the ball in the given DrawSurface way.
 */
public class Ball implements Sprite {

    // fields: center, radios, color, velocity, frame details.
    private Point center;
    private int radios;
    private java.awt.Color color;
    private Velocity velocity = new Velocity(0, 0);
    private Paddle paddle;
    private int startHeight;
    private int endHeight;
    private int startWidth;
    private int endWidth;
    private GameEnvironment gameEnvironment;

    /**
     * This constructor method creates the ball.
     * @param center value of the point that is the center of ball.
     * @param r value of the radios of the ball.
     * @param color the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radios = r;
        this.color = color;
    }

    /**
     * This constructor method creates the ball.
     * @param x value of the x of point that is the center of ball.
     * @param y value of the y of point that is the center of ball.
     * @param r value of the radios of the ball.
     * @param color the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this (new Point(x, y), r, color);
    }

    /**
     * This method return the x value of the center.
     * @return the x value of the center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * This method return the x value of the center.
     * @return the y value of the center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * This method return the radios value of the ball.
     * @return the radios value of the ball.
     */
    public int getSize() {
        return this.radios;
    }

    /**
     * This method return the color of the ball.
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * This method return the start of the frame in X axis of the ball.
     * @return the start of the frame in X axis of the ball.
     */
    public int getStartHeight() {
        return this.startHeight;
    }

    /**
     * This method return the end of the frame in X axis of the ball.
     * @return the end of the frame in X axis of the ball.
     */
    public int getEndHeight() {
        return this.endHeight;
    }

    /**
     * This method return the end of the frame in Y axis of the ball.
     * @return the end of the frame in Y axis of the ball.
     */
    public int getEndWidth() {
        return this.endWidth;
    }

    /**
     * This method return the start of the frame in Y axis of the ball.
     * @return the start of the frame in Y axis of the ball.
     */
    public int getStartWidth() {
        return this.startWidth;
    }

   @Override
    public void drawOn(DrawSurface surface) {
        /*
        This method draw the ball on the given DrawSurface way.
         */
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.getSize());
    }

    /**
     * This method save the size of window in the ball.
     * @param endHeigh Where the frame ends in X axis.
     * @param startHeigh Where the frame begins in X axis.
     * @param startWid Where the frame begins in Y axis.
     * @param endWid Where the frame ends in Y axis.
     */
    public void setSizeOfScreen(int startHeigh, int endHeigh, int startWid, int endWid) {
        // save the size of window in the ball.
        this.startHeight = startHeigh;
        this.endHeight = endHeigh;
        this.startWidth = startWid;
        this.endWidth = endWid;
        // in case of the radios are big then the size of the screen.
        if (this.radios >= ((endHeigh - startHeigh) / 2)) {
            this.radios = ((endHeigh - startHeigh) / 3);
        }
        if (this.radios >= ((endWid - startWid) / 2)) {
            this.radios = ((endWid - startWid) / 3);
        }
    }

    /**
     * This method save the game environmen in the ball.
     * @param g the game environment.
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.gameEnvironment = g;
    }

    /**
     * This method save the Velocity in the ball.
     * @param v the Velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * This method save the Velocity in the ball by double arguments.
     * @param dx of the Velocity.
     * @param dy of the Velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * This method set the paddle in the ball.
     * @param pad the Velocity.
     */
    public void setPaddle(Paddle pad) {
        this.paddle = pad;
    }

    /**
     * This method return the velocity of the ball.
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    @Override
    public void timePassed() {
        /*
         * This method make the ball move.
         * the ball does not go outside of the screen:
         * when it hits the border to the left or to the right,
         * it should change its horizontal direction,
         * and when it hits the border on the top or the bottom,
         * it should change its vertical direction.
         * the ball also does not go into the collide able objects.
         */
        // if ball in the paddle.
        if (this.getX() > this.paddle.getCollisionRectangle().getUpperLeft().getX()
                && this.getX() < this.paddle.getCollisionRectangle().getUpperLeft().getX()
                + this.paddle.getCollisionRectangle().getWidth()) {
            if (this.getY() > this.paddle.getCollisionRectangle().getUpperLeft().getY()
                    && this.getY() < this.paddle.getCollisionRectangle().getUpperLeft().getY()
                    + this.paddle.getCollisionRectangle().getHeight()) {
                // set the proper velocity to the ball in order to get out of the paddle.
                this.setVelocity(-this.velocity.getDx(), -this.velocity.getDy());
                while ((this.getX() > this.paddle.getCollisionRectangle().getUpperLeft().getX()
                        && this.getX() < this.paddle.getCollisionRectangle().getUpperLeft().getX()
                        + this.paddle.getCollisionRectangle().getWidth())
                        && (this.getY() > this.paddle.getCollisionRectangle().getUpperLeft().getY()
                        && this.getY() < this.paddle.getCollisionRectangle().getUpperLeft().getY()
                        + this.paddle.getCollisionRectangle().getHeight())) {
                    this.center = this.getVelocity().applyToPoint(this.center);
                }
                // move the ball.
                this.center = this.getVelocity().applyToPoint(this.center);
            }
        }

        // check if the ball meet block
        Line trajectory = new Line(this.center, new Point(this.getX() + 2 * this.getVelocity().getDx(),
                this.getY() + 2 * this.velocity.getDy()));
        CollisionInfo theColInfo = this.gameEnvironment.getClosestCollision(trajectory);
        // check if there is a collision.
        if (theColInfo != null) {
            // move the ball to "almost" the hit point, but just slightly before it.
            this.center = new Point(theColInfo.collisionPoint().getX() - this.velocity.getDx(),
                  theColInfo.collisionPoint().getY() - this.velocity.getDy());
            // update the velocity to the new velocity returned by the hit() method.
            this.setVelocity(theColInfo.collisionObject().hit(this, theColInfo.collisionPoint(), this.velocity));
            // in order to check the next option of collision.
            return;
        }
        // move the ball.
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * this method add the ball to the game.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * this method remove the block from the game.
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}