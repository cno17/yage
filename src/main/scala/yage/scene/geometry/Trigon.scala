package yage.scene.geometry

import yage.math.Vec2
import yage.math.Vec4

/**
 * A trigon in the xy-plane centered at the origin. Normals could be a static attribute!
 */

class Trigon(val a: Vec2, val b: Vec2, val c: Vec2) extends Geometry:

  def this() = this(Vec2(-1, -1), Vec2(+1, -1), Vec2(0, 1))

  // init()
  // create()

  override def init() =
    posA = Array(Vec4(a.x, a.y, 0, 1), Vec4(b.x, b.y, 0, 1), Vec4(c.x, c.y, 0, 1))
    norA = Array(Vec4(0, 0, 1, 0), Vec4(0, 0, 1, 0), Vec4(0, 0, 1, 0))
    indA = Array(0, 1, 2)