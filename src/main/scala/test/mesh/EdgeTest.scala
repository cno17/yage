package test.mesh

import yage.mesh.Vertex2
import yage.mesh.iterator.VertexEdgeIterator
import yage.mesh.Edge2
import yage.mesh.Face2
import yage.mesh.Mesh2
import yage.mesh.iterator.VertexVertexIterator2

object EdgeTest:

  def main(args: Array[String]): Unit =
    val f0 = Face2()
    val f1 = Face2()
    val v0 = Vertex2(2, 3)
    val v1 = Vertex2(3, 5)
    val e = Edge2(v0, f0)
    val t = Edge2(v1, f1)
    e.twin = t
    t.twin = e
    println(e.start)
    println(e.end)
    val v = new Vertex2(e)
    println(v.edge.start)

