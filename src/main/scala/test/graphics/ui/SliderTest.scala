package test.graphics.ui

import yage.graphics.fw.App
import yage.graphics.fw.AppCreateInfo
import yage.graphics.fw.EventMode
import yage.math.Vec2
import yage.graphics.vg.Color
import scala.util.Random
import yage.graphics.vg.shape.TrigonShape
import yage.graphics.ui.BoxShape
import yage.graphics.ui.Slider

object SliderTest extends App:

  var slider: Slider = null

  override def info() =
    val res = AppCreateInfo()
    res.sizeX = 1200
    res.sizeY = 900
    res.eventMode = EventMode.Wait
    res

  override def init() =
    slider = Slider(ctx, 0, 100, 20)
    slider.valueChangedListeners += valueChanged
    slider.addListenersTo(keyboard, mouse)
    ctx.state.setClearColor(Color(0, 0, 0))

  override def draw() =
    val sx = window.innerSizeX
    val sy = window.innerSizeY
    ctx.clear()
    ctx.beginFrame(sx, sy)
    slider.paint()
    ctx.endFrame()

  def valueChanged(curVal: Float) = println(curVal.toInt)  