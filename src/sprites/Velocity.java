// ID: 206775074

/**
 * @author hadas eshel
 */

package sprites;

//imports
import primitivesgeometry.Point;

/**
 * This class is a Velocity that pecifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    // fields: dx, dy.
    private double dx;
    private double dy;

    /**
     * This constructor method creates the Velocity.
     * @param dx of the Velocity.
     * @param dy of the Velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This method creates the Velocity by the constructor.
     * @param angle of the direction of progress.
     * @param speed the units of progress.
     * @return the new Velocity that created.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // get the angel in radians.
        double radian = Math.toRadians(angle);
        double dx = speed * (Math.sin(radian));
        double dy = speed * (-Math.cos(radian));
        // creates the Velocity by the constructor.
        return new Velocity(dx, dy);
    }


    /**
     * This method return dx of the Velocity.
     * @return dx of the Velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * This method return dy of the Velocity.
     * @return dy of the Velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * This method Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p point with position (x,y).
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}