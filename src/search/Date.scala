package search

case class Date(year:Int, month:Int, day:Int) extends Comparable[Date] {

  if (!Date.isValid(month, day, year)) throw new IllegalArgumentException("Invalid date")

  // the next Date
  def next:Date = {
    if (Date.isValid(year, month, day + 1))    Date(year, month, day + 1)
    else if (Date.isValid(year, month + 1, 1)) Date(year, month + 1, 1)
    else                                       Date(year + 1, 1, 1)
  }


  // is this Date after b?
  def isAfter(b:Date):Boolean = {
    compareTo(b) > 0
  }

  // is this Date a before b?
  def isBefore(b:Date):Boolean = {
    compareTo(b) < 0
  }

  // compare this Date to that one
  def compareTo(that:Date):Int = {
    if (year  < that.year)  -1
    else if (year  > that.year)  +1
    else if (month < that.month) -1
    else if (month > that.month) +1
    else if (day   < that.day)   -1
    else if (day   > that.day)   +1
    0
  }

  // a string representation of this date
  override def toString:String = {
    year + "/" + month + "/" + day
  }

  // is this Date equal to x?
  def equals(x:Object):Boolean = {
    if (x == this) true
    else if (x == null) false
    else if (!x.isInstanceOf[Date]) false
    else {
      val that = x.asInstanceOf[Date]
      (year == that.year) && (month == that.month) && (day == that.day)
    }
  }

  override def hashCode:Int = {
    var hash = 17
    hash = 31*hash + year
    hash = 31*hash + month
    hash = 31*hash + day
    hash
  }
}

object Date {

  object Months {

    val days = Array( 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 )

  }

  // create new data by parsing from string of the form mm/dd/yy
  def fromString(date:String) {

    val fields = date.split("/")

    if (fields.length != 3) throw new IllegalArgumentException("Invalid date")

    val year  = Integer.parseInt(fields(0))
    val month = Integer.parseInt(fields(1))
    val day   = Integer.parseInt(fields(2))

    Date(year,month,day)
  }

  // is the given date valid?
  def isValid(m:Int, d:Int, y:Int):Boolean = {

    if (m < 1 || m > 12)                          false
    else if (d < 1 || d > Months.days(m))         false
    else if (m == 2 && d == 29 && !isLeapYear(y)) false
    else                                          true
  }

  // is y a leap year?
  def isLeapYear(y:Int):Boolean = {

    if (y % 400 == 0)      true
    else if (y % 100 == 0) false
    else                   y % 4 == 0
  }


  // sample client for testing
  def main(args:Array[String]) {

    var today = new Date(2004, 2, 25)
    println(today)

    for (i <- 0 until 10) {
      today = today.next
      println(today)
    }

    println(today.isAfter(today.next))
    println(today.isAfter(today))
    println(today.next.isAfter(today))

    var birthday = new Date(1971, 10, 16)
    println(birthday)

    for (i <- 0 until 10) {
      birthday = birthday.next
      println(birthday)
    }
  }
}
