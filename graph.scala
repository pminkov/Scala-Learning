/**
 * Graph breadth first traversal.
 */

case class EdgeList(v: Int, adj: List[Int]) {}

case class Graph(graph: List[EdgeList]) {
  val edges: Map[Int, EdgeList] = graph.map(l => (l.v, l)).toMap
}


class BFSTraverser(graph: Graph) {
  private type Visitor = (Int) => Unit

  def traverse(start: Int, visitor: Visitor): Unit = {
    doTraverse(graph, Set(start), List(start), visitor)
  }

  private def doTraverse(graph: Graph, visited: Set[Int], front: List[Int], visitor: Visitor): Unit = {
    println("V: " + visited + " F: " + front)

    // We're starting to construct a new front from nothing and we keep track of 
    // the currently visited vertices.
    val startState = (visited, List.empty[Int])
    
    val (newVisited, newFront) = front.foldLeft(startState)((state, v) => {
      visitor(v)

      val (curVisited, curFront) = state
      val adjNotVisited: List[Int] = graph.edges(v).adj.filterNot(v => curVisited.contains(v))

      // Mark new vertices as visited and add them to the front.
      (adjNotVisited.toSet ++ curVisited, adjNotVisited ++ curFront)
    })

    println

    if (newFront.size > 0) {
      doTraverse(graph, newVisited, newFront, visitor)
    } else {
      println("Terminating. All visited: " + visited)
    }
  }
}

def visit(v: Int): Unit = {
  print(v + " ")
}

val edges1 = EdgeList(1, List(2, 3))
val edges2 = EdgeList(2, List(5))
val edges3 = EdgeList(3, List(5))
val edges5 = EdgeList(5, List(1))

val g = List(edges1, edges2, edges3, edges5)
val graph = Graph(g)

val traverser = new BFSTraverser(graph)

traverser.traverse(1, visit)
