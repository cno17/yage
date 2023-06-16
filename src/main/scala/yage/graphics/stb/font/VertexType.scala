package yage.graphics.stb.font

import org.lwjgl.stb.STBTruetype.STBTT_vcubic
import org.lwjgl.stb.STBTruetype.STBTT_vcurve
import org.lwjgl.stb.STBTruetype.STBTT_vline
import org.lwjgl.stb.STBTruetype.STBTT_vmove

// move to Vertex?

enum VertexType(val id: Int):

  case Move extends VertexType(STBTT_vmove)
  case Line extends VertexType(STBTT_vline)
  case Quad extends VertexType(STBTT_vcurve)
  case Cubic extends VertexType(STBTT_vcubic)

object VertexType:

  def apply(i: Int) = VertexType.values.find(_.id == i).get
