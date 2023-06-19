package test.image.grid

import yage.image.grid.Grid
import yage.image.grid.Pixel

object GridArithmeticTest:

  def main(args: Array[String]) =
    val p = Pixel(6, 5)
    val g = Grid(2, 3)(7, 11)
    println(g.inc(0, 7))
