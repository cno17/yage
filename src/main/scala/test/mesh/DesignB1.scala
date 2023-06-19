package test.mesh

object DesignB1:

  private trait Item
  
  private trait Vertex extends Item:    
    type E <: Edge
    var edge: E    

  private trait Edge extends Item:
    type V <: Vertex
    type E <: Edge
    type F <: Face
    var start: V
    def end = twin.start
    var pred: E
    def succ = twin.pred
    var twin: E
    var face: F

  private trait Face extends Item:
    type E <: Edge
    var edge: E  
  

  def main(args: Array[String]) =
    println(2)
