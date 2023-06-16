package yage.graphics.stb.font

import org.lwjgl.stb.STBTTFontinfo
import org.lwjgl.stb.STBTruetype.*
import yage.graphics.StackUser
import scala.collection.mutable.ArrayBuffer

/**
 * Box and metric are given in unscaled coordinates, vertices is empty for invisible glyphs.
 */ 

class Glyph(val handle: STBTTFontinfo, val codePoint: Int, val glyphIndex: Int) extends StackUser:

  var box = createBox()
  var metric = createMetric()
  var vertices = createVertices()

  def createBitmap(scaX: Float, scaY: Float) =
    val res = GlyphBitmap() 
    useStack(s =>
      val pExtX = s.mallocInt(1)
      val pExtY = s.mallocInt(1)
      val pOffX = s.mallocInt(1)
      val pOffY = s.mallocInt(1)
      res.data = stbtt_GetGlyphBitmap(handle, scaX, scaY, glyphIndex, pExtX, pExtY, pOffX, pOffY)
      res.extX = pExtX.get(0)
      res.extY = pExtY.get(0)
      res.offX = pOffX.get(0)
      res.offY = pOffY.get(0)
    )
    res

  private def createBox() =
    var res: Box = null 
    useStack(s =>
      val pMinX = s.mallocInt(1)
      val pMinY = s.mallocInt(1)
      val pMaxX = s.mallocInt(1)
      val pMaxY = s.mallocInt(1)
      stbtt_GetGlyphBox(handle, glyphIndex, pMinX, pMinY, pMaxX, pMaxY)
      val min = Point(pMinX.get(0), pMinY.get(0))
      val max = Point(pMaxX.get(0), pMaxY.get(0))
      res = Box(min, max)
    )
    res

  private def createMetric() =
    var res: GlyphMetric = null
    useStack(s =>
      val pAdv = s.mallocInt(1)
      val pLsb = s.mallocInt(1)
        stbtt_GetGlyphHMetrics(handle, glyphIndex, pAdv, pLsb)
        res = GlyphMetric(pAdv.get(0), pLsb.get(0))
    )
    res

  private def createVertices() =
    val res = new ArrayBuffer[Vertex]()
    val vs = stbtt_GetGlyphShape(handle, glyphIndex)
    if vs != null then
      val nv = vs.capacity
      for i <- 0 to nv - 1 do
        val v = vs.get(i)
        val p = Point(v.x, v.y)
        val cp1 = Point(v.cx, v.cy)
        val cp2 = Point(v.cx1, v.cy1)
        val vt = v.`type`
        res += Vertex(p, cp1, cp2, VertexType(vt))
    res.toArray


/*    
    

  def getGlyphSdf(cp: Int) = stbtt_GetGlyphSDF​
 
  def getGlyphSvg(cp: Int) = 0

  */