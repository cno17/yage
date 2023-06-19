package test.image.grid

import yage.image.grid.Pixel

object PixelTest:

  def main(args: Array[String]) =
    val p = Pixel(2, 3, 5, 7, 11)
    println(p.take(0) x Pixel(Array(20)) x p.drop(1))
