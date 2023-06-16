package yage.mesh

trait Face[V <: Vertex[V, E, F], E <: Edge[V, E, F], F <: Face[V, E, F]](
  var edge: E
) extends Item
