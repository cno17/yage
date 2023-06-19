package test.graphics.vg.pure
import java.io.File
import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVGGL3
import org.lwjgl.opengl.GL11C.*
import yage.graphics.fw.App
import org.lwjgl.BufferUtils

object BoundsTest extends App:

  var ctxp: Long = 0
  var color1: NVGColor = null
  var color2: NVGColor = null
  val text = "Ying goes yang."
  var bounds = BufferUtils.createFloatBuffer(4)

  override def init() =
    glClearColor(0, 0, 0, 1)
    ctxp = NanoVGGL3.nvgCreate(0)
    color1 = NVGColor.create()
    color2 = NVGColor.create()
    nvgRGBf(0.2f, 0.8f, 0.2f, color1)
    nvgRGBf(0.8f, 0.8f, 0.8f, color2)
    nvgCreateFont(ctxp, "font1", File(resDir, "font/Cousine-Regular.ttf").getAbsolutePath())
    nvgCreateFont(ctxp, "font2", File(resDir, "font/Roboto-Bold.ttf").getAbsolutePath())

  override def draw() =
    val sx = window.innerSizeX.toFloat
    val sy = window.innerSizeY.toFloat
    glClear(GL_COLOR_BUFFER_BIT)


    nvgBeginFrame(ctxp, sx, sy, 1)

    nvgTextAlign(ctxp, NVG_ALIGN_LEFT | NVG_ALIGN_MIDDLE)
    nvgBeginPath(ctxp)
    nvgFillColor(ctxp, color1)
    nvgFontBlur(ctxp, 0.0f)
    nvgFontSize(ctxp, 80.0f)
    nvgFontFace(ctxp, "font1")
    nvgText(ctxp, 100, 100, text)
    nvgTextBounds(ctxp, 100, 100, text, bounds)
    val x1 = bounds.get(0)
    val y1 = bounds.get(1)
    val x2 = bounds.get(2)
    val y2 = bounds.get(3)
    nvgClosePath(ctxp)
    
    // println(s"$x1 $y1 $x2 $y2")
    
    nvgBeginPath(ctxp)
    nvgStrokeWidth(ctxp, 1.0f)
    nvgStrokeColor(ctxp, color2)
    nvgRect(ctxp, x1, y1, x2 - x1, y2 - y1)
    nvgStroke(ctxp)
    nvgClosePath(ctxp)

    nvgEndFrame(ctxp)
