package test.graphics.vg.shape

import org.lwjgl.opengl.GL11C.glClear
import org.lwjgl.opengl.GL11C.glClearColor
import org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT
import yage.graphics.fw.App
import yage.graphics.vg.Color
import yage.graphics.vg.Paint
import yage.graphics.vg.Point
import yage.graphics.vg.RadialGradient
import yage.graphics.vg.shape.EdgeShape

object EdgeShapeTest extends App:

  var color: Color = null
  var shape: EdgeShape = null

  override def init() =
    color = Color(0.8f, 0.8f, 0.8f)
    shape = EdgeShape(ctx)
    shape.set(100, 100, 500, 200)
    glClearColor(0, 0, 0, 1)

  override def draw() =
    val sx = window.innerSizeX
    val sy = window.innerSizeY
    glClear(GL_COLOR_BUFFER_BIT)
    ctx.beginFrame(sx, sy)
    ctx.state.setStrokeColor(color)
    shape.draw()
    ctx.endFrame()
