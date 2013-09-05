class RandomSeq { 
    def main(args:Array[String]) {

        // command-line arguments
        Int N = Integer.parseInt(args[0])

        if (args.length == 1) {
            // generate and prInt N numbers between 0.0 and 1.0
            for (i <- 0 until n) {
                Double x = StdRandom.uniform()
                println(x)
            }
        }

        else if (args.length == 3) {
            Double lo = Double.parseDouble(args[1])
            Double hi = Double.parseDouble(args[2])

            // generate and prInt N numbers between lo and hi
            for (i <- 0 until n) {
                Double x = StdRandom.uniform(lo, hi)
                StdOut.printf("%.2f\n", x)
            }
        }

        else {
            throw new IllegalArgumentException("Invalid number of arguments")
        }
    }
}
