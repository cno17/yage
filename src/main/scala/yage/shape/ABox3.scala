package yage.shape

import yage.math.Vec3

class ABox3(min: Vec3, max: Vec3) extends ABox[Vec3](min, max):

  def this() = this(Vec3(), Vec3())
  def this(box: ABox3) = this(Vec3(box.min), Vec3(box.max))
  def this(x1: Float, y1: Float, z1: Float, x2: Float, y2: Float, z2: Float) = this(Vec3(x1, y1, z1), Vec3(x2, y2, z2))
