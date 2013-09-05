package search

import java.util.Arrays
import java.util.Comparator
import scala.collection.immutable.Range



case class Transaction(
  who:String,
  when:Date,
  amount:Double
) extends Comparable[Transaction] {

  // ascending order of account number
  case class WhoOrder(v:Transaction, w:Transaction) extends Comparator[Transaction] {
    def compare:Int = v.who.compareTo(w.who)
  }

  // ascending order of time
  case class WhenOrder(v:Transaction, w:Transaction) extends Comparator[Transaction] {
    def compare:Int = {
      val other = w.when.asInstanceOf[Object]
      v.when.compareTo(other)
    }
  }

  // ascending order of ammount
  case class HowMuchOrder(v:Transaction, w:Transaction) extends Comparator[Transaction] {
    def compare:Int = {
      if      (v.amount < w.amount) -1
      else if (v.amount > w.amount) +1
      else                           0
    }
  }

  override def toString:String = String.format("%-10s %10s %8.2f", who, when, amount)

  def compareTo(that:Transaction):Int = {
    if      (amount < that.amount) -1
    else if (amount > that.amount) +1
    else                            0
  }

  override def hashCode():Int = {
    var hash = 17
    hash = 31*hash + who.hashCode()
    hash = 31*hash + when.hashCode()
    hash = 31*hash + amount.hashCode()
    hash
  }
}

object Transaction {
  // test client
  def main(args:Array[String]) {

    val a = new Array[Transaction](4)
    a[0] = new Transaction("Turing   6/17/1990  644.08")
    a[1] = new Transaction("Tarjan   3/26/2002 4121.85")
    a[2] = new Transaction("Knuth    6/14/1999  288.34")
    a[3] = new Transaction("Dijkstra 8/22/2007 2678.40")

    println("Unsorted")

    for (i <- 0 until a.length)
      println(a(i))

    println()

    println("Sort by date")
    Arrays.sort(a, new Transaction.WhenOrder())

    for (i <- 0 until a.length)
      println(a(i))

    println()

    println("Sort by customer")
    Arrays.sort(a, new Transaction.WhoOrder())

    for (i <- 0 until a.length)
      println(a(i))

    println()

    println("Sort by amount")
    Arrays.sort(a, new Transaction.HowMuchOrder())

    for (i <- 0 until a.length)
      println(a(i))

    println()
  }

}
