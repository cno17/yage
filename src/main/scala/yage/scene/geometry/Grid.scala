package yage.scene.geometry

import yage.math.MathUtil
import yage.math.Vec4

class Grid(radX: Float, radY: Float, numU: Int, numV: Int)
extends Surface(-1, 1, -1, 1, numU, numV):

  def this() = this(1, 2, 5, 10)

  init()
  create()

  override def pos(u: Float, v: Float) =
    val x = u * radX
    val y = v * radY
    Vec4(x, y, 0, 1)
