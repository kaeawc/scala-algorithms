package search

import java.util.Arrays
import java.util.Comparator

case class Interval1D {
    static final Comparator<Interval1D> LEFT_ENDPOINT_ORDER  = new LeftComparator()
    static final Comparator<Interval1D> RIGHT_ENDPOINT_ORDER = new RightComparator()
    static final Comparator<Interval1D> LENGTH_ORDER         = new LengthComparator()

    private final Double left
    private final Double right

    Interval1D(Double left, Double right) {
        if (left <= right) {
            left  = left
            right = right
        }
        else throw new IllegalArgumentException("Illegal interval")
    }

    // left endpoint
    Double left() { 
        return left
    }

    // right endpoint
    Double right() { 
        return right
    }

    // does this interval intersect that one?
    Boolean intersects(Interval1D that) {
        if (right < that.left) return false
        if (that.right < left) return false
        return true
    }

    // does this interval contain x?
    Boolean contains(Double x) {
        return (left <= x) && (x <= right)
    }

    // length of this interval
    Double length() {
        return right - left
    }

    // string representation of this interval        
    String toString() {
        return "[" + left + ", " + right + "]"
    }



    // ascending order of left endpoint, breaking ties by right endpoint
    private static class LeftComparator extends Comparator<Interval1D> {
        Int compare(Interval1D a, Interval1D b) {
            if      (a.left  < b.left)  return -1
            else if (a.left  > b.left)  return +1
            else if (a.right < b.right) return -1
            else if (a.right > b.right) return +1
            else                        return  0
        }
    }

    // ascending order of right endpoint, breaking ties by left endpoint
    private static class RightComparator extends Comparator<Interval1D> {
        Int compare(Interval1D a, Interval1D b) {
            if      (a.right < b.right) return -1
            else if (a.right > b.right) return +1
            else if (a.left  < b.left)  return -1
            else if (a.left  > b.left)  return +1
            else                        return  0
        }
    }

    // ascending order of length
    private static class LengthComparator extends Comparator<Interval1D> {
        Int compare(Interval1D a, Interval1D b) {
            Double alen = a.length()
            Double blen = b.length()
            if      (alen < blen) return -1
            else if (alen > blen) return +1
            else                  return  0
        }
    }




    // test client
    def main(args:Array[String]) {
        Interval1D[] intervals = new Interval1D[4]
        intervals[0] = new Interval1D(15.0, 33.0)
        intervals[1] = new Interval1D(45.0, 60.0)
        intervals[2] = new Interval1D(20.0, 70.0)
        intervals[3] = new Interval1D(46.0, 55.0)

        println("Unsorted")
        for (Int i = 0 i < intervals.length i++)
            println(intervals(i))
        println()
        
        println("Sort by left endpoint")
        Arrays.sort(intervals, Interval1D.LEFT_ENDPOINT_ORDER)
        for (Int i = 0 i < intervals.length i++)
            println(intervals(i))
        println()

        println("Sort by right endpoint")
        Arrays.sort(intervals, Interval1D.RIGHT_ENDPOINT_ORDER)
        for (Int i = 0 i < intervals.length i++)
            println(intervals(i))
        println()

        println("Sort by length")
        Arrays.sort(intervals, Interval1D.LENGTH_ORDER)
        for (Int i = 0 i < intervals.length i++)
            println(intervals(i))
        println()
    }
}
