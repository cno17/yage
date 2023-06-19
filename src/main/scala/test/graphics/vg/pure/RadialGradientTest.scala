package test.graphics.vg.pure
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVGGL3

import yage.graphics.fw.App
import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NVGPaint

object RadialGradientTest extends App:

  var ctxp: Long = 0
  var color1: NVGColor = null
  var color2: NVGColor = null
  var paint: NVGPaint = null

  override def init() =
    glClearColor(0, 0, 0, 1)
    ctxp = NanoVGGL3.nvgCreate(0)
    color1 = NVGColor.create()
    color2 = NVGColor.create()
    paint = NVGPaint.create()
    nvgRGBf(0.2f, 0.8f, 0.2f, color1)
    nvgRGBf(0.2f, 0.2f, 0.8f, color2)
    
  override def draw() =
    val sx = window.innerSizeX.toFloat
    val sy = window.innerSizeY.toFloat
    glClear(GL_COLOR_BUFFER_BIT)

    nvgRadialGradient(ctxp, sx / 2, sy / 2, 100, 300, color1, color2, paint)    
    
    nvgBeginFrame(ctxp, sx, sy, 1)

    nvgBeginPath(ctxp)
    nvgFillPaint(ctxp, paint)
    // nvgRect(ctx, 50, 50, sx - 100, sy - 100)
    nvgRect(ctxp, 100, 100, 200, 100)
    nvgFill(ctxp)
    nvgClosePath(ctxp)

    nvgBeginPath(ctxp)
    nvgFillPaint(ctxp, paint)
    nvgRect(ctxp, 300, 200, 300, 200)
    nvgFill(ctxp)
    nvgClosePath(ctxp)

    nvgEndFrame(ctxp)

