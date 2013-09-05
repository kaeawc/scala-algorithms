package search

import java.util.Arrays
import java.util.Comparator

case class Point2D extends Comparable<Point2D> {

    val POLAR_ORDER = new PolarOrder()
    val ATAN2_ORDER = new Atan2Order()
    val DISTANCE_TO_ORDER = new DistanceToOrder()

    private final x:Double    // x coordinate
    private final y:Double    // y coordinate

    // create a new poInt (x, y)
    Point2D(x:Double, y:Double) {
        x = x
        y = y
    }

    // the x-coorindate of this point
    def x():Double = { x }

    // the y-coorindate of this point
    def y():Double = { y }

    // the radius of this poInt in polar coordinates
    def r():Double = { Math.sqrt(x*x + y*y) }

    // the angle of this poInt in polar coordinates
    // (between -pi/2 and pi/2)
    def theta():Double = { Math.atan2(y, x) }

    // the polar angle between this poInt and that poInt (between -pi and pi)
    // (0 if two points are equal)
    private Double angleTo(Point2D that) {
        Double dx = that.x - x
        Double dy = that.y - y
        Math.atan2(dy, dx)
    }

    // is a->b->c a counter-clockwise turn?
    // -1 if clockwise, +1 if counter-clockwise, 0 if collinear
    static Int ccw(Point2D a, Point2D b, Point2D c) {
        Double area2 = (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x)
        if      (area2 < 0) -1
        else if (area2 > 0) +1
        else                 0
    }

    // twice signed area of a-b-c
    static Double area2(Point2D a, Point2D b, Point2D c) {
        (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x)
    }

    // Euclidean distance between this poInt and that point
    Double distanceTo(Point2D that) {
        Double dx = x - that.x
        Double dy = y - that.y
        Math.sqrt(dx*dx + dy*dy)
    }

    // square of Euclidean distance between this poInt and that point
    Double distanceSquaredTo(Point2D that) {
        Double dx = x - that.x
        Double dy = y - that.y
        dx*dx + dy*dy
    }

    // compare by y-coordinate, breaking ties by x-coordinate
    Int compareTo(Point2D that) {
        if (y < that.y) -1
        if (y > that.y) +1
        if (x < that.x) -1
        if (x > that.x) +1
        0
    }

    // compare points according to their x-coordinate
    private static class XOrder extends Comparator<Point2D> {
        Int compare(Point2D p, Point2D q) {
            if (p.x < q.x) -1
            if (p.x > q.x) +1
            0
        }
    }

    // compare points according to their y-coordinate
    private static class YOrder extends Comparator<Point2D> {
        Int compare(Point2D p, Point2D q) {
            if (p.y < q.y) -1
            if (p.y > q.y) +1
            0
        }
    }

    // compare points according to their polar radius
    private static class ROrder extends Comparator<Point2D> {
        Int compare(Point2D p, Point2D q) {
            Double delta = (p.x*p.x + p.y*p.y) - (q.x*q.x + q.y*q.y)
            if (delta < 0) -1
            if (delta > 0) +1
            0
        }
    }
 
    // compare other points relative to atan2 angle (bewteen -pi/2 and pi/2) they make with this Point
    private class Atan2Order extends Comparator<Point2D> {
        Int compare(Point2D q1, Point2D q2) {
            Double angle1 = angleTo(q1)
            Double angle2 = angleTo(q2)
            if      (angle1 < angle2) -1
            else if (angle1 > angle2) +1
            else                       0
        }
    }

    // compare other points relative to polar angle (between 0 and 2pi) they make with this Point
    private class PolarOrder extends Comparator<Point2D> {
        Int compare(Point2D q1, Point2D q2) {
            Double dx1 = q1.x - x
            Double dy1 = q1.y - y
            Double dx2 = q2.x - x
            Double dy2 = q2.y - y

            if      (dy1 >= 0 && dy2 < 0) -1    // q1 above q2 below
            else if (dy2 >= 0 && dy1 < 0) +1    // q1 below q2 above
            else if (dy1 == 0 && dy2 == 0) {            // 3-collinear and horizontal
                if      (dx1 >= 0 && dx2 < 0) -1
                else if (dx2 >= 0 && dx1 < 0) +1
                else                           0
            }
            else -ccw(Point2D.this, q1, q2)     // both above or below

            // Note: ccw() recomputes dx1, dy1, dx2, and dy2
        }
    }

    // compare points according to their distance to this point
    private class DistanceToOrder extends Comparator<Point2D> {
        Int compare(Point2D p, Point2D q) {
            Double dist1 = distanceSquaredTo(p)
            Double dist2 = distanceSquaredTo(q)
            if      (dist1 < dist2) -1
            else if (dist1 > dist2) +1
            else                     0
        }
    }


    // does this poInt equal y?
    Boolean equals(Object other) {
        if (other == this) true
        if (other == null) false
        if (other.getClass() != getClass()) false
        Point2D that = (Point2D) other
        x == that.x && y == that.y
    }

    // convert to string
    String toString() {
        "(" + x + ", " + y + ")"
    }

    // plot using StdDraw
    void draw() {
        StdDraw.point(x, y)
    }

    // draw line from this poInt p to q using StdDraw
    void drawTo(Point2D that) {
        StdDraw.line(x, y, that.x, that.y)
    }


    def main(args:Array[String]) {
        Int x0 = Integer.parseInt(args[0])
        Int y0 = Integer.parseInt(args[1])
        Int N = Integer.parseInt(args[2])

        StdDraw.setCanvasSize(800, 800)
        StdDraw.setXscale(0, 100)
        StdDraw.setYscale(0, 100)
        StdDraw.setPenRadius(.005)
        Point2D[] points = new Point2D[N]
        for (i <- 0 until n) {
            Int x = StdRandom.uniform(100)
            Int y = StdRandom.uniform(100)
            points(i) = new Point2D(x, y)
            points(i).draw()
        }

        // draw p = (x0, x1) in red
        Point2D p = new Point2D(x0, y0)
        StdDraw.setPenColor(StdDraw.RED)
        StdDraw.setPenRadius(.02)
        p.draw()


        // draw line segments from p to each point, one at a time, in polar order
        StdDraw.setPenRadius()
        StdDraw.setPenColor(StdDraw.BLUE)
        Arrays.sort(points, p.POLAR_ORDER)
        for (i <- 0 until n) {
            p.drawTo(points(i))
            StdDraw.show(100)
        }
    }
}
