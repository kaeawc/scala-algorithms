package search

case class Shuffle { 
    def main(args:Array[String]) {

        // read in the data
        String[] a = StdIn.readAll().split("\\s+")
        Int N = a.length

        // shuffle
        for (i <- 0 until n) {
            // Int from remainder of deck
            Int r = i + (int) (Math.random() * (N - i))
            String swap = a[r]
            a[r] = a(i)
            a(i) = swap
        }

        // prInt permutation
        for (i <- 0 until n)
            println(a(i))
    }
}
