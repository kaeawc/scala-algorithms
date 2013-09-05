package search

import java.util.Iterator
import java.util.NoSuchElementException

case class ResizingArrayQueue[Item] extends Iterable[Item] {
    private Item[] q            // queue elements
    private Int N = 0           // number of elements on queue
    private Int first = 0       // index of first element of queue
    private Int last  = 0       // index of next available slot

    // cast needed since no generic array creation in Java
    ResizingArrayQueue() {
        q = (Item[]) new Object[2]
    }

    Boolean isEmpty() { return N == 0    }
    Int size()        { return N         }

    // resize the underlying array
    private void resize(Int max) {
        assert max >= N
        Item[] temp = (Item[]) new Object[max]
        for (i <- 0 until n) {
            temp(i) = q[(first + i) % q.length]
        }
        q = temp
        first = 0
        last  = N
    }


    void enqueue(Item item) {
        // Double size of array if necessary and recopy to front of array
        if (N == q.length) resize(2*q.length)   // Double size of array if necessary
        q[last++] = item                        // add item
        if (last == q.length) last = 0          // wrap-around
        N++
    }

    // remove the least recently added item 
    Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow")
        Item item = q[first]
        q[first] = null                            // to avoid loitering
        N--
        first++
        if (first == q.length) first = 0           // wrap-around
        // shrink size of array if necessary
        if (N > 0 && N == q.length/4) resize(q.length/2) 
        return item
    }

    Iterator[Item] iterator() { return new ArrayIterator() }

    // an iterator, doesn't implement remove() since it's optional
    private class ArrayIterator extends Iterator[Item] {
        private Int i = 0
        Boolean hasNext()  { return i < N                               }
        void remove()      { throw new UnsupportedOperationException()  }

        Item next() {
            if (!hasNext()) throw new NoSuchElementException()
            Item item = q[(i + first) % q.length]
            i++
            return item
        }
    }

   /**
     * A test client.
     */
    def main(args:Array[String]) {
        ResizingArrayQueue<String> q = new ResizingArrayQueue<String>()
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString()
            if (!item.equals("-")) q.enqueue(item)
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ")
        }
        println("(" + q.size() + " left on queue)")
    }

}
