package fundamentals

class Stopwatch {

  private val start = System.currentTimeMillis()

  def elapsedTime() = {
    val now = System.currentTimeMillis()
    (now - start) / 1000.0
  }

}
