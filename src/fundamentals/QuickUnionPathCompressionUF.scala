package fundamentals

case class QuickUnionPathCompressionUF(n:Int) extends UF {

  private val id = new Array[Int](n)
  private var count = n

  // Return component identifier for component containing p
  def find(p:Int) = {

    var root = p
    var q = p

    while (root != id(root)) {
      root = id(root)
    }

    while (q != root) {
      var newp:Int = id(q)
      id(q) = root
      q = newp
    }

    root
  }

  // Replace sets containing p and q with their union.
  def union(p:Int, q:Int) {
    val i:Int = find(p)
    val j:Int = find(q)
    if (i == j) return
    id(i) = j
    count = count - 1
  }
}

object QuickUnionPathCompressionUF {
  def main(args:Array[String]) {

    val lines = io.Source.stdin.getLines()

    val n = lines.next().toInt
    val uf = QuickUnionPathCompressionUF(n)

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
