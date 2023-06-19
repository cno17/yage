package test.mesh

import yage.mesh.Vertex2
import yage.mesh.iterator.VertexEdgeIterator
import yage.mesh.Edge2
import yage.mesh.Face2
import yage.mesh.Mesh2
import yage.mesh.iterator.VertexVertexIterator2

object Test:

  def main(args: Array[String]): Unit =
    val v1 = Vertex2(2, 3)
    val v2 = Vertex2(3, 5)

    val itVV = VertexVertexIterator2(v1)
    println(itVV.hasNext)
    val nv = itVV.next

    val m = Mesh2()
    m.vertices += v1
    println(m.vertices.size)
