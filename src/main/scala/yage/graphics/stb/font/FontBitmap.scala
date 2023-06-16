package yage.graphics.stb.font

import org.lwjgl.BufferUtils

// font.bakeFontBitmap
// can be used to create a texture

case class FontBitmap(si: Int, sj: Int):
  
  val data = BufferUtils.createByteBuffer(si * sj)
