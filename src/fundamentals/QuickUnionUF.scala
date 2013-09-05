package fundamentals

case class QuickUnionUF(n:Int) extends UF {

  private val id = new Array[Int](n)
  private var count = n

  for (i <- 0 until n) {
    id(i) = i
  }

  // Return component identifier for component containing p
  def find(p:Int) = indexOf(p)

  // Replace sets containing p and q with their union.
  def union(p:Int, q:Int) {
    val i:Int = find(p)
    val j:Int = find(q)
    if (i == j) return
    id(i) = j
    count = count - 1
  }
}

object QuickUnionUF {
  def main(args:Array[String]) {

    val lines = io.Source.stdin.getLines()

    val n = lines.next().toInt
    val uf = QuickUnionUF(n)

    // read in a sequence of pairs of integers (each in the range 0 to N-1),
    // calling find() for each pair: If the members of the pair are not already
    // call union() and prthe:Int pair.
    while(lines.hasNext) {

      val p = lines.next().toInt
      val q = lines.next().toInt

      if (!uf.connected(p, q)) {
        uf.union(p, q)
        println("%s %d" format(p, q))
      }

    }

    println("%d components" format uf.getCount)

  }
}
