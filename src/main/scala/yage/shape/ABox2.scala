package yage.shape

import yage.math.Vec2

class ABox2(min: Vec2, max: Vec2) extends ABox[Vec2](min, max):

  def this() = this(Vec2(), Vec2())
  def this(box: ABox2) = this(Vec2(box.min), Vec2(box.max))
  def this(x1: Float, y1: Float, x2: Float, y2: Float) = this(Vec2(x1, y1), Vec2(x2, y2))
