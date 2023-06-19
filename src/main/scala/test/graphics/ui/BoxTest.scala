package test.graphics.ui

import yage.graphics.fw.App
import yage.graphics.fw.AppCreateInfo
import yage.graphics.fw.EventMode
import yage.math.Vec2
import yage.graphics.vg.Color
import scala.util.Random
import yage.graphics.vg.shape.TrigonShape
import yage.graphics.ui.BoxShape

object BoxTest extends App:

  var box: BoxShape = null

  override def info() =
    val res = AppCreateInfo()
    res.sizeX = 1200
    res.sizeY = 900
    res.eventMode = EventMode.Wait
    res

  override def init() =
    box = BoxShape(ctx, Vec2(300, 100), Vec2(700, 300))
    box.addListenersTo(keyboard, mouse)
    ctx.state.setClearColor(Color(0, 0, 0))

  override def draw() =
    val sx = window.innerSizeX
    val sy = window.innerSizeY
    ctx.clear()
    ctx.beginFrame(sx, sy)
    box.draw()
    ctx.endFrame()
