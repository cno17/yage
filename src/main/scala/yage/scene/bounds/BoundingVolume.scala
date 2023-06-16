package yage.scene.bounds

import yage.math.Vec

trait BoundingVolume[V <: Vec[V], B <: BoundingVolume[V, B]]:

  def contains(p: V): Boolean

