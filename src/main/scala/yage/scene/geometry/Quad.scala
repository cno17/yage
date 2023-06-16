package yage.scene.geometry

import yage.math.Vec2
import yage.math.Vec4

/**
 * A quad in the xy-plane. Normals could be a static attribute!
 */

object Quad:

  def apply(rx: Float, ry: Float): Quad = 
    apply(0, 0, rx, ry)

  def apply(cx: Float, cy: Float, rx: Float, ry: Float): Quad =
    val sw = Vec4(cx - rx, cy - ry, 0, 1)
    val se = Vec4(cx + rx, cy - ry, 0, 1)
    val ne = Vec4(cx + rx, cy + ry, 0, 1)
    val nw = Vec4(cx - rx, cy + ry, 0, 1)
    new Quad(sw, se, ne, nw)

class Quad(sw: Vec4, se: Vec4, ne: Vec4, nw: Vec4) extends Geometry:

  override def init() =
    posA = Array(sw, se, ne, nw)
    norA = Array(Vec4(0, 0, 1, 0), Vec4(0, 0, 1, 0), Vec4(0, 0, 1, 0), Vec4(0, 0, 1, 0))
    tecA = Array(Vec2(0, 0), Vec2(1, 0), Vec2(1, 1), Vec2(0, 1))
    indA = Array(0, 1, 2, 2, 3, 0)
