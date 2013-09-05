package search

import java.util.Arrays

case class BinarySearch {

    // precondition: array a[] is sorted
    static Int rank(Int key, Array[Int] a) {
        Int lo = 0
        Int hi = a.length - 1
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            Int mid = lo + (hi - lo) / 2
            if      (key < a[mid]) hi = mid - 1
            else if (key > a[mid]) lo = mid + 1
            else return mid
        }
        return -1
    }

    def main(args:Array[String]) {
        In in = new In(args[0])
        Array[Int] whitelist = in.readAllInts()

        Arrays.sort(whitelist)

        // read key prInt if not in whitelist
        while (!StdIn.isEmpty()) {
            Int key = StdIn.readInt()
            if (rank(key, whitelist) == -1)
                println(key)
        }
    }
}
