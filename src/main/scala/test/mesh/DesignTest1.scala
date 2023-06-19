package test.mesh

object DesignTest1:

  private class Vertex[V <: Vertex[V]]
  private class Vertex2 extends Vertex[Vertex2]

  private class VertexVertexIterator[V <: Vertex[V]](var certex: V):
    def this() = this(null.asInstanceOf[V])

  private class VertexVertexIterator2 extends VertexVertexIterator[Vertex2]

  def main(args: Array[String]) =
    val v = Vertex2()
    val it = VertexVertexIterator(v)

    println(3)
