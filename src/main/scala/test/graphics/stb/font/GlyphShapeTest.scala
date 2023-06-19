package test.graphics.stb.font

import java.io.File
import yage.util.FileExt
import yage.graphics.stb.font.Font
import org.lwjgl.stb.STBTruetype.*
import org.lwjgl.stb.STBTTFontinfo

// draw the bezier control points of a glyph with nvg

object GlyphShapeTest:

  def main(args: Array[String]) =
    val font = Font(File("src/main/resources/font/FiraSans-Regular.ttf"))
    for v <- font.glyph(65).vertices do
      println(v)
