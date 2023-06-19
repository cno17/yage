package test.graphics.vg.pure

import java.io.File
import java.nio.ByteBuffer
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.BufferUtils
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVGGL3

import yage.graphics.fw.App
import org.lwjgl.nanovg.NVGColor

object ArcToTest extends App:

  var ctxp: Long = 0
  var color1: NVGColor = null
  var color2: NVGColor = null

  override def init() =
    glClearColor(0, 0, 0, 1)
    ctxp = NanoVGGL3.nvgCreate(0)
    color1 = NVGColor.create()
    color2 = NVGColor.create()
    nvgRGBf(0.8f, 0.2f, 0.2f, color1)
    nvgRGBf(0.2f, 0.8f, 0.2f, color2)

  override def draw() =
    val sx = window.innerSizeX.toFloat
    val sy = window.innerSizeY.toFloat
    glClear(GL_COLOR_BUFFER_BIT)
    nvgBeginFrame(ctxp, sx, sy, 1)
    nvgStrokeColor(ctxp, color1)
    nvgStrokeWidth(ctxp, 5)
    nvgBeginPath(ctxp)
    nvgMoveTo(ctxp, 400, 300)
    nvgArcTo(ctxp, 300, 300, 200, 200, 150)
    // nvgClosePath(ctx)
    nvgStroke(ctxp)
    nvgEndFrame(ctxp)


