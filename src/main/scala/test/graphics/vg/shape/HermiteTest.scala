package test.graphics.vg.shape

import yage.graphics.fw.App
import yage.graphics.fw.AppCreateInfo
import yage.graphics.fw.EventMode
import yage.graphics.vg.shape.HermiteControllerShape
import yage.graphics.vg.Color
import scala.util.Random

object HermiteTest extends Random, App:

  var n = 10
  var controllers = new Array[HermiteControllerShape](n)

  override def info() =
    val res = AppCreateInfo()
    res.sizeX = 1200
    res.sizeY = 900
    res.eventMode = EventMode.Wait
    res

  override def init() =
    val sx = window.innerSizeX
    val sy = window.innerSizeY
    for i <- 0 to n - 1 do
      val x1 = 100f + nextInt(sx - 200)
      val y1 = 100f + nextInt(sy - 200)
      val x2 = x1 - 100f + nextInt(200)
      val y2 = y1 - 100f + nextInt(200)
      controllers(i) = HermiteControllerShape(ctx).set(x1, y1, x2, y2)
      controllers(i).addListenersTo(keyboard, mouse)
    ctx.state.setClearColor(Color(0, 0, 0))

  override def draw() =
    val sx = window.innerSizeX
    val sy = window.innerSizeY
    ctx.clear()
    ctx.beginFrame(sx, sy)
    controllers.foreach(_.fill())
    ctx.endFrame()
