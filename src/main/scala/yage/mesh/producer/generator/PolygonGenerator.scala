package yage.mesh.producer.generator

import scala.collection.mutable.ArrayBuffer
import yage.math.Vec2
import yage.mesh.Edge2
import yage.mesh.Face2
import yage.mesh.Mesh2
import yage.mesh.Vertex2

/**
 * Create a planar mesh with a single finite face from an array of
 * counterclockwise points.
 * 
 */

class PolygonGenerator:

  def apply(ps: Array[Vec2]) =
    val n = ps.size
    val mesh = Mesh2()
    val edges = ArrayBuffer[Edge2]()
    val twins = ArrayBuffer[Edge2]()
    // create items
    val f0 = Face2()
    val f1 = Face2()
    for p <- ps do
      mesh.vertices += Vertex2(p)
    for i <- 0 to n - 1 do
      edges += Edge2(mesh.vertices(mod(i + 0, n)), f1)
      twins += Edge2(mesh.vertices(mod(i + 1, n)), f0)
    // glue items
    f0.edge = edges(0)
    f1.edge = twins(0)
    for i <- 0 to n - 1 do
      mesh.vertices(i).edge = edges(i)
      edges(i).pred = edges(mod(i - 1, n))
      edges(i).twin = twins(i)
      twins(i).pred = twins(mod(i + 1, n))
      twins(i).twin = edges(i)
    mesh.edges ++= edges
    mesh

  def mod(i: Int, n: Int) = if i < 0 then i + n else if i >= n then i - n else i
