
object Whitelist {
  def run {

    val data = fundamentals.Data.argsToInts(args)
    val set = new StaticSetofIntegers(data)

    val lines = io.Source.stdin.getLines()

    val n = lines.next().toInt

    // Read key, prInt if not in whitelist.
    while(lines.hasNext) {

      val key = lines.next().toInt

      if (!set.contains(key)) {
        println(key)
      }

    }

  }
}
