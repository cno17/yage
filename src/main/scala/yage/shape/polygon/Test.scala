package yage.shape.polygon

object Test:

  abstract class Feature[T]:
    def pred: T
    def succ: T

  class Vertex[V <: Vertex[V, E], E <: Edge[V, E]](var in: E, var out: E) extends Feature[V]:
    override def pred = in.start
    override def succ = out.end

  class Edge[V <: Vertex[V, E], E <: Edge[V, E]](var start: V, var end: V) extends Feature[E]:
    override def pred = start.in
    override def succ: E = end.out

  class Polygon[V <: Vertex[V, E], E <: Edge[V, E]]:
    val vertices: List[V] = null

  def main(args: Array[String]) =
    println("ok")

