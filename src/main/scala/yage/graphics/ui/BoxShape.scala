package yage.graphics.ui

import yage.graphics.vg.Context
import yage.math.Vec2
import yage.graphics.vg.Color
import yage.graphics.vg.shape.Shape
import yage.graphics.vg.shape.TrigonShape
import BoxShape.*
import yage.graphics.fw.Keyboard
import yage.graphics.fw.Mouse

// a resizeable box

object BoxShape:

  var handleLength = 20f

  var colorD = Color(0.2f, 0.8f, 0.2f)
  var colorF = Color(0.2f, 0.2f, 0.8f)
  val colorH = Color(0.8f, 0.2f, 0.2f) // highlight


class BoxShape(ctx: Context, var min: Vec2, var max: Vec2) extends Shape(ctx):

  val nw = TrigonShape.nw(ctx, min.x, min.y, handleLength)
  val sw = TrigonShape.sw(ctx, min.x, max.y, handleLength)
  val se = TrigonShape.se(ctx, max.x, max.y, handleLength)
  val ne = TrigonShape.ne(ctx, max.x, min.y, handleLength)

  var highlight: TrigonShape = null

  override def draw() =
    ctx.state.setStrokeColor(colorD)
    ctx.path.begin()
    ctx.path.rect(min.x, min.y, max.x - min.x, max.y - min.y)
    ctx.stroke()
    ctx.state.setFillColor(colorF)
    nw.draw()
    sw.draw()
    se.draw()
    ne.draw()
    ctx.state.setStrokeColor(colorH)
    ctx.state.setFillColor(colorH)
    if highlight == nw then nw.fill()
    if highlight == sw then sw.fill()
    if highlight == se then se.fill()
    if highlight == ne then ne.fill()

  override def addListenersTo(k: Keyboard, m: Mouse) =
    m.movedListeners += mouseMoved
    m.draggedListeners += mouseDragged  

  override def removeListenersFrom(k: Keyboard, m: Mouse) =
    m.movedListeners -= mouseMoved
    m.draggedListeners -= mouseDragged  

  private def mouseMoved(x: Float, y: Float, dx: Float, dy: Float) =
    highlight = null
    if nw.contains(x, y) then highlight = nw
    if sw.contains(x, y) then highlight = sw
    if se.contains(x, y) then highlight = se
    if ne.contains(x, y) then highlight = ne

  private def mouseDragged(x: Float, y: Float, dx: Float, dy: Float) =
    if highlight == nw then 
      min.x += dx
      min.y += dy
      nw.translate(dx, dy)
      sw.translate(dx, 0)
      ne.translate(0, dy)
    if highlight == sw then 
      min.x += dx
      max.y += dy
      sw.translate(dx, dy)
      nw.translate(dx, 0)
      se.translate(0, dy)
    if highlight == se then 
      max.x += dx
      max.y += dy
      se.translate(dx, dy)
      ne.translate(dx, 0)
      sw.translate(0, dy)
    if highlight == ne then 
      max.x += dx
      min.y += dy
      ne.translate(dx, dy)
      se.translate(dx, 0)
      nw.translate(0, dy)
