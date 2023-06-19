package test.image

import yage.image.*
import yage.image.grid.Pixel
import yage.image.producer.RandomGeneratorF

object ImageSliceTest:

  def main(args: Array[String]) =
    val img1 = ImageF(Pixel(0, 0), Pixel(2, 3))
    val img2 = img1.slice(0, 0)
    // img2.foreachI(i => println(img2.toPixel(i)))
    println(img2)