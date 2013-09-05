package fundamentals

object DoublingTest {

  def main(args:Array[String]) {

    var n:Int = 250

    while (true) {
      val time = timeTrial(n)
      println("%7d %5.1f", n, time)
      n = n + n
    }
  }

  def timeTrial(n:Int):Double = {

    val max = 1000000
    val a = new Array[Int](n)

    for (i <- 0 until n)
      a(i) = Math.round(Math.random() * 2 * max - max).toInt

    val timer = new Stopwatch()
    val count = ThreeSum.count(a)
    timer.elapsedTime()
  }

}
