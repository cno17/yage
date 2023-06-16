package yage.mesh

/**
 * A vertex stores an outgoing edge.  
 */

trait Vertex[V <: Vertex[V, E, F], E <: Edge[V, E, F], F <: Face[V, E, F]](
  var edge: E
) extends Item
