package test.graphics.vg

import org.lwjgl.opengl.GL11C.*
import yage.graphics.fw.App
import yage.graphics.vg.Color
import yage.graphics.vg.Path
import yage.graphics.vg.PathWinding

object WindingTest extends App:

  var color = Color(0.2f, 0.8f, 0.2f)

  override def init() =
    glClearColor(0, 0, 0, 1)

  override def draw() =
    val sx = window.innerSizeX
    val sy = window.innerSizeY
    glClear(GL_COLOR_BUFFER_BIT)
    ctx.beginFrame(sx, sy)
    ctx.state.setFillColor(color)
    ctx.state.setFontSize(5)
    ctx.path.begin()
    ctx.path.rect(100, 100, 400, 300)
    ctx.state.pathWinding(PathWinding.CW) // mark circle as hole
    ctx.path.circle(300, 250, 50)
    ctx.fill()
    ctx.endFrame()
