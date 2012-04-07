/**
 * Breadth first traversal.
 */

case class EdgeList(v: Int, adj: List[Int]) {
}

case class Graph(graph: List[EdgeList]) {
  val edges: Map[Int, EdgeList] = graph.map(l => (l.v, l)).toMap
}

def visit(v: Int) {
  print(v + " ")
}

def BFS_Traverse(graph: Graph, visited: Set[Int], front: List[Int]): Unit = {
  println("V: " + visited + " F: " + front)

  // We're starting to construct a new front from nothing and we keep track of 
  // the currently visited vertices.
  val startState = (visited, List.empty[Int])
  
  val (newVisited, newFront) = front.foldLeft(startState)((state, v) => {
    visit(v)

    val (curVisited, curFront) = state
    val adjNotVisited: List[Int] = graph.edges(v).adj.filterNot(v => curVisited.contains(v))

    // Mark new vertices as visited and add them to the front.
    (adjNotVisited.toSet ++ curVisited, adjNotVisited ++ curFront)
  })

  println

  if (newFront.size > 0) {
    BFS_Traverse(graph, newVisited, newFront)
  } else {
    println("Terminating. All visited: " + visited)
  }
}

def BFS(graph: Graph, start: Int): Unit = {
  BFS_Traverse(graph, Set(start), List(start))
}

val edges1 = EdgeList(1, List(2, 3))
val edges2 = EdgeList(2, List(5))
val edges3 = EdgeList(3, List(5))
val edges5 = EdgeList(5, List(1))

val g = List(edges1, edges2, edges3, edges5)
val graph = Graph(g)

BFS(graph, 1)
