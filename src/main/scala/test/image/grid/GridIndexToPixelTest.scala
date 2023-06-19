package test.image.grid

import yage.image.grid.iterator.ForwardIterator
import yage.image.grid.Grid

object GridIndexToPixelTest:

  def main(args: Array[String]) =
    val g = Grid(2, 3, 5)(3, 5, 8)
    val it = new ForwardIterator(g)
    while it.hasNext do
      val p = it.next
      val i = g.toIndex(p)
      val q = g.toPixel(i)
      println(s"$p : $i")

    for i <- 0 to g.size - 1 do
      val p = g.toPixel(i)
      // println(s"$i - $p")

    // println(g.iteratedSize(2))
