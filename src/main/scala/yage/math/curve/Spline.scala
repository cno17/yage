package yage.math.curve

import yage.math.Vec
import scala.collection.mutable.ArrayBuffer

trait Spline[V <: Vec[V], C <: Curve[V]] extends Curve[V]:

  val segments = ArrayBuffer[C]()

  def tMin = 0
  def maxT = 0
