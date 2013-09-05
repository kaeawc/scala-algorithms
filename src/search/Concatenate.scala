package search

case class Concatenate { 

    def main(args:Array[String]) { 
        Out out = new Out(args[args.length - 1])
        for (Int i = 0 i < args.length - 1 i++) {
            In in = new In(args(i))
            String s = in.readAll()
            out.println(s)
            in.close()
        }
        out.close()
    }

}
