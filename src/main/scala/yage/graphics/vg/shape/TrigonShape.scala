package yage.graphics.vg.shape

import yage.graphics.vg.Context
import yage.math.Vec2
import yage.math.MathUtil

// extend Trigon?

object TrigonShape extends MathUtil:

  def n(ctx: Context, x: Float, y: Float, l: Float) = 
    val o = l / sqrt(2)
    TrigonShape(ctx, Vec2(x, y), Vec2(x - o, y + o), Vec2(x + o, y + o))

  def w(ctx: Context, x: Float, y: Float, l: Float) =
    val o = l / sqrt(2)
    TrigonShape(ctx, Vec2(x, y), Vec2(x + o, y + o), Vec2(x + o, y - o))

  def s(ctx: Context, x: Float, y: Float, l: Float) =
    val o = l / sqrt(2)
    TrigonShape(ctx, Vec2(x, y), Vec2(x + o, y - o), Vec2(x - o, y - o))

  def e(ctx: Context, x: Float, y: Float, l: Float) =
    val o = l / sqrt(2)
    TrigonShape(ctx, Vec2(x, y), Vec2(x - o, y - o), Vec2(x - o, y + o))

  def nw(ctx: Context, x: Float, y: Float, l: Float) = 
    TrigonShape(ctx, Vec2(x, y), Vec2(x, y + l), Vec2(x + l, y))

  def sw(ctx: Context, x: Float, y: Float, l: Float) = 
    TrigonShape(ctx, Vec2(x, y), Vec2(x + l, y), Vec2(x, y - l))

  def se(ctx: Context, x: Float, y: Float, l: Float) = 
    TrigonShape(ctx, Vec2(x, y), Vec2(x, y - l), Vec2(x - l, y))

  def ne(ctx: Context, x: Float, y: Float, l: Float) = 
    TrigonShape(ctx, Vec2(x, y), Vec2(x - l, y), Vec2(x, y + l))

class TrigonShape(ctx: Context, var p1: Vec2, var p2: Vec2, var p3: Vec2) extends Shape(ctx):

  def translate(tx: Float, ty: Float) =
    p1.set(p1.x + tx, p1.y + ty)
    p2.set(p2.x + tx, p2.y + ty)
    p3.set(p3.x + tx, p3.y + ty)

  def contains(x: Float, y: Float) =
    ccw(p1, p2, x, y) && ccw(p2, p3, x, y) && ccw(p3, p1, x, y)

  // def createPath()!

  override def fill() =
    ctx.path.begin()
    ctx.path.moveTo(p1)
    ctx.path.lineTo(p2)
    ctx.path.lineTo(p3)
    ctx.path.close()
    ctx.fill()

  override def draw() =
    ctx.path.begin()
    ctx.path.moveTo(p1)
    ctx.path.lineTo(p2)
    ctx.path.lineTo(p3)
    ctx.path.close()
    ctx.stroke()


