package test.mesh

import yage.math.Vec2

object DesignA3:

  private trait Item
  
  private trait Vertex[V <: Vertex[V, E, F], E <: Edge[V, E, F], F <: Face[V, E, F]] extends Item:
  	var edge: E = _

  private trait Edge[V <: Vertex[V, E, F], E <: Edge[V, E, F], F <: Face[V, E, F]] extends Item:
    var start: V = _
    def end: V = twin.start
    var pred: E = _
    def succ: E = twin.pred
    var twin: E = _
    var face: F = _
  
  private trait Face[V <: Vertex[V, E, F], E <: Edge[V, E, F], F <: Face[V, E, F]] extends Item:
    var edge: E = _

//

  private class Vertex2 extends Vec2, Vertex[Vertex2, Edge2, Face2]

  private class Edge2 extends Edge[Vertex2, Edge2, Face2]:

    def length = start.distance(end)

  private class Face2 extends Face[Vertex2, Edge2, Face2]


  def main(args: Array[String]) =
    println(3)
