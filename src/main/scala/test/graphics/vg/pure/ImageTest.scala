package test.graphics.vg.pure
import java.io.File
import java.nio.ByteBuffer
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.BufferUtils
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVGGL3

import yage.graphics.fw.App
import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NVGPaint

object ImageTest extends App:

  var ctxp: Long = 0
  var img: Int = 0
  var paint: NVGPaint = null

  override def init() =
    glClearColor(0, 0, 0, 1)
    ctxp = NanoVGGL3.nvgCreate(0)
    img = nvgCreateImage(ctxp, File(resDir, "image/noir/Woman1.jpg").getAbsolutePath(), 0)
    paint = NVGPaint.create()
    nvgImagePattern(ctxp, 0, 0, 400, 300, 0, img, 1.0f, paint)
    println(img)

  override def draw() =
    val sx = window.innerSizeX.toFloat
    val sy = window.innerSizeY.toFloat
    glClear(GL_COLOR_BUFFER_BIT)
    nvgBeginFrame(ctxp, sx, sy, 1)
    nvgBeginPath(ctxp)
    nvgRect(ctxp, 100, 100, 400, 300)
    nvgFillPaint(ctxp, paint)
    nvgFill(ctxp)
    nvgClosePath(ctxp)
    nvgEndFrame(ctxp)
