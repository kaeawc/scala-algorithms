package search

import java.util.Iterator
import java.util.NoSuchElementException

case class ResizingArrayStack[Item] extends Iterable[Item] {
    private Item[] a         // array of items
    private Int N            // number of elements on stack

    // create an empty stack
    ResizingArrayStack() {
        a = (Item[]) new Object[2]
    }

    Boolean isEmpty() { return N == 0 }
    Int size()        { return N      }


    // resize the underlying array holding the elements
    private void resize(Int capacity) {
        assert capacity >= N
        Item[] temp = (Item[]) new Object[capacity]
        for (i <- 0 until n) {
            temp(i) = a(i)
        }
        a = temp
    }

    // push a new item onto the stack
    void push(Item item) {
        if (N == a.length) resize(2*a.length)    // Double size of array if necessary
        a[N++] = item                            // add item
    }

    // delete and return the item most recently added
    Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow")
        Item item = a[N-1]
        a[N-1] = null                              // to avoid loitering
        N--
        // shrink size of array if necessary
        if (N > 0 && N == a.length/4) resize(a.length/2)
        return item
    }


    Iterator[Item] iterator()  { return new ReverseArrayIterator()  }

    // an iterator, doesn't implement remove() since it's optional
    private class ReverseArrayIterator extends Iterator[Item] {
        private Int i

        ReverseArrayIterator() {
            i = N
        }

        Boolean hasNext() {
            return i > 0
        }

        void remove() {
            throw new UnsupportedOperationException()
        }

        Item next() {
            if (!hasNext()) throw new NoSuchElementException()
            return a[--i]
        }
    }



   /***********************************************************************
    * Test routine.
    **********************************************************************/
    def main(args:Array[String]) {
        ResizingArrayStack<String> s = new ResizingArrayStack<String>()
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString()
            if (!item.equals("-")) s.push(item)
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ")
        }
        println("(" + s.size() + " left on stack)")
    }
}
