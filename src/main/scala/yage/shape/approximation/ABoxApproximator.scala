package yage.shape.approximation

import yage.math.Vec
import yage.shape.ABox

class ABoxApproximator[V <: Vec[V]] extends Approximator[V, ABox[V]]:
  
  override def get(points: Seq[V]): ABox[V] = null