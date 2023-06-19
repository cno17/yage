package test.graphics.vg
import yage.graphics.fw.App
import yage.graphics.vg.Color

object RectangleTest extends App:

  val colorBG = Color(0.0f, 0.0f, 0.0f)
  val colorFG = Color(0.2f, 0.8f, 0.2f)

  override def init() =
    ctx.state.setClearColor(colorBG)
    
  override def draw() =
    val sx = window.innerSizeX
    val sy = window.innerSizeY
    ctx.clear()
    ctx.beginFrame(sx, sy)
    ctx.state.setFillColor(colorFG)
    ctx.path.begin()
    ctx.path.rect(100, 100, 100, 100)
    ctx.path.rect(300, 100, 100, 100)
    ctx.fill()
    ctx.endFrame()
