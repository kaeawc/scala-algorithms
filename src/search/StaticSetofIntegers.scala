package search

import java.util.Arrays

case class StaticSetofIntegers(keys:Array[Int]) {
    private val a = new Array[Int](keys.length)

    // defensive copy
    for (i <- 0 until keys.length)
      a(i) = keys(i)

    Arrays.sort(a)

    // probably should check that no duplicates

    def contains(key:Int):Boolean = {
        rank(key) != -1
    }

    // Binary search.
    def rank(key:Int):Int = {
        var lo:Int = 0
        var hi:Int = a.length - 1
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            val mid:Int = lo + (hi - lo) / 2
            if      (key < a(mid)) hi = mid - 1
            else if (key > a(mid)) lo = mid + 1
            else mid
        }
        -1
    }
}
