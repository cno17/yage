package yage.shape.distance

import yage.math.Vec
import yage.shape.{ABox, Ray}

abstract class RayABoxDistance[V <: Vec[V]] extends Distance[V, Ray[V], ABox[V]]:

  override def get(ray: Ray[V], box: ABox[V]) = info
