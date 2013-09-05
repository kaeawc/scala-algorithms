package fundamentals

import java.nio.file.{Files, FileSystems}
import java.nio.charset.Charset
import java.io.IOException

object Data {

  def argsToInts(args:Array[String]):Array[Int] = {

    args.toList.foldLeft(List[Int]()) {
      (list,arg) =>
        val values = arg match {
          case number:Int => { List[Int](number) }
          case filename:String => fromFile(filename)
          case _ => throw new IllegalArgumentException("Can't understand argument.")
        }

        list ::: values
    }.toArray
  }

  def fromFile(filename:String,path:String = "./") = {

    val path = FileSystems.getDefault.getPath(path, filename)
    val file = path.toFile

    val charset = Charset.forName("UTF-8");
    if (!file.canRead) throw new java.io.IOException("Can't read this file, don't have permission.")

    val reader = Files.newBufferedReader(path, charset)

    if (!reader.ready) throw new IOException("File buffered reader not yet ready.")

    val list = List[Int]()

    for ( line <- reader.readLine() ) {
      list :+ line.toInt
    }

    list
  }
}
