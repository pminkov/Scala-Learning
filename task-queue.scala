

import scala.actors.Futures._
import scala.util.Random

val r = new Random()

val tasks = for (i <- 1 to 100) yield future {
  println("Executing task " + i)
  val t = (r.nextDouble() * 5000.).toLong
  Thread.sleep(t)
  i * i
}

val squares = awaitAll(20000L, tasks: _*)
println("Squares: " + squares)
