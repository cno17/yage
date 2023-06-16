package yage.shape.polygon

class Edge[E <: Edge[E, V], V <: Vertex[V, E]](var start: V, var end: V) extends Feature[E, V]:
  
  override def pred = start.in
  override def succ = end.out
