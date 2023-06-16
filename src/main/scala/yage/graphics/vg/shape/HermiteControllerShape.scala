package yage.graphics.vg.shape

import yage.math.Vec2
import yage.math.MathUtil
import yage.graphics.vg.Context
import yage.graphics.vg.Color
import yage.graphics.fw.Keyboard
import yage.graphics.fw.Mouse
import HermiteControllerShape.*

object HermiteControllerShape:

  var baseRadius = 12.0f
  var stemWidthFactor = 0.5f
  var headLengthFactor = 3.0f
  var headWidthFactor = 2.0f

  val normalColor = Color(0.8f, 0.8f, 0.8f)
  val highlightColor = Color(0.2f, 0.8f, 0.2f)

  enum Part:
    case None, Base, Stem, Head

class HermiteControllerShape(ctx: Context) extends Shape(ctx):

  var p1 = Vec2()
  var p2 = Vec2()
  
  private var dir = Vec2()
  private var nor = Vec2()
  private var sa = Vec2()
  private var sb = Vec2()
  private var sc = Vec2()
  private var sd = Vec2()
  private var ha = Vec2()
  private var hb = Vec2()
  private var hc = Vec2()
  
  private var highlight = Part.None
  
  def set(x1: Float, y1: Float, x2: Float, y2: Float) =
    p1.set(x1, y1)
    p2.set(x2, y2)
    update()
    this

  override def fill() =
    ctx.state.setFillColor(normalColor)
    fillBase()
    fillStem()
    fillHead()
    ctx.state.setFillColor(highlightColor)
    if highlight == Part.Base then fillBase()
    if highlight == Part.Head then fillHead()

  override def addListenersTo(k: Keyboard, m: Mouse) =
    m.movedListeners += mouseMoved
    m.draggedListeners += mouseDragged  

  override def removeListenersFrom(k: Keyboard, m: Mouse) =
    m.movedListeners -= mouseMoved
    m.draggedListeners -= mouseDragged  

  private def update() =
    val sw = baseRadius * stemWidthFactor
    val hl = baseRadius * headLengthFactor
    val hw = baseRadius * headWidthFactor
    val len = p1.distance(p2)
    dir.toDifferenceOf(p2, p1).normalize()
    nor.set(dir.y, -dir.x)
    sa.set(p1).add(nor, sw * -0.5f)
    sb.set(sa).add(dir, +len)
    sc.set(sb).add(nor, sw)
    sd.set(sc).add(dir, -len)
    ha.set(p2).add(nor, hw * -0.5f)
    hb.set(p2).add(dir, hl)
    hc.set(p2).add(nor, hw * +0.5f)

  private def fillBase() =
    ctx.path.begin()
    ctx.path.circle(p1, baseRadius)
    ctx.fill()

  private def fillStem() = 
    ctx.path.begin()
    ctx.path.moveTo(sa)
    ctx.path.lineTo(sb)
    ctx.path.lineTo(sc)
    ctx.path.lineTo(sd)
    ctx.path.close()
    ctx.fill()

  private def fillHead() =
    ctx.path.begin()
    ctx.path.moveTo(ha)
    ctx.path.lineTo(hb)
    ctx.path.lineTo(hc)
    ctx.path.close()
    ctx.fill()

  private def baseContains(x: Float, y: Float) = 
    p1.distance(x, y) < baseRadius
    
  private def headContains(x: Float, y: Float) = 
    ccw(ha, hb, x, y) && ccw(hb, hc, x, y) && ccw(hc, ha, x, y)
  
  private def mouseMoved(x: Float, y: Float, dx: Float, dy: Float) =
    if baseContains(x, y) then highlight = Part.Base
    else if headContains(x, y) then highlight = Part.Head
    else highlight = Part.None

  private def mouseDragged(x: Float, y: Float, dx: Float, dy: Float) =
    if highlight == Part.Base then 
      p1.set(p1.x + dx, p1.y + dy)
      p2.set(p2.x + dx, p2.y + dy)
      update()
    else if highlight == Part.Head then 
      p2.set(p2.x + dx, p2.y + dy)
      update()


/*
  private def stemContains(x: Float, y: Float) = 
    ccw(sa, sb, x, y) && ccw(sb, sc, x, y) && ccw(sc, sd, x, y) && ccw(sd, sa, x, y)

  private def contains(x: Float, y: Float) = 
    baseContains(x, y) || stemContains(x, y) || headContains(x, y)

*/