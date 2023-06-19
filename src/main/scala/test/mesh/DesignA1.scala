package test.mesh

object DesignA1:

  private class Item
  
  private class Vertex[V <: Vertex[V, E, F], E <: Edge[V, E, F], F <: Face[V, E, F]] extends Item
  
  private class Edge[V <: Vertex[V, E, F], E <: Edge[V, E, F], F <: Face[V, E, F]] extends Item:
    
    var start: V = _
    def end: V = twin.start
    var pred: E = _
    def succ: E = twin.pred
    var twin: E = _
  
  private class Face[V <: Vertex[V, E, F], E <: Edge[V, E, F], F <: Face[V, E, F]] extends Item


  def main(args: Array[String]) =
    println(3)
