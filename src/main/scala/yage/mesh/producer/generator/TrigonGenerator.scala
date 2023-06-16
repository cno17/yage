package yage.mesh.producer.generator

import yage.math.Vec2
import yage.mesh.Mesh2

import yage.mesh.Face2
import yage.mesh.Edge2
import yage.mesh.Vertex2
import scala.collection.mutable.ArrayBuffer

/**
 * Create a planar mesh with a single finite face from an array of
 * counterclockwise points.
 * 
 */ 

class TrigonGenerator:

  def apply(p0: Vec2, p1: Vec2, p2: Vec2) =
    // create items
    val f0 = Face2()
    val f1 = Face2()
    val v0 = Vertex2(p0)
    val v1 = Vertex2(p1)
    val v2 = Vertex2(p2)
    val e0 = Edge2(v0, f1)
    val e1 = Edge2(v1, f1)
    val e2 = Edge2(v2, f1)
    val t0 = Edge2(v1, f0)
    val t1 = Edge2(v2, f0)
    val t2 = Edge2(v0, f0)
    // glue items
    f0.edge = t0
    f1.edge = e0
    v0.edge = e0
    v1.edge = e1
    v2.edge = e2
    e0.pred = e2
    e1.pred = e0
    e2.pred = e1
    e0.twin = t0
    e1.twin = t1
    e2.twin = t2
    t0.pred = t1
    t1.pred = t2
    t2.pred = t0
    t0.twin = e0
    t1.twin = e1
    t2.twin = e2
    val mesh = Mesh2()
    mesh.vertices ++= Array(v0, v1, v2)
    mesh.edges ++= Array(e0, e1, e2)
    mesh.faces ++= Array(f0, f1)
    // Mesh2(Array(v0, v1, v2), Array(e0, e1, e2), Array(f0, f1))
    mesh

  def mod(i: Int, n: Int) = if i < 0 then i + n else if i >= n then i - n else i
    