package test.graphics.stb.font

import java.io.File
import yage.util.FileExt
import yage.graphics.stb.font.Font
import org.lwjgl.stb.STBImageWrite.*

// thanks to justin meiners

object BitmapTest:

  def main(args: Array[String]) =
    val font = Font(File("src/main/resources/font/FiraSans-Regular.ttf"))
    // val map = font.getGlyphBitmap(65, 1.0f, 1.0f)
    // val bm = font.bakeBitmap(64.0f, 1024, 1024)
    val bm = font.bakeBitmap(64.0f, 512, 512)
    val res = stbi_write_jpg("Bitmap.jpg", bm.si, bm.sj, 1, bm.data, 100)
    println(res)
 
