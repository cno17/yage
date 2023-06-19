package test.image

import java.io.File
import javax.imageio.ImageIO
import yage.image.grid.Pixel
import yage.image.util.AwtAdapter
import yage.image.ImageB
import yage.image.producer.RandomGeneratorF

object ImageGeneratorTest:

  def main(args: Array[String]) =
    val min = Pixel(0, 0)
    val max = Pixel(255, 255)
    val img = RandomGeneratorF(min, max).exec
    ImageIO.write(AwtAdapter.toBufferedImage(img), "jpg", File("Image.jpg"))
    println("ok")
