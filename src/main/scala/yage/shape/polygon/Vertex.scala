package yage.shape.polygon

/*
object Vertex:
  
  def apply = new Vertex(null, null)  
  def apply[E <: Edge[_]](in: E, out: E) = new Vertex(in, out)
*/

class Vertex[V <: Vertex[V, E], E <: Edge[E, V]](var in: E, var out: E) extends Feature[V, E]:

  // def Vertex() // = this(null, null)
  
  override def pred = in.start
  override def succ = out.end
