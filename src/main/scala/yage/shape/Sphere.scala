package yage.shape

import yage.math.Vec

class Sphere[V <: Vec[V]](var center: V, var radius: Float) extends Shape[V]:

  def volume = 0.0f

  def contains(p: V) = p.distanceSquared(center) <= radius * radius

  override def toString() = s"($center, $radius)"