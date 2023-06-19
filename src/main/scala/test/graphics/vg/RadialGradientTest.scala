package test.graphics.vg

import org.lwjgl.opengl.GL11C.glClear
import org.lwjgl.opengl.GL11C.glClearColor
import org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT
import yage.graphics.fw.App
import yage.graphics.vg.Color
import yage.graphics.vg.Paint
import yage.graphics.vg.Point
import yage.graphics.vg.RadialGradient

object RadialGradientTest extends App:

  var paint: RadialGradient = null

  override def init() =
    glClearColor(0, 0, 0, 1)
    paint = RadialGradient(ctx, Color(0.2f, 0.8f, 0.2f), Color(0.2f, 0.2f, 0.8f))

  override def draw() =
    val sx = window.innerSizeX.toFloat
    val sy = window.innerSizeY.toFloat
    glClear(GL_COLOR_BUFFER_BIT)
    paint.org.x = sx / 2 // point needs work or use Vec2
    paint.org.y = sx / 2
    paint.innerRadius = 100
    paint.outerRadius = 300
    ctx.beginFrame(sx, sy)
    ctx.state.setFillPaint(paint)
    ctx.path.begin()
    ctx.path.rect(100, 100, 200, 100)
    ctx.fill()
    ctx.path.begin()
    ctx.path.rect(300, 200, 300, 200)
    ctx.fill()
    ctx.endFrame()
