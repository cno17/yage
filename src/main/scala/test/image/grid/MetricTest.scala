package test.image.grid

import yage.image.grid.*

object MetricTest:

  def main(args: Array[String]) =
    val p = Pixel(1, 1)
    val q = Pixel(9, 1)
    val g = Grid(0, 0)(10, 10)
    println(p.lengthOne)
    println(g.distanceOne(p, q))
