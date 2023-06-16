package yage.math.curve

import yage.math.Vec

trait HermiteCurve[V <: Vec[V]] extends Curve[V]:

  var pos0: V
  var tan0: V
  var pos1: V
  var tan1: V

  override def c0(t: Float, res: V) =
    val t0 = t
    val t1 = t0 * t
    val t2 = t1 * t
    val t3 = t2 * t
    res.toZero()
    res.add(pos0, 2 * t3 - 3 * t2 + 1)
    res.add(tan0, t3 - 3 * t2 + t0)
    res.add(pos1, -2 * t3 + 3 * t2)
    res.add(tan1, t3 - t2)
    res