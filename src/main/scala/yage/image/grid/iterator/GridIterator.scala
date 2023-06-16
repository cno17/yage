package yage.image.grid.iterator

import yage.image.grid.Grid
import yage.image.grid.Pixel

trait GridIterator(val grid: Grid) extends Iterator[Pixel]:

  // var index = 0
  // var pixel = new Pixel(grid.dim)

  def reset: Unit
