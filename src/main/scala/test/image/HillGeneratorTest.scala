package test.image

import yage.image.grid.Pixel
import yage.image.util.AwtAdapter
import yage.image.ImageF
import yage.image.producer.HillGeneratorF

import java.io.File
import javax.imageio.ImageIO

object HillGeneratorTest:

  def main(args: Array[String]) =
    val min = Pixel(0, 0)
    val max = Pixel(512, 512)
    val t1 = System.currentTimeMillis()
    val img = HillGeneratorF(min, max, 100, 5, 10).exec.asInstanceOf[ImageF]
    val t2 = System.currentTimeMillis()
    ImageIO.write(AwtAdapter.toBufferedImage(img), "jpg", File("Image.jpg"))
    println(img.isToroidal)
    println((t2 - t1) / 1000)
