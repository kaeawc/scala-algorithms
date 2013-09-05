package search

object Average {
  def main(args:Array[String]) {
    Int count = 0       // number input values
    Double sum = 0.0    // sum of input values

    // read data and compute statistics
    while (!StdIn.isEmpty()) {
      Double value = StdIn.readDouble()
      sum += value
      count++
    }

    // compute the average
    Double average = sum / count

    // prInt results
    println("Average is " + average)
  }
}
