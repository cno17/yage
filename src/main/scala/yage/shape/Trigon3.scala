package yage.shape

import yage.math.Vec3

class Trigon3(p1: Vec3, p2: Vec3, p3: Vec3) extends Trigon[Vec3](p1, p2, p3):

  def this() = this(Vec3(), Vec3(), Vec3())

