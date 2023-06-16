package yage.scene.geometry

import yage.math.Vec4

// val PI = Math.PI.toFloat
// Spheroid --> Cuboid

class Ellipsoid(radX: Float, radY: Float, radZ: Float, resU: Int, resV: Int)
extends Surface(0, PI * 2, -PI / 2, PI / 2, resU, resV):

  def this() = this(0.5f, 1.0f, 1.5f, 20, 20)

  init()
  create()

  override def pos(u: Float, v: Float) =
    val x = radX * sin(u) * cos(v)
    val y = radY * sin(v)
    val z = radZ * cos(u) * cos(v)
    Vec4(x, y, z, 1)
