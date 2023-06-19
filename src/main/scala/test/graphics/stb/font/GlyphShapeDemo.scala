package test.graphics.stb.font

import java.io.File
import java.nio.ByteBuffer
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.BufferUtils
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVGGL3

import yage.graphics.fw.App
import org.lwjgl.nanovg.NVGColor
import yage.graphics.stb.font.Font
import yage.graphics.stb.font.VertexType
import yage.graphics.stb.font.Glyph
import yage.graphics.fw.AppCreateInfo

// TODO: 
// scale/zoom
// fill, winding rule
// key input: inc/dec codepoint
// wait mode
// triangulation: use/port jts 

object GlyphShapeDemo extends App:

  var ctx2: Long = 0
  var color: NVGColor = null
  val font = Font(File("src/main/resources/font/FiraSans-Regular.ttf"))
  val glyph = font.glyph(38)

  override def info() =
    val res = AppCreateInfo()
    res.sizeX = 800
    res.sizeY = 1000
    res

  override def init() =
    glClearColor(0, 0, 0, 1)
    ctx2 = NanoVGGL3.nvgCreate(0)
    color = NVGColor.create()
    nvgRGBf(0.2f, 0.8f, 0.2f, color)

  override def draw() =
    val sx = window.innerSizeX.toFloat
    val sy = window.innerSizeY.toFloat
    glClear(GL_COLOR_BUFFER_BIT)
    nvgBeginFrame(ctx2, sx, sy, 1)
    draw(glyph)
    nvgEndFrame(ctx2)
  
  private def draw(g: Glyph) =
    nvgBeginPath(ctx2)
    for v <- g.vertices do
      if v.typ == VertexType.Move then nvgMoveTo(ctx2, v.p.x.toFloat, v.p.y.toFloat)
      if v.typ == VertexType.Line then nvgLineTo(ctx2, v.p.x.toFloat, v.p.y.toFloat)
      if v.typ == VertexType.Quad then nvgQuadTo(ctx2, v.cp1.x.toFloat, v.cp1.y.toFloat, v.p.x.toFloat, v.p.y.toFloat)
      // no cubics?
    nvgClosePath(ctx2)
    nvgStrokeColor(ctx2, color)
    nvgStroke(ctx2)

