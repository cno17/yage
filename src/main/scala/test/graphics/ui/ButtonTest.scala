package test.graphics.ui

import yage.graphics.fw.App
import yage.graphics.fw.AppCreateInfo
import yage.graphics.fw.EventMode
import yage.math.Vec2
import yage.graphics.vg.Color
import scala.util.Random
import yage.graphics.vg.shape.TrigonShape
import yage.graphics.ui.BoxShape
import yage.graphics.ui.Button


object ButtonTest extends App:

  var button: Button = null

  override def info() =
    val res = AppCreateInfo()
    res.sizeX = 1200
    res.sizeY = 900
    res.eventMode = EventMode.Wait
    res

  override def init() =
    button = Button(ctx, Vec2(100, 100), Vec2(200, 150))
    button.clickedListeners += buttonClicked
    button.addListenersTo(keyboard, mouse)
    ctx.state.setClearColor(Color(0, 0, 0))

  override def draw() =
    val sx = window.innerSizeX
    val sy = window.innerSizeY
    ctx.clear()
    ctx.beginFrame(sx, sy)
    button.paint()
    ctx.endFrame()

  def buttonClicked(b: Button) = println(b.state)  