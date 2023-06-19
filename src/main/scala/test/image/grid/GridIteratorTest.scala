package test.image.grid

import yage.image.grid.Grid
import yage.image.grid.iterator.*

object GridIteratorTest:

  def main(args: Array[String]): Unit =
    val g = Grid(0, 0)(2, 3)
    val fit = ForwardIterator(g)
    val bit = BackwardIterator(g)
    // while it.hasNext do println(it.next)
    // for (p, q) <- fit zip bit do println(s"$p : $q")
    for p <- fit do print(p)
    println()
    fit.reset
    for p <- fit do print(p)
