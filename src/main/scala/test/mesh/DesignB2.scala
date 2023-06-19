package test.mesh

object DesignB:

  private trait Item:
    type V <: Vertex
    type E <: Edge
    type F <: Face
  
  private trait Vertex extends Item:    
    var edge: E    

  private trait Edge extends Item:
    var start: V
    def end = twin.start
    var pred: E
    def succ = twin.pred
    var twin: E
    var face: F

  private trait Face extends Item:
    var edge: E  
/*
  private class Vertex2 extends Vertex:
    edge = _

  private class Edge2 extends Edge
  private class Face2 extends Face
*/


  def main(args: Array[String]) =
    println(2)
