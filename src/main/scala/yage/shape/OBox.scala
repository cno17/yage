package yage.shape

import yage.math.Vec
import yage.math.Vec2
import yage.math.Vec3

object OBox:

  def apply(n: Int) = 0 // new OBox(n)


class OBox[V <: Vec[V]](var org: V, var dir: Array[V], var rad: Array[Float]) extends Shape[V]:

  val n = org.n
  
  def toZero =
    org.toZero()
    for i <- 0 to n - 1 do
      dir(i).toZero()
      rad(i) = 0
    this

  def toUnit =
    org.toZero()
    for i <- 0 to n - 1 do
      dir(i).toUnit(i)
      rad(i) = 1
    this

  def vertices(ret: Array[V] = null) = ret

///////////////////////////////////////////////////////////////////////////////
  
class OBox2(org: Vec2, dir: Array[Vec2], rad: Array[Float]) extends OBox[Vec2](org, dir, rad)

///////////////////////////////////////////////////////////////////////////////
  
class OBox3(org: Vec3, dir: Array[Vec3], rad: Array[Float]) extends OBox[Vec3](org, dir, rad)
