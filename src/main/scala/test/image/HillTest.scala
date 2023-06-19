package test.image

import java.io.File
import javax.imageio.ImageIO
import yage.image.grid.Pixel
import yage.image.producer.Hill
import yage.image.util.AwtAdapter
import yage.image.ImageB

object HillTest:

  def main(args: Array[String]) =
    val img = Hill(Pixel(100, 0), 50).createImage
    for i0 <- 0 to img.size(0) - 1 do
      for i1 <- 0 to img.size(1) - 1 do
        val v = img(Pixel(img.min(0) + i0, img.min(1) + i1))
    println("ok")
    ImageIO.write(AwtAdapter.toBufferedImage(img), "jpg", File("Image.jpg"))
    println(img.values.max)
