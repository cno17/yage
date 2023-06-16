package yage.graphics.vg.shape

import yage.math.Vec2
import yage.math.MathUtil
import yage.graphics.vg.Context
import yage.graphics.vg.Color

// HalfEdgeShape
class EdgeShape(ctx: Context) extends Shape(ctx):
  
  var handleOffset = 0.6f
  var handleLength = 50.0f
  var handleWidth = 20.0f

  var p1 = Vec2()
  var p2 = Vec2()
  var dir = Vec2()
  var nor = Vec2()
  var pa = Vec2()
  var pb = Vec2()
  var pc = Vec2()

  def set(x1: Float, y1: Float, x2: Float, y2: Float) =
    p1.set(x1, y1)
    p2.set(x2, y2)
    update()

  def contains(x: Float, y: Float) = false

  override def draw() =
    ctx.path.begin()
    ctx.path.moveTo(p1.x, p1.y)
    ctx.path.lineTo(p2.x, p2.y)
    ctx.stroke()
    ctx.path.begin()
    ctx.path.moveTo(pa.x, pa.y)
    ctx.path.lineTo(pb.x, pb.y)
    ctx.path.lineTo(pc.x, pc.y)
    ctx.path.close()
    ctx.fill()

  override def fill() = {}

  private def update() =
    val len = p1.distance(p2)
    dir.toDifferenceOf(p2, p1).normalize()
    nor.set(dir.y, -dir.x) // set(dir).rotate(PI / 2)
    pa.set(p1).add(dir, len * handleOffset)
    pb.set(pa).add(dir, handleLength)
    pc.set(pa).add(nor, handleWidth)