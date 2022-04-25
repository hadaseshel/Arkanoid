// ID: 206775074

/**
 * @author hadas eshel
 */

package primitivesgeometry;

//imports
import java.util.ArrayList;

/**
 * This class is a rectangle which contain: location and width/height.
 * the rectangle are aligned with the axes.
 * there is math in the class that return a (possibly empty) List of intersection points with the specified line.
 */
public class Rectangle {
    // fields: the points and edges of the rectangle, the size of rectangle.
    private Point upperLeft;
    private Point upperRight;
    private Point lowRight;
    private Point lowLeft;
    private Line leftHeigt;
    private Line rightHeigt;
    private Line upperWidth;
    private Line lowWidth;
    private double width;
    private double height;

    /**
     * This constructor method creates the rectangle by the location and width/height.
     * @param upperLeft the upper left point of the rectangle.
     * @param width the size of rectangle.
     * @param height the size of rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
        // create the points of the rectangle.
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.lowLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.lowRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        // create the edges of the rectangle.
        this.leftHeigt = new Line(this.upperRight, this.lowRight);
        this.lowWidth = new Line(this.upperRight, this.upperLeft);
        this.upperWidth = new Line(this.lowLeft, this.lowRight);
        this.rightHeigt = new Line(this.upperLeft, this.lowLeft);
    }

    /**
     * This method return a (possibly empty) List of intersection points with the specified line.
     * @param line the specified line.
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // create the array.
        java.util.List<Point> theList = new ArrayList<Point>();
        // check if there is an intersection with one of the edges.
        if (this.leftHeigt.intersectionWith(line) != null) {
            theList.add(this.leftHeigt.intersectionWith(line));
        }
        if (this.upperWidth.intersectionWith(line) != null) {
            // check if the intersection is already exist in the list.
            if (!theList.contains(this.upperWidth.intersectionWith(line))) {
                theList.add(this.upperWidth.intersectionWith(line));
            }
        }
        if (this.rightHeigt.intersectionWith(line) != null) {
            // check if the intersection is already exist in the list.
            if (!theList.contains(this.rightHeigt.intersectionWith(line))) {
                theList.add(this.rightHeigt.intersectionWith(line));
            }
        }
        if (this.lowWidth.intersectionWith(line) != null) {
            // check if the intersection is already exist in the list.
            if (!theList.contains(this.lowWidth.intersectionWith(line))) {
                theList.add(this.lowWidth.intersectionWith(line));
            }
        }
        // return the list.
        return theList;
    }

    // access.
    /**
     * This method return the width of the rectangle.
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * This method return the height of the rectangle.
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * This method return the upper-left point of the rectangle.
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * This method return the left height rib line of the rectangle.
     * @return the left height rib line of the rectangle.
     */
    public Line getLeftHeigt() {
        return this.leftHeigt;
    }

    /**
     * This method return the right height rib line of the rectangle.
     * @return the right height rib line of the rectangle.
     */
    public Line getRightHeigt() {
        return this.rightHeigt;
    }

    /**
     * This method return the upper width rib line of the rectangle.
     * @return the upper width rib line of the rectangle.
     */
    public Line getUpperWidth() {
        return this.upperWidth;
    }

    /**
     * This method return the low width rib line of the rectangle.
     * @return the low width rib line of the rectangle.
     */
    public Line getLowWidth() {
        return this.lowWidth;
    }
}

