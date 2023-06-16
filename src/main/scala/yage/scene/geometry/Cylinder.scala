package yage.scene.geometry

import yage.math.Vec4

/**
 * radB = bottom radius radT = top radius
 */

class Cylinder(val radB: Float, var radT: Float, var height: Float, resU: Int, resV: Int)
extends Surface(0, PI * 2, 0, 1, resU, resV):

  def this() = this(0.5f, 0.2f, 1.0f, 32, 4)
  
  init()
  create()

  override def pos(u: Float, v: Float) =
    val x = (v * radT + (1 - v) * radB) * sin(u)
    val y = height * (v - 0.5f)
    val z = (v * radT + (1 - v) * radB) * cos(u)
    Vec4(x, y, z, 1)
