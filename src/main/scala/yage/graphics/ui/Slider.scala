package yage.graphics.ui

import yage.graphics.vg.Context
import yage.math.Vec2
import yage.graphics.fw.Keyboard
import yage.graphics.fw.Mouse
import yage.graphics.vg.Color
import Slider.*
import yage.graphics.ui.Slider
import scala.collection.mutable.ArrayBuffer

// todo: box

object Slider:

  val minColor = Color(0.4f, 0.8f, 0.4f)
  val maxColor = Color(0.4f, 0.4f, 0.8f)
  val normalColor = Color(0.8f, 0.8f, 0.8f)
  val focusedColor = Color(1.0f, 1.0f, 1.0f)

  val trackRadius = 5.0f
  val knobRadius = 12.0f

  protected class Track(ctx: Context, slider: Slider) extends Widget(ctx):

    var n1 = Vec2()
    var s1 = Vec2()
    var nc = Vec2()
    var sc = Vec2()
    var n2 = Vec2()
    var s2 = Vec2()

    override def update() =
      n1.set(slider.minPos).add(slider.nor, -trackRadius)
      s1.set(slider.minPos).add(slider.nor, +trackRadius)
      nc.set(slider.curPos).add(slider.nor, -trackRadius)
      sc.set(slider.curPos).add(slider.nor, +trackRadius)
      n2.set(slider.maxPos).add(slider.nor, -trackRadius)
      s2.set(slider.maxPos).add(slider.nor, +trackRadius)

    override def paint() =
      ctx.path.begin()
      ctx.path.moveTo(n1)
      ctx.path.lineTo(s1)
      ctx.path.lineTo(sc)
      ctx.path.lineTo(nc)
      ctx.path.close()
      ctx.path.circle(slider.minPos, trackRadius)
      ctx.state.setFillColor(minColor)
      ctx.fill()
      ctx.path.begin()
      ctx.path.moveTo(nc) 
      ctx.path.lineTo(sc)
      ctx.path.lineTo(s2)
      ctx.path.lineTo(n2)
      ctx.path.close()
      ctx.path.circle(slider.maxPos, trackRadius)
      ctx.state.setFillColor(maxColor)
      ctx.fill()

  protected class Knob(ctx: Context, slider: Slider) extends Widget(ctx):

    override def paint() =
      ctx.path.begin()
      ctx.path.circle(slider.curPos, knobRadius)
      if slider.focus then ctx.state.setFillColor(focusedColor) 
      else ctx.state.setFillColor(normalColor)
      ctx.fill()
      
class Slider(ctx: Context, val minVal: Float, val maxVal: Float, var curVal: Float) extends Widget(ctx):
        
  var minPos = Vec2()
  var maxPos = Vec2()
  var curPos = Vec2()      

  var dir = Vec2()      
  var nor = Vec2() 

  var focus = false
  
  var track = Track(ctx, this)
  var knob = Knob(ctx, this)

  var valueChangedListeners = ArrayBuffer[(Float) => Unit]()

  setMinMaxPos(100, 100, 600, 100)

  def setMinMaxPos(minX: Float, minY: Float, maxX: Float, maxY: Float) =
    minPos.set(minX, minY)
    maxPos.set(maxX, maxY)
    dir.set(maxPos.x - minPos.x, maxPos.y - minPos.y).normalize()
    nor.set(minPos.y - maxPos.y, maxPos.x - minPos.x).normalize()
    updatePos()
    track.update()

  def setCurVal(v: Float) =
    curVal = v
    if curVal < minVal then curVal = minVal
    if curVal > maxVal then curVal = maxVal
    updatePos()
    track.update()

  // TODO override def box(): Abox2   

  override def paint() =
    track.paint()
    knob.paint()
  
  override def addListenersTo(k: Keyboard, m: Mouse) =
    m.movedListeners += mouseMoved
    m.draggedListeners += mouseDragged  

  override def removeListenersFrom(k: Keyboard, m: Mouse) =
    m.movedListeners -= mouseMoved
    m.draggedListeners -= mouseDragged  

  private def updateVal() =
    val f = curPos.distance(minPos) / maxPos.distance(minPos)
    curVal = minVal + (maxVal - minVal) * f

  private def updatePos() =
    val f = (curVal - minVal) / (maxVal - minVal)
    curPos.x = minPos.x + (maxPos.x - minPos.x) * f
    curPos.y = minPos.y + (maxPos.y - minPos.y) * f

  private def mouseMoved(x: Float, y: Float, dx: Float, dy: Float) =
    focus = curPos.distance(x, y) < knobRadius

  private def mouseDragged(x: Float, y: Float, dx: Float, dy: Float) =
    if focus == true then 
      val dot = dir.x * dx + dir.y * dy
      curPos.add(dir, dot)
      if curPos.distance(minPos) > maxPos.distance(minPos) then 
        curPos.set(maxPos)
        // focus = false
      if curPos.distance(maxPos) > minPos.distance(maxPos) then 
        curPos.set(minPos)
        // focus = false
      updateVal()
      track.update()
      valueChangedListeners.foreach(_(curVal))

