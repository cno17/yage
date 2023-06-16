package yage.graphics.stb.font

import java.io.File
import java.nio.ByteBuffer
import org.lwjgl.stb.STBTTFontinfo
import org.lwjgl.stb.STBTruetype.*
import org.lwjgl.system.MemoryStack.*
import org.lwjgl.system.MemoryStack.stackPush
import scala.language.postfixOps
import scala.collection.mutable.Map
import yage.util.FileExt
import yage.graphics.StackUser

import org.lwjgl.PointerBuffer
import org.lwjgl.stb.STBTTBakedChar
import org.lwjgl.BufferUtils

object Font extends FileExt:

  def apply(f: File) =
    val data = f.bytes
    val handle = STBTTFontinfo.create()
    stbtt_InitFont(handle, data)
    new Font(data, handle, 32, 127)

/**
 * cp: code point
 * gi: glyph index  
 */

class Font(val data: ByteBuffer, val handle: STBTTFontinfo, val minCP: Int, val maxCP: Int) extends StackUser:

  val box = createBox()
  val metric = createMetric()
  val glyphs = createGlyphs()

  def createBox() = 
    var res: Box = null 
    useStack(s =>
      val pMinX = s.mallocInt(1)
      val pMinY = s.mallocInt(1)
      val pMaxX = s.mallocInt(1)
      val pMaxY = s.mallocInt(1)
      stbtt_GetFontBoundingBox(handle, pMinX, pMinY, pMaxX, pMaxY)
      val min = Point(pMinX.get(0), pMinY.get(0))
      val max = Point(pMaxX.get(0), pMaxY.get(0))
      res = Box(min, max)
    )
    res

  def createMetric() = 
    var res: FontMetric = null
    useStack(s =>
      val pAsc = s.mallocInt(1)
      val pDes = s.mallocInt(1)
      val pGap = s.mallocInt(1)
      stbtt_GetFontVMetrics(handle, pAsc, pDes, pGap)
      res = FontMetric(pAsc.get(0), pDes.get(0), pGap.get(0))
    )
    res

  def createGlyphs() =
    val res = new Array[Glyph](maxCP - minCP + 1)  
    for cp <- minCP to maxCP do
      val gi = stbtt_FindGlyphIndex(handle, cp)
      res(cp - minCP) = Glyph(handle, cp, gi)
    res

  def glyph(cp: Int) = glyphs(cp - minCP)

  ////////////////

  def getScaleForHeight(h: Float) = stbtt_ScaleForPixelHeight(handle, h)
 
  def getKernAdvance(cp1: Int, cp2: Int) = 0

  def getFontNameString() = 0

  def bakeBitmap(height: Float, si: Int, sj: Int) =
    val res = FontBitmap(si, sj)
    val bcb = STBTTBakedChar.malloc(maxCP - minCP + 1)    
    stbtt_BakeFontBitmap(data, height, res.data, si, sj, minCP, bcb) // use result value!
    res