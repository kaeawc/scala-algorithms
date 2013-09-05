package fundamentals

trait UF {

  val n:Int
  protected val id:Array[Int] = new Array[Int](n)
  protected val sz:Array[Int] = new Array[Int](n)
  protected var count:Int = n

  if (n < 0) throw new IllegalArgumentException()

  for (i <- 0 until n) {
    id(i) = i
    sz(i) = 1
  }

  /**
   * Return the id of component corresponding to object p.
   * @throws java.lang.IndexOutOfBoundsException unless 0 <= p < n
   */
  def find(p:Int):Int

  protected def indexOf(p:Int) = {
    
    var q = p

    while (q != id(q))
      q = id(q)

    q
  }

  /**
   * Return the number of disjosets:Int.
   */
  def getCount() = count

  /**
   * Are objects p and q in the same set?
   * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < n and 0 <= q < n
   */
  def connected(p:Int, q:Int) = find(p) == find(q)

  /**
   * Replace sets containing p and q with their union.
   * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < n and 0 <= q < n
   */
  def union(p:Int, q:Int)
}
