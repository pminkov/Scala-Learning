import scala.math

object Vector {
  // Cosine similarity.
  def similarity(v1: Vector, v2: Vector): Double = {
    v1.dotp(v2) / (v1.length() * v2.length())
  }
}

case class Vector(c: List[Double]) {
  def length(): Double = {
    math.sqrt(c.map(x => x * x).sum)
  }

  def normalize(): Vector = {
    val len = length()
    Vector(c.map(cr => cr / length))
  }

  def dotp(v: Vector): Double = {
    v.c.zip(this.c).map { case (x,y) => x * y }.sum
  }
}

val v1 = Vector(List(5, 6, 7.3))
val v2 = Vector(List(5, 6, 7.3))
val v3 = Vector(List(4, 3, 18))

println(Vector.similarity(v1, v2))
println(Vector.similarity(v1, v3))

