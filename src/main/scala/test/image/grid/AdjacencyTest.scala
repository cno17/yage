package test.image.grid

import yage.image.grid.adjacency.OneAdjacency
import yage.image.grid.Grid
import yage.image.grid.Pixel

object AdjacencyTest:

  def main(args: Array[String]) =
    val g = Grid(0, 0)(10, 10)
    val c = Pixel(0, 0)
    val adj = OneAdjacency(g, c)
    for p <- adj do println(p)
