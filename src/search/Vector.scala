package search

case class Vector(x:Int) extends IVector {

  n    = x
  data = new Array[Double](n)

  for (i <- 0 until n)
    data(i) = i
}

case class Vectors(d:Array[Double]) extends IVector {

  n    = d.length
  data = new Array[Double](n)

  for (i <- 0 until n)
    data(i) = d(i)
}

trait IVector {

  var n:Int = 0               // length of the vector
  var data:Array[Double] = null       // array of vector's components


  // create the zero vector of length n


  // create a vector from either an array or a vararg list
  // this constructor uses Java's vararg syntax to support
  // a constructor that takes a variable number of arguments, such as
  // Vector x = new Vector(1.0, 2.0, 3.0, 4.0)
  // Vector y = new Vector(5.0, 2.0, 4.0, 1.0)

  // the length of the vector
  def length:Int = n

  // the inner product of this Vector a and b
  def dot(that:IVector):Double = {

    if (n != that.n) throw new IllegalArgumentException("Dimensions don't agree")

    var sum = 0.0

    for (i <- 0 until n)
      sum = sum + (data(i) * that.data(i))

    sum
  }

  // the Euclidean norm of this Vector
  def magnitude:Double = Math.sqrt(dot(this))

  // the Euclidean distance between this and that
  def distanceTo(that:IVector):Double = {

    if (n != that.n) throw new IllegalArgumentException("Dimensions don't agree")

    minus(that).magnitude
  }

  // this + that
  def plus(that:IVector):IVector = {
    if (n != that.n) throw new IllegalArgumentException("Dimensions don't agree")
    val c = new Vector(n)

    for (i <- 0 until n)
      c.data(i) = data(i) + that.data(i)

    c
  }

  // this + that
  def minus(that:IVector):IVector = {
    if (n != that.n) throw new IllegalArgumentException("Dimensions don't agree")
    val c = new Vector(n)

    for (i <- 0 until n)
      c.data(i) = data(i) - that.data(i)

    c
  }

  // the corresponding coordinate
  def cartesian(i:Int):Double = data(i)

  // create and a new object whose value is (this * factor)
  def times(factor:Double):IVector = {

    val c = new Vector(n)

    for (i <- 0 until n)
      c.data(i) = factor * data(i)

    c
  }


  // the corresponding unit vector
  def direction:IVector = {
    if (magnitude == 0.0) throw new ArithmeticException("Zero-vector has no direction")
    times(1.0 / magnitude)
  }


  // a string representation of the vector
  override def toString:String = {

    var s = ""

    for (i <- 0 until n)
      s = s + data(i) + " "

    s
  }
}

object Vector {

  // test client
  def main(args:Array[String]) {

    val x = new Vectors(Array[Double](1.0, 2.0, 3.0, 4.0))
    val y = new Vectors(Array[Double](5.0, 2.0, 4.0, 1.0))

    println("   x       = " + x)
    println("   y       = " + y)

    var z = x.plus(y)
    println("   z       = " + z)

    z = z.times(10.0)
    println(" 10z       = " + z)

    println("  |x|      = " + x.magnitude)
    println(" <x, y>    = " + x.dot(y))
    println("dist(x, y) = " + x.distanceTo(y))
    println("dir(x)     = " + x.direction)

  }
}
