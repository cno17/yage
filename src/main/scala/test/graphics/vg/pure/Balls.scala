package test.graphics.vg.pure
import java.io.File
import java.nio.ByteBuffer
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.BufferUtils
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVGGL3

import yage.graphics.fw.App
import org.lwjgl.nanovg.NVGColor
import yage.math.Vec2
import scala.collection.mutable.ArrayBuffer
import scala.util.Random
import yage.graphics.fw.AppCreateInfo

object Balls extends App:

  var ctxp: Long = 0
  var color1: NVGColor = null
  var color2: NVGColor = null
  var balls: Array[Ball] = null

  override def info() =
    val res = AppCreateInfo()
    res.sizeX = 1200
    res.sizeY = 900
    res

  override def init() =
    ctxp = NanoVGGL3.nvgCreate(0)
    color1 = NVGColor.create()
    color2 = NVGColor.create()
    nvgRGBf(0.2f, 0.8f, 0.2f, color1)
    nvgRGBf(0.8f, 0.8f, 0.8f, color2)
    glClearColor(0, 0, 0, 1)
    balls = createBalls(20)


  override def draw() =
    val sx = window.innerSizeX.toFloat
    val sy = window.innerSizeY.toFloat
    glClear(GL_COLOR_BUFFER_BIT)
    nvgBeginFrame(ctxp, sx, sy, 1)
    nvgFillColor(ctxp, color1)
    nvgStrokeWidth(ctxp, 2.0f)
    nvgStrokeColor(ctxp, color2)
    nvgBeginPath(ctxp)
    balls.foreach(_.draw(ctxp))
    nvgFill(ctxp)
    nvgStroke(ctxp)
    nvgClosePath(ctxp)
    nvgEndFrame(ctxp)

  override def step(dt: Int) =
    val sx = window.innerSizeX.toFloat
    val sy = window.innerSizeY.toFloat
    balls.foreach(_.step(sx, sy, dt))

  def createBalls(n: Int) =
    val sx = window.innerSizeX
    val sy = window.innerSizeY
    val rnd = Random()
    val res = new Array[Ball](n)
    for i <- 0 to n - 1 do
      val px = sx * rnd.nextFloat()
      val py = sy * rnd.nextFloat()
      val vx = -1.0f * 2.0f * rnd.nextFloat()
      val vy = -1.0f * 2.0f * rnd.nextFloat()
      val rad = 5.0f + rnd.nextFloat() * 20.0f
      res(i) = Ball(Vec2(px, py), Vec2(vx, vy), rad)
    res

  class Ball(val pos: Vec2, val vel: Vec2, val rad: Float):

    def draw(ctx: Long) =
      nvgCircle(ctx, pos.x, pos.y, rad)

    def step(sx: Float, sy: Float, dt: Int) =
      pos += (vel, 1f)
      if pos.x < 0 || pos.x > sx then vel.x *= -1
      if pos.y < 0 || pos.y > sy then vel.y *= -1