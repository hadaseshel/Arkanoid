// ID: 206775074

/**
 * @author hadas eshel
 */
package primitivesgeometry;

/**
 * This class is a line-segment connects two points :a start point and an end point.
 * the class have lengths, and may intersect with other lines.
 * it can also tell if it is the same as another line segment.
 */
public class Line {

    // constants
    static final double MID_CALCULATION = 2;
    static final double ZERO = 0;

    // fields: line-segment connects two points,lengths, incline and point which cutting Y axis
    private Point start;
    private Point end;
    private double length;
    private double incline;
    private double cuttingWithYAxis;

    // constructors.

    /**
     * This constructor method creates the line.
     * @param start the point that the line start from her.
     * @param end the point that the line is end in her.
     */
    public Line(Point start, Point end) {
        // put the points in the proper field.
        this.start = start;
        this.end = end;
        // calculate the length of the segment by the method of the Point class.
        this.length = start.distance(end);
        // calculate the incline of the line by the proper equation.
        this.incline = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        // put the proper value in that case of straight line.
        if (this.start.getY() - this.end.getY() == ZERO) {
            this.incline = ZERO;
        }
        // calculate the value of the point of the line "cut" in Y axis.
        this.cuttingWithYAxis = (this.start.getY() - (this.start.getX() * this.incline));
    }

