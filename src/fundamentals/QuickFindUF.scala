package fundamentals

case class QuickFindUF(n:Int) extends UF {

  private var count:Int = n
  private val list = Array[Int](n)

  def find(p:Int):Int = list(p)

  def union(p:Int, q:Int) {

    if (!connected(p, q)) {

      val disconnect = list(p)

      for (i <- 0 until list.length) {
        if (list(i) == disconnect) {
          list(i) = list(q)
        }
      }

      count = count - 1
    }
  }
}

object QuickFindUF {

  def main(args:Array[String]) {

    val lines = io.Source.stdin.getLines

    val n = lines.next().toInt
    val uf = new QuickFindUF(n)

    while(lines.hasNext) {

      val p = lines.next().toInt
      val q = lines.next().toInt

      if (!uf.connected(p, q)) {
        uf.union(p, q)
        println("%s %d" format(p, q))
      }

    }

    println("%d components" format uf.count)

  }
}
