package test.graphics.vg.pure
import java.io.File
import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVGGL3
import org.lwjgl.opengl.GL11C.*
import yage.graphics.fw.App

object GlyphPositionTest extends App:

  var ctxp: Long = 0
  var color1: NVGColor = null
  var color2: NVGColor = null
  var text = "Hello world!"

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

    // nvgTextAlign(ctx, NVG_ALIGN_LEFT | NVG_ALIGN_MIDDLE)
    
    nvgBeginPath(ctxp)
    nvgFillColor(ctxp, color1)
    nvgFontBlur(ctxp, 5.0f)
    nvgFontSize(ctxp, 80.0f)
    nvgFontFace(ctxp, "font1")
    nvgText(ctxp, 100, 100, "Hello World!")
    
    nvgFillColor(ctxp, color2)
    nvgFontBlur(ctxp, 0.0f)
    nvgFontSize(ctxp, 40.0f)
    nvgFontFace(ctxp, "font2")
    nvgTextBox(ctxp, 100, 300, 200, "Hello Africa and Europe!")
    nvgClosePath(ctxp)

    nvgEndFrame(ctxp)
