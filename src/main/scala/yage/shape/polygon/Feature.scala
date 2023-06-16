package yage.shape.polygon

/**
 * A polygon feature is either a vertex or an edge. 
 * S = self type
 * I = incident type
 */


trait Feature[S <: Feature[S, I], I <: Feature[I, S]]:

  def pred: S
  def succ: S
