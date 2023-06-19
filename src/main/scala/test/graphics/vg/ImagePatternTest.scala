package test.graphics.vg

import org.lwjgl.opengl.GL11C.glClear
import org.lwjgl.opengl.GL11C.glClearColor
import org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT
import yage.graphics.fw.App
import yage.graphics.vg.Color
import yage.graphics.vg.Paint
import yage.graphics.vg.Point
import yage.graphics.vg.ImageFlags
import yage.graphics.vg.Image
import yage.graphics.vg.ImagePattern
import yage.graphics.vg.RadialGradient
import java.io.File

object ImagePatternTest extends App:

  var img: Image = null
  var paint: ImagePattern = null

  override def init() =
    val flags = ImageFlags()
    img = Image(ctx, File(resDir, "icon/Tree-96.png"), flags)
    paint = ImagePattern(img)
    ctx.state.setClearColor(Color(0, 0, 0))

  override def draw() =
    val sx = window.innerSizeX
    val sy = window.innerSizeY
    paint.px = 100
    paint.py = 100
    paint.sx = img.sizeI.toFloat * 3
    paint.sy = img.sizeJ.toFloat
    ctx.clear()
    ctx.beginFrame(sx, sy)
    ctx.state.setFillPaint(paint)
    ctx.path.begin()
    ctx.path.rect(100, 100, img.sizeI.toFloat * 2, img.sizeJ.toFloat)
    ctx.fill()
    ctx.path.begin()
    ctx.path.rect(300, 200, 300, 200)
    ctx.fill()
    ctx.endFrame()
