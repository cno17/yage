package yage.shape.distance

import yage.math.Vec
import yage.shape.Sphere

abstract class PointSphereDistance[V <: Vec[V]] extends Distance[V, V, Sphere[V]]:

  override def get(pos: V, sph: Sphere[V]) =
    info.cp1 := pos
    // for i <- 0 to pos.n - 1 do
    // info.ds = info.p1.distanceSquared(info.p2)
    info
