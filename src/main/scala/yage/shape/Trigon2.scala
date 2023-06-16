package yage.shape

import yage.math.Vec2

class Trigon2(p1: Vec2, p2: Vec2, p3: Vec2) extends Trigon[Vec2](p1, p2, p3):

  def this() = this(Vec2(), Vec2(), Vec2())

  def innerCircle() = 0
  
  // ...

