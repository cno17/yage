package test.graphics.gl.resource

import java.io.File
import javax.imageio.ImageIO
import java.awt.image.BufferedImage.*

object BufferedImageTest:

  def main(args: Array[String]) =
    val dir = File(File("").getAbsolutePath() + "/src/main/resources/image")
    foreachFileUnder(dir)(info)

  def foreachFileUnder(dir: File)(fun: File => Unit): Unit =
    for f <- dir.listFiles() do
      if f.isFile() then fun(f)
      else foreachFileUnder(f)(fun)

  def info(f: File) =
    val img = ImageIO.read(f)
    // println(s"${f.getName}: ${img.getWidth()}, ${img.getHeight()}")
    println(s"${f.getName}: ${img.getType()}")

  enum DataType(val value: Int):
    case I08_BGR extends DataType(TYPE_3BYTE_BGR) // 90%
    case I08_ABGR extends DataType(TYPE_4BYTE_ABGR) // 9%
    case I32_BGR extends DataType(TYPE_INT_BGR) 
    case I32_ARBG extends DataType(TYPE_INT_ARGB) 
