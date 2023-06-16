package yage.shape

import yage.math.Vec

class Segment[V <: Vec[V]](var p1: V, var p2: V) extends Shape[V]
