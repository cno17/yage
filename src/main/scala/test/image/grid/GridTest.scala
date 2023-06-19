package test.image.grid

import yage.image.grid.Grid
import yage.image.grid.Pixel

object GridTest:

  def main(args: Array[String]) = test2
  
  def test1 =
    val g = Grid(2, 3)(7, 11)
    val p = Pixel(6, 11)
    println(g)
    println(p)
    println(g.contains(p))
    g.inc(0, p)
    println(p)
    g.inc(0, p)
    println(p)
    g.foreachP(p => println(p))

  def test2 =
    val g = Grid(2, 3)(7, 11)
    println(g)
    for i <- 0 to 5 do
      println(g.rndP)
    println(g.minExtent)