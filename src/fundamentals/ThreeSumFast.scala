package fundamentals

import java.util.Arrays

object ThreeSumFast {

  def printAll(a:Array[Int]) {
    val n = a.length

    for (i <- 0 until n) {
      for(j <- i+1 until n) {

        val k = Arrays.binarySearch(a, -(a(i) + a(j)))
        if (k > j) println(a(i) + " " + a(j) + " " + a(k))

      }
    }
  }

  def count(a:Array[Int]) = {
    val n = a.length
    var count = 0

    for (i <- 0 until n) {
      for(j <- i+1 until n) {

        val k = Arrays.binarySearch(a, -(a(i) + a(j)))
        if (k > j) count = count + 1

        for (k <- j+1 until n) {
          if (a(i) + a(j) + a(k) == 0) {
            count = count + 1
          }
        }
      }
    }

    count
  }

  def main(args:Array[String]) = {

    val data = fundamentals.Data.argsToInts(args)
    val result = count(data)
    println(result)

  }
}
