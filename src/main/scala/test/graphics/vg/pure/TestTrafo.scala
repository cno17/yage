package test.graphics.vg.pure
import java.io.File
import java.nio.ByteBuffer
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.BufferUtils
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVGGL3

import yage.graphics.fw.App
import org.lwjgl.nanovg.NVGColor
import yage.graphics.fw.MouseButton
import java.nio.FloatBuffer

object TrafoTest extends App:

  var ctx2: Long = 0
  var color: NVGColor = null
  var trafo: FloatBuffer = null

  override def init() =
    mouse.draggedListeners += onMouseDragged
    ctx2 = NanoVGGL3.nvgCreate(0)
    color = NVGColor.create()
    nvgRGBf(0.2f, 0.2f, 0.8f, color)
    trafo = BufferUtils.createFloatBuffer(6)
    glClearColor(0, 0, 0, 1)

  override def draw() =
    val sx = window.innerSizeX.toFloat
    val sy = window.innerSizeY.toFloat
    glClear(GL_COLOR_BUFFER_BIT)
    nvgBeginFrame(ctx2, sx, sy, 1)
    nvgBeginPath(ctx2)
    nvgRect(ctx2, 100, 100, 200, 100)
    nvgFillColor(ctx2, color)
    nvgFill(ctx2)
    nvgClosePath(ctx2)
    nvgEndFrame(ctx2)
    println(timer.milliTime)

  def onMouseDragged(x: Double, y: Double, dx: Double, dy: Double) =
    nvgTranslate(ctx2, dx.toFloat, dy.toFloat)