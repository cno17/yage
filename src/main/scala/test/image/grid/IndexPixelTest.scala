package test.image.grid

import yage.image.grid.*

// todo: last coord should move fastest?
object IndexPixelTest:

  def main(args: Array[String]): Unit =
    val g = Grid(0, 0)(2, 3)
    testIndexToPixel(g)

  def testIndexToPixel(g: Grid) =
    for i <- 0 to g.size - 1 do
      val pixel = g.toPixel(i)
      val index = g.toIndex(pixel)
      println(s"$i => $pixel => $index")

  def testPixelToIndex(g: Grid) =
    for i <- 0 to g.size(0) do
      for j <- 0 to g.size(1) do
        val p = Pixel(i, j)
        println(s"$p => ${g.toIndex(p)}")
