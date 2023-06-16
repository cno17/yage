package yage.shape.approximation

import yage.math.Vec
import yage.shape.Shape

// Fitter

abstract class Approximator[V <: Vec[V], S <: Shape[V]]:

  def get(points: Seq[V]): S
