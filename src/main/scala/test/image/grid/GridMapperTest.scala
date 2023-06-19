package test.image.grid

import yage.image.grid.Grid
import yage.image.grid.Pixel

object GridMapperTest:

  def main(args: Array[String]) =
    val g = Grid(0, 0)(5, 5)
    g.multiApplyAt(p => println(p))(Pixel(5, 5))