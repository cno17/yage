package yage.shape

import yage.math.Vec
import yage.math.Vec2
import yage.math.Vec3

class ABox[V <: Vec[V]](var min: V, var max: V) extends Shape[V] :

  def set(box: ABox[V]): ABox[V] = set(box.min, box.max)

  def set(min: V, max: V) =
    this.min := min
    this.max := max
    this

  def center(ret: V) = ret.set(min).add(max, 0.5)
  def radius(ret: V) = ret.set(max).subtract(min, 0.5)
  def extent(ret: V) = ret.set(max).subtract(min)

  def center(i: Int) = (min(i) + max(i)) / 2
  def radius(i: Int) = (max(i) - min(i)) / 2
  def extent(i: Int) = (max(i) - min(i))

  // TODO
  def vertices(ret: Array[V]) = ret

  override def toString() = s"($min, $max)"

  def :=(box: ABox[V]) = set(box)
  def :=(min: V, max: V) = set(min, max)


