package test.graphics.vg.shape

import yage.graphics.fw.App
import yage.graphics.fw.AppCreateInfo
import yage.graphics.fw.EventMode
import yage.math.Vec2
import yage.graphics.vg.Color
import scala.util.Random
import yage.graphics.vg.shape.TrigonShape

object TrigonTest extends App:

  var trigon: TrigonShape = null

  override def info() =
    val res = AppCreateInfo()
    res.sizeX = 1200
    res.sizeY = 900
    res.eventMode = EventMode.Wait
    res

  override def init() =
    trigon = TrigonShape.se(ctx, 300, 300, 200)
    ctx.state.setClearColor(Color(0, 0, 0))

  override def draw() =
    val sx = window.innerSizeX
    val sy = window.innerSizeY
    ctx.clear()
    ctx.beginFrame(sx, sy)
    trigon.fill()
    ctx.endFrame()
