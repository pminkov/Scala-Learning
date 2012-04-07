import scala.collection.mutable.Queue

class Graph(n: Int) {
  var edges = new Array[List[Int]](n)

  for (i <- 0 until n) {
    edges(i) = List[Int]()
  }

  def nodesCount = n

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

def BFS(g: Graph, from: Int, to: Int) = {
  var queue = new Queue[Int]()
  var visited = new Array[Boolean](g.nodesCount)
  var prev = new Array[Int](g.nodesCount)

  queue += from
  visited(from) = true
  prev(from) = -1

  var found = false
  while (queue.length > 0 && !found) {
    var node = queue.dequeue

    if (node != to) {
      g.edges(node).foreach((to: Int) => {
        if (!visited(to)) {
          queue.enqueue(to)
  	  visited(to) = true
	  prev(to) = node
        }
      })
    } else {
      found = true
    }
  }

  var maybeNot = ""
  if (!found) maybeNot = "not "
  println("A path from " + from + " to " + to + " was " + maybeNot + "found.")

  if (found) {
    var path = List[Int]()
    var currentNode = to
    while (currentNode != -1) {
      path = currentNode :: path 
      currentNode = prev(currentNode)
    }
    path.foreach((node: Int) => {
      print(" " + node)
    })
    println
  }
}

var g = new Graph(5)
g.addEdge(0, 1)
g.addEdge(1, 2)
g.addEdge(2, 3)
g.addEdge(0, 3)
g.addEdge(3, 4)

BFS(g, 0, 4)
BFS(g, 4, 0)
