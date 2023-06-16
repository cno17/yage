package yage.mesh

/**
 * Edge is the workhorse of the twin edge data structure. It stores all the
 * fields for easy navigation between incident and adjacent mesh items.
 * 
 * Why can't end() and succ() be implemented here?
 */

trait Edge[V <: Vertex[V, E, F], E <: Edge[V, E, F], F <: Face[V, E, F]](
  var start: V,
  var pred: E,
  var twin: E,
  var face: F
) extends Item:

  def end: V
  def succ: E
