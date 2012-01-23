class Graph(n: Int) {
  var edges = new Array[List[Int]](n)

  for (i <- 0 until n) {
    edges(i) = List[Int]()
  }

  def addEdge(from: Int, to: Int) = {
    edges(from) = to :: edges(from)
  }

  def printGraph() = {
    for (i <- 0 until n) {
      print(i + " ->")
      edges(i).foreach((x: Int) => {
        print(" " + x)
      })
      println
    }
  }
}


var g = new Graph(5)
g.addEdge(0, 1)
g.addEdge(1, 2)
g.addEdge(2, 3)
g.addEdge(0, 3)
g.addEdge(3, 4)

//g.printGraph()

val q = new Queue[Int]()

/*

var a = List[Int]()
a = 5 :: a
a = 6 :: a
println(a.length)
//var g = new Graph(5)

*/
