package test.graphics.stb.font

import java.io.File
import yage.util.FileExt
import yage.graphics.stb.font.Font
import org.lwjgl.stb.STBTruetype.*
import org.lwjgl.stb.STBTTFontinfo

// thanks to justin meiners

object FontTest:

  def main(args: Array[String]) =
    val font = Font(File("src/main/resources/font/FiraSans-Regular.ttf"))
    val glyph = font.glyph(65)
    val box = glyph.box
    val adv = glyph.metric.advance
    val map = glyph.createBitmap(1.0f, 1.0f)
    println(box)
    println(adv)
    println(map)

