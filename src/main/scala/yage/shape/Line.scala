package yage.shape

import yage.math.Vec

class Line[V <: Vec[V]](var p1: V, var p2: V) extends Shape[V]
