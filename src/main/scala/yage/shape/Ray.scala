package yage.shape

import yage.math.Vec

class Ray[V <: Vec[V]](var org: V, var dir: V) extends Shape[V]
