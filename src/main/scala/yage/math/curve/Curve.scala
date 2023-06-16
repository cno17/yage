package yage.math.curve

import yage.math.Vec
import yage.math.MathUtil



trait Curve[V <: Vec[V]] extends MathUtil:
  
  var minT: Float
  var maxT: Float

  protected var tmpV1: V
  protected var tmpV2: V

  def c0(t: Float, res: V): V 
  def c1(t: Float, res: V): V 
  def c2(t: Float, res: V): V 

  def pos(t: Float, res: V) = c0(t, res)
  def tan(t: Float, res: V) = c1(t, res)
  def nor(t: Float, res: V) = c2(t, res)

  def curvature(t: Float) =
    c1(t, tmpV1)
    c2(t, tmpV2)
    val d11 = tmpV1.dot(tmpV1)
    val d12 = tmpV1.dot(tmpV2)
    val d22 = tmpV2.dot(tmpV2)
    sqrt(d12 * d22 - d12 * d12) / pow(d11, 3)

  // todo: how to compute this in R^n?  
  def torsion(t: Float) = 0

  def length(t1: Float, t2: Float, n: Int) =
    val dt = (t2 - t1) / n
    var res = 0.0f
    for i <- 0 to n - 1 do 
      pos(t1 + (i + 0) * dt, tmpV1)
      pos(t1 + (i + 1) * dt, tmpV2)
      res += tmpV1.distance(tmpV2)
    res

  def length(n: Int): Float = length(minT, maxT, n)

  def timeToLength(t: Float, n: Int): Float = length(minT, t, n)

  def lengthToTime(s: Float, n: Int): Float =
    if s <= 0 then return minT
    if s >= length(n) then return maxT
    // todo
    0
