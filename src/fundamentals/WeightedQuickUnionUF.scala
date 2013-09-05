package fundamentals

/**
 *  The <tt>UF</tt> class represents a union-find data data structure.
 *  It supports the <em>union</em> and <em>find</em>
 *  operations, along with a method for determining the number of
 *  disjosets:Int.
 *  <p>
 *  This implementation uses weighted quick union.
 *  Creating a data structure with n objects takes linear time.
 *  Afterwards, all operations are logarithmic worst-case time.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/15uf">Section 1.5</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
case class WeightedQuickUnionUF(n:Int) extends UF {

  /**
   * Return the id of component corresponding to object p.
   * @throws java.lang.IndexOutOfBoundsException unless 0 <= p < n
   */
  def find(p:Int) = indexOf(p)

  /**
   * Replace sets containing p and q with their union.
   * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < n and 0 <= q < n
   */
  def union(p:Int, q:Int) {
    val i = find(p)
    val j = find(q)
    if (i != j) {
      // make smaller root poto:Int larger one
      if (sz(i) < sz(j)) {
        id(i) = j
        sz(j) += sz(i)
      } else {
        id(j) = i
        sz(i) += sz(j)
      }

      count = count - 1
    }
  }
}


object WeightedQuickUnionUF {
  def main(args:Array[String]) {

    val lines = io.Source.stdin.getLines()

    val n = lines.next().toInt
    val uf = WeightedQuickUnionUF(n)

    // read in a sequence of pairs of integers (each in the range 0 to N-1),
    // calling find() for each pair: If the members of the pair are not already
    // call union() and print the pair.
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