    /**
     * This constructor method creates the line.
     * @param x1 the x value of the point that the line start from her.
     * @param y1 the y value of the point that the line start from her.
     * @param x2 the x value of the line end in her.
     * @param y2 the y value of the line end in her.
     */
    public Line(double x1, double y1, double x2, double y2) {
        // creat new points and call the constructor below.
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * This method return the value of the incline of line.
     * @return the value of the incline of line.
     */
    public double incline() {
        return this.incline;
    }

    /**
     * This method return the value of the point of the line "cut" in Y axis.
     * @return the value of the point of the line "cut" in Y axis.
     */
    public double cuttingWithYAxis() {
        return this.cuttingWithYAxis;
    }

    /**
     * This method return the length of the line.
     * @return the length of the line.
     */
    public double length() {
        return this.length;
    }

    /**
     * This method return the middle point of the line.
     * @return the middle point of the line.
     */
    public Point middle() {
        // calculation the middle values of y and x separately.
        double midX = (this.start.getX() + this.end.getX()) / MID_CALCULATION;
        double midY = (this.start.getY() + this.end.getY()) / MID_CALCULATION;
        // create new point according to values
        Point middle = new Point(midX, midY);
        // return this point.
        return middle;
    }

    /**
     * This method return the start point of the line.
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * This method the end point of the line.
     * @return the start end of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * This method return maximum value of numbers.
     * @param num1 number one.
     * @param num2 number two.
     * @return maximum value of numbers.
     */
    public double maxVal(double num1, double num2) {
        if (num1 > num2) {
            return num1;
        }
        return num2;
    }

    /**
     * This method return minimum value of numbers.
     * @param num1 number one.
     * @param num2 number two.
     * @return minimum value of numbers.
     */
    public double minVal(double num1, double num2) {
        if (num1 < num2) {
            return num1;
        }
        return num2;
    }

    /**
     * This method return true if the point other is in this line ,and false otherwise.
     * @param other the point that need to check if is in the line.
     * @return true if the point other is in this line ,and false otherwise.
     */
    public boolean isThePointInLine(Point other) {
        // create maximum and minimum range of the Y and X values of the line-segment.
        double maxInX = this.maxVal(this.start.getX(), this.end.getX());
        double minInX = this.minVal(this.start.getX(), this.end.getX());
        double maxInY = this.maxVal(this.start.getY(), this.end.getY());
        double minInY = this.minVal(this.start.getY(), this.end.getY());
        double epsilon = 0.000000000000001;
        // check if the x value is in the rang of the line.
        if ((other.getX() < minInX) || (other.getX() > maxInX)) {
            return false;
        }
        // check if the y value is in the rang of the line.
        if ((other.getY() < minInY) || (other.getY() > maxInY)) {
            return false;
        }
        // in case that the line have infinity incline and the point is on it range.
        if ((Math.abs(this.start.getX() - this.end.getX()) < epsilon)
                && (Math.abs(other.getX() - this.start.getX()) < epsilon)) {
            return true;
        }
        // in case that the line have zero incline and the point is on it range.
        if ((Math.abs(this.start.getY() - this.end.getY()) < epsilon)
                && (Math.abs(other.getY() - this.start.getY()) < epsilon)) {
            return true;
        }
        // check if the straight line equation holds in the placement of the point values
        if (Math.abs(other.getY() - ((other.getX() * this.incline) + this.cuttingWithYAxis)) < epsilon) {
            return true;
        }
        // otherwise return false
        return false;
    }

    /**
     * This method return true if the lines are equal,and false otherwise.
     * @param other the other line that need the check if is equals to this one.
     * @return true if the lines are equal,and false otherwise.
     */
    public boolean equals(Line other) {
        // check the case that the lines have the same start and end.
        if ((this.start.equals(other.start)) && (this.end.equals(other.end))) {
            return true;
        }
        // check the case that the start and end points are different but the lines are the same.
        if ((this.start.equals(other.end())) && (this.end.equals(other.start))) {
            return true;
        }
        // otherwise return false.
        return false;
    }

    /**
     * This method return true if this line contain the other line,and false otherwise.
     * @param other the other line that need the check if is in this line.
     * @return true if this line contain the other line,and false otherwise.
     */
    public boolean contain(Line other) {
        if (this.isThePointInLine(other.start) && this.isThePointInLine(other.end)) {
            return true;
        }
        return false;
    }

    /**
     * This method return true if the lines intersect, false otherwise.
     * @param other the other line that need to check if is intersect this line.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        // a variable that holds a parameter of one part of the equation (subtraction of inclines).
        double m = this.incline - other.incline();

        // if the lines are equals.
        if (this.equals(other)) {
            return true;
        }

        //in case of straight cohesion
        if ((this.isThePointInLine(other.start())) || (this.isThePointInLine(other.end()))) {
            return true;
        }
        if ((other.isThePointInLine(this.start())) || (other.isThePointInLine(this.end()))) {
            return true;
        }

        double epsilon = 0.000000000000001;
        // in case that the lines have the same incline.
        if (Math.abs(this.incline - other.incline()) < epsilon) {
            return false;
        }

        // a variable that holds a parameter of other part of the equation (the cut of Y axis).
        double b = other.cuttingWithYAxis() - this.cuttingWithYAxis;

        // create variables which will be use in all the cases.
        double x = ZERO;
        double y = ZERO;
        Point theCut = new Point(x, y);

        // division into cases: when other is a straight vertical.
        if (Math.abs(other.start.getX() - other.end.getX()) < epsilon) {
            // create the value of x,y that is the solving the equation.
            x = other.start.getX();
            y = (x * this.incline) + this.cuttingWithYAxis;
            if (Math.abs(this.start.getY() - this.end.getY()) < epsilon) {
                y = this.start.getY();
            }
            theCut = new Point(x, y);
        } else if (Math.abs(this.start.getX() - this.end.getX()) < epsilon) {
            // when this is a straight vertical create the value of x,y that is the solving the equation.
            x = this.start.getX();
            y = (x * other.incline) + other.cuttingWithYAxis;
            if (Math.abs(other.start.getY() - other.end.getY()) < epsilon) {
                y = other.start.getY();
            }
            theCut = new Point(x, y);
        } else {
            // create the value of x,y that is the solving the equation.
            x = (b / m);
            y = (x * this.incline) + this.cuttingWithYAxis;
            theCut = new Point(x, y);
        }

        // check if the point is on the lines.
        if (this.isThePointInLine(theCut)) {
            // create maximum and minimum range of the Y and X values of the other line-segment.
            double maxInX = other.maxVal(other.start.getX(), other.end.getX());
            double minInX = other.minVal(other.start.getX(), other.end.getX());
            double maxInY = other.maxVal(other.start.getY(), other.end.getY());
            double minInY = other.minVal(other.start.getY(), other.end.getY());
            // check if the x value is in the rang of the line.
            if ((x < minInX) || (x > maxInX)) {
                return false;
            }
            // check if the y value is in the rang of the line.
            if ((y < minInY) || (y > maxInY)) {
                return false;
            }
            return true;
        }
        // return false otherwise.
        return false;
    }

    /**
     * This method return intersection point if the lines intersect,and null otherwise.
     * @param other the other line that need to check what is the intersect with this line.
     * @return intersection point if the lines intersect,and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // check if there is intersection.
        if (this.isIntersecting(other)) {
            // case of two lines that is equals and have only one point.
            if (this.equals(other)) {
                if (this.start.equals(this.end())) {
                    return this.start;
                } else {
                    // case of lines that equal anf they are not a point.
                    return null;
                }
            }
            // case of one of the lines is point.
            if (this.start.equals(this.end)) {
                if (other.isThePointInLine(this.start)) {
                    return this.start;
                } else {
                    return null;
                }
            }
            if (other.start.equals(other.end)) {
                if (this.isThePointInLine(other.start)) {
                    return other.start;
                } else {
                    return null;
                }
            }

            //a variable that holds a parameter of one part of the equation (subtraction of inclines).
            double m = this.incline - other.incline;

            double epsilon = 0.000000000000001;
            // in case that the lines have the same incline
            if (Math.abs(this.incline - other.incline()) < epsilon) {
                // check all the cases.
                if (this.contain(other) || other.contain(this)) {
                    return null;
                } else if (this.start.equals(other.start) || this.start.equals(other.end)) {
                    return this.start;
                } else if (this.end.equals(other.end) || this.end.equals(other.start)) {
                    return this.end;
                }
                return null;
            }

            // if the lines have an infinite slope.
            if ((Math.abs(this.start.getX() - this.end.getX()) < epsilon)
                    && (Math.abs(other.start.getX() - other.end.getX()) < epsilon)) {
                // check all the cases.
                if (this.contain(other) || other.contain(this)) {
                    return null;
                } else if (this.start.equals(other.start) || this.start.equals(other.end)) {
                    return this.start;
                } else if (this.end.equals(other.end) || this.end.equals(other.start)) {
                    return this.end;
                }
                return null;
            }

            // if this line has an infinite slope.
            if (Math.abs(this.start.getX() - this.end.getX()) < epsilon) {
                double y = (this.start.getX() * other.incline()) + other.cuttingWithYAxis;
                return new Point(this.start.getX(), y);
            }

            // if other line has an infinite slope.
            if (Math.abs(other.start.getX() - other.end.getX()) < epsilon) {
                double y = (other.start.getX() * this.incline()) + this.cuttingWithYAxis;
                return new Point(other.start.getX(), y);
            }

            // a variable that holds a parameter of other part of the equation (the cut of Y axis).
            double b = other.cuttingWithYAxis() - this.cuttingWithYAxis;

            // create the value of x,y that is the solving the equation
            double x = b / m;
            double y = (x * this.incline) + this.cuttingWithYAxis;
            // create the point and return her.
            Point theCut = new Point(x, y);
            return theCut;
            } else {
            // return null otherwise
            return null;
        }
    }

    /**
     * This method return the closest intersection point to the start of the line with the rectangle.
     * @param rect the rectangle that need to check if have intersect with this line.
     * @return the closest intersection point to the start of the line with the rectangle, if there is no
     * intersect with the rectangle, return null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // get list of the instrection by calling meth of the rectangle.
        java.util.List<Point> insterctions = rect.intersectionPoints(this);
        // if the list is not empty.
        if (insterctions != null) {
            if (insterctions.size() != 0) {
                // if there are two point in the list check which one is the closer to start of line.
                if (insterctions.size() == 2) {
                    if (insterctions.get(0).distance(this.start) > insterctions.get(1).distance(this.start)) {
                        return insterctions.get(1);
                    } else {
                        return insterctions.get(0);
                    }
                } else {
                    // there is only one instrection.
                    return insterctions.get(0);
                }
            }
        }
        // if there is no intersect with the rectangle, return null.
        return null;
    }
}