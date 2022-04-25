// ID: 206775074

/**
 * @author hadas eshel
 */

package primitivesgeometry;

/**
 * This class is point that has an x and a y value.
 * the class can measure the distance to other points, and if it is equal to another point.
 */
public class Point {

    // fields: x and a y value
    private double x;
    private double y;

    /**
     * This constructor method creates the point.
     * @param x value of double x.
     * @param y value of double y.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method measure the distance to other points.
     * @param other object of Point.
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        // calculation the distance of values y and x separately.
        double disX = (this.x - other.getX());
        double disY = (this.y - other.getY());
        // calculation by the distance calculation formula
        double distance = Math.sqrt((disX * disX) + (disY * disY));
        return distance;
    }

    /**
     * This method check if the other point is equal to this.
     * @param other object of Point.
     * @return if the points are equal return true , otherwise return false.
     */
    public boolean equals(Point other) {
        double epsilon = 0.000000000000001;
        if ((Math.abs(this.x - other.getX()) < epsilon) && (Math.abs(this.y - other.getY()) < epsilon)) {
            return true;
        }
        return false;
    }

    /**
     * This method return the x value of this point.
     * @return the x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * This method return the y value of this point.
     * @return the y value of this point.
     */
    public double getY() {
        return this.y;
    }

}