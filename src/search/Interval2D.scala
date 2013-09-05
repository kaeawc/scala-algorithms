package search

case class Interval2D {
    private final Interval1D x
    private final Interval1D y

    Interval2D(Interval1D x, Interval1D y) {
        x = x
        y = y
    }

    // does this interval intersect that one?
    Boolean intersects(Interval2D that) {
        if (!x.intersects(that.x)) return false
        if (!y.intersects(that.y)) return false
        return true
    }

    // does this interval contain x?
    Boolean contains(Point2D p) {
        return x.contains(p.x())  && y.contains(p.y())
    }

    // area of this interval
    Double area() {
        return x.length() * y.length()
    }
        
    String toString() {
        return x + " x " + y
    }

    void draw() {
        Double xc = (x.left() + x.right()) / 2.0
        Double yc = (y.left() + y.right()) / 2.0
        StdDraw.rectangle(xc, yc, x.length() / 2.0, y.length() / 2.0)
    }

    // test client
    def main(args:Array[String]) {
        Double xlo = Double.parseDouble(args[0])
        Double xhi = Double.parseDouble(args[1])
        Double ylo = Double.parseDouble(args[2])
        Double yhi = Double.parseDouble(args[3])
        Int T = Integer.parseInt(args[4])

        Interval1D xinterval = new Interval1D(xlo, xhi)
        Interval1D yinterval = new Interval1D(ylo, yhi)
        Interval2D box = new Interval2D(xinterval, yinterval)
        box.draw()

        Counter counter = new Counter("hits")
        for (Int t = 0 t < T t++) {
            Double x = StdRandom.random()
            Double y = StdRandom.random()
            Point2D p = new Point2D(x, y)

            if (box.contains(p)) counter.increment()
            else                 p.draw()
        }

        println(counter)
        StdOut.printf("box area = %.2f\n", box.area())
    }
}