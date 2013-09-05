package fundamentals

object DoublingRatio {

  // time ThreeSum.count() for N random 6-digit ints
  def timeTrial(n:Int):Double = {
    val max = 1000000
    val a = new Array[Int](n)

    for (i <- 0 until n) {
      a(i) = (Math.random() * max * 2 - max).toInt
    }
    var timer = new Stopwatch()
    var cnt = ThreeSum.count(a)
    timer.elapsedTime()
  }

  def main(args:Array[String]) {
    var prev = timeTrial(125)
    var n = 250

    while(true) {
      val time = timeTrial(n)
      println("%6d %7.1f %5.1f\n" format(n, time, time/prev))
      prev = time
      n = n + n
    }
  }
}

