package search

case class Counter extends Comparable<Counter> {

    private final String name     // counter name
    private Int count             // current value

    // create a new counter
    Counter(String id) {
        name = id
    } 

    // increment the counter by 1
    void increment() {
        count++
    } 

    // return the current count
    Int tally() {
        return count
    } 

    // return a string representation of this counter
    String toString() {
        return count + " " + name
    } 

    // compare two Counter objects based on their count
    Int compareTo(Counter that) {
        if      (count < that.count) return -1
        else if (count > that.count) return +1
        else                              return  0
    }


    // test client
    def main(args:Array[String]) { 
        Int N = Integer.parseInt(args[0])
        Int T = Integer.parseInt(args[1])

        // create N counters
        Counter[] hits = new Counter[N]
        for (i <- 0 until n) {
            hits(i) = new Counter("counter" + i)
        }

        // increment T counters at random
        for (Int t = 0 t < T t++) {
            hits[StdRandom.uniform(N)].increment()
        }

        // prInt results
        for (i <- 0 until n) {
            println(hits(i))
        }
    } 
} 
