package search

import java.util.Iterator
import java.util.NoSuchElementException

/**
*  The <tt>Bag</tt> class represents a bag (or multiset) of 
*  generic items. It supports insertion and iterating over the 
*  items in arbitrary order.
*  <p>
*  The <em>add</em>, <em>isEmpty</em>, and <em>size</em>  operation 
*  take constant time. Iteration takes time proportional to the number of items.
*  <p>
*  For additional documentation, see <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
*  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
*/
case class Bag[Item] extends Iterable[Item] {
  private Int N         // number of elements in bag
  private Node first    // beginning of bag

  // helper linked list class
  private class Node {
    private Item item
    private Node next
  }

  /**
  * Create an empty stack.
  */
  Bag() {
    first = null
    N = 0
    assert check()
  }

  /**
  * Is the BAG empty?
  */
  Boolean isEmpty() {
    return first == null
  }

  /**
  * Return the number of items in the bag.
  */
  Int size() {
    return N
  }

  /**
  * Add the item to the bag.
  */
  void add(Item item) {
    Node oldfirst = first
    first = new Node()
    first.item = item
    first.next = oldfirst
    N++
    assert check()
  }

  // check internal invariants
  private Boolean check() {
    if (N == 0) {
      if (first != null) return false
    }
    else if (N == 1) {
      if (first == null)      return false
      if (first.next != null) return false
    }
    else {
      if (first.next == null) return false
    }

  // check internal consistency of instance variable N
  Int numberOfNodes = 0
  for (Node x = first x != null x = x.next) {
    numberOfNodes++
  }
  if (numberOfNodes != N) return false

  return true
  } 


  /**
  * Return an iterator that iterates over the items in the bag.
  */
  Iterator[Item] iterator()  {
    return new ListIterator()  
  }

  // an iterator, doesn't implement remove() since it's optional
  private class ListIterator extends Iterator[Item] {
    private Node current = first

    Boolean hasNext()  { return current != null                     }
    void remove()      { throw new UnsupportedOperationException()  }

    Item next() {
      if (!hasNext()) throw new NoSuchElementException()
      Item item = current.item
      current = current.next 
      return item
    }
  }

  /**
  * A test client.
  */
  def main(args:Array[String]) {
    Bag<String> bag = new Bag<String>()
    while (!StdIn.isEmpty()) {
      String item = StdIn.readString()
      bag.add(item)
    }

    println("size of bag = " + bag.size())
    for (String s : bag) {
      println(s)
    }
  }


}
