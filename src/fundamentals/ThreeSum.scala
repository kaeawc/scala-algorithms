package fundamentals

object ThreeSum {

  def printAll(a:Array[Int]) {
    val n = a.length

    for (i <- 0 until n) {
      for(j <- i+1 until n) {
        for (k <- j+1 until n) {
          if (a(i) + a(j) + a(k) == 0) {
            println(a(i) + " " + a(j) + " " + a(k))
          }
        }
      }
    }
  }

  def count(a:Array[Int]) = {
    val n = a.length
    var count = 0

    for (i <- 0 until n) {
      for(j <- i+1 until n) {
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
    val timer = new Stopwatch()
    val result = count(data)
    println("elapsed time = " + timer.elapsedTime())
    println(result)

  }
}
