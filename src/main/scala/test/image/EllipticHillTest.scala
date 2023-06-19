package test.image

import yage.image.grid.Pixel
import yage.image.producer.EllipticHill2
import yage.image.util.AwtAdapter
import yage.image.ImageB
import yage.math.Vec2

import java.io.File
import javax.imageio.ImageIO

object EllipticHillTest:

  def main(args: Array[String]) =
    val img = EllipticHill2(Vec2(0, 0), Vec2(10, 0), Vec2(0, 10)).createImage
    println("ok")
    ImageIO.write(AwtAdapter.toBufferedImage(img), "jpg", File("Image.jpg"))
    println(img.values.max)
