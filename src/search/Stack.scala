package search

import java.util.Iterator
import java.util.NoSuchElementException

/**
*  The <tt>Stack</tt> class represents a last-in-first-out (LIFO) stack of generic items.
*  It supports the usual <em>push</em> and <em>pop</em> operations, along with methods
*  for peeking at the top item, testing if the stack is empty, and iterating through
*  the items in LIFO order.
*  <p>
*  All stack operations except iteration are constant time.
*  <p>
*  For additional documentation, see <a href="/algs4/13stacks">Section 1.3</a> of
*  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
*/
case class Stack[Item]() extends Iterable[Item] {

  var n:Int = 0          // size of the stack
  var first:Node = null     // top of stack

  check

  // helper linked list class
  class Node {
    var item:Item = null
    var next:Node = null
  }

 /**
   * Is the stack empty?
   */
  override def isEmpty:Boolean = first == null

 /**
   * Return the number of items in the stack.
   */
  override def size:Int = n

 /**
   * Add the item to the stack.
   */
  def push(item:Item) {
    val oldfirst = first
    first = new Node()
    first.item = item
    first.next = oldfirst
    n = n + 1
    check
  }

 /**
   * Delete and the item most recently added to the stack.
   * @throws java.util.NoSuchElementException if stack is empty.
   */
  def pop:Item = {
    if (isEmpty) throw new NoSuchElementException("Stack underflow")
    val item = first.item        // save item to return
    first = first.next            // delete first node
    n = n - 1
    check
    item                   // the saved item
  }


 /**
   * Return the item most recently added to the stack.
   * @throws java.util.NoSuchElementException if stack is empty.
   */
  def peek:Item = {

    if (isEmpty) throw new NoSuchElementException("Stack underflow")

    first.item
  }

 /**
   * Return string representation.
   */
  override def toString():String = {
    val s = new StringBuilder()

    for (item <- this)
      s.append(item + " ")

    s.toString()
  }


  // check internal invariants
  def check:Boolean = {
    val initial:Boolean = if (n == 0) {
      if (first != null) false
      else true
    }
    else if (n == 1) {
      if (first == null)      false
      if (first.next != null) false
      else true
    }
    else {
      if (first.next == null) false
      else true
    }

    if(initial) {

      // check internal consistency of instance variable N
      var numberOfNodes = 0
      var x:Node = first
      while (x != null) {
        numberOfNodes = numberOfNodes + 1
        x = x.next
      }
      if (numberOfNodes != n) false
      else true
    } else
      false
  }


 /**
   * Return an iterator to the stack that iterates through the items in LIFO order.
   */
  def iterator:Iterator[Item]  { new ListIterator()  }

  // an iterator, doesn't implement remove() since it's optional
  class ListIterator extends Iterator[Item] {

    var current:Node = first
    def hasNext:Boolean = current != null

    def remove { throw new UnsupportedOperationException() }

    def next():Item = {
      if (!hasNext()) throw new NoSuchElementException()
      val item = current.item
      current = current.next
      item
    }
  }
}

object Stack {

 /**
   * A test client.
   */
  def main(args:Array[String]) {

    val s = new Stack[String]()
    val lines = io.Source.stdin.getLines()

    while(lines.hasNext) {

      val item = lines.next()

      if (!item.equals("-")) s.push(item)
      else if (!s.isEmpty) println(s.pop + " ")

    }

    println("(" + s.size + " left on stack)")
  }
}
