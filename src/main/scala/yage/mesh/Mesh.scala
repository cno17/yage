package yage.mesh

import scala.collection.mutable.ArrayBuffer

/**
 * Please note: edges contains from each edge-twin-pair only a single edge.
 */ 

class Mesh[V <: Vertex[V, E, F], E <: Edge[V, E, F], F <: Face[V, E, F]]:

  var vertices = ArrayBuffer[V]()
  var edges = ArrayBuffer[E]()
  var faces = ArrayBuffer[F]()

  // more functional!
  def isValid(): Boolean =
    def fail(msg: String) = 
      println(s"$msg")
      false
    for e <- edges do
      if e.start == null then return fail("e.start == null")
      if e.start != e.twin.end then return fail("e.start != e.twin.end")
      if e.end == null then return fail("e.end == null")
      if e.end != e.twin.start then return fail("e.end != e.twin.start")
      if e.pred == null then return fail("e.pred == null")
      if e.pred.end != e.start then return fail("e.pred.end != e.start")
      if e.pred.succ != e then return fail("e.pred.succ != e")
      if e.succ == null then return fail("e.succ == null")
      if e.succ.start != e.end then return fail("e.succ.start != e.end")
      if e.succ.pred != e then return fail("e.succ.pred != e")
      if e.twin == null then return fail("e.twin == null")
      if e.twin.twin != e then return fail("e.twin.twin != e")
      // if e.twin.succ != e.pred.twin then return fail("e.twin == null")
      if e.face == null then return fail("e.face == null")
    for v <- vertices do
      if v.edge == null then return fail("v.edge == null")
      if v.edge.start != v then return fail("v.edge.start != v")
    for f <- faces do
      if f.edge == null then return fail("f.edge == null")
      if f.edge.face != f then return fail("f.edge.face != f")
    true

