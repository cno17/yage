package yage.image.grid.iterator

import yage.image.grid.Grid
import yage.image.grid.Pixel

class BackwardIterator(grid: Grid) extends GridIterator(grid):

  var index = grid.size
  var pixel = new Pixel(grid.dim)

  override def reset = 
    index = grid.size

  override def hasNext =
    index > 0

  override def next =
    index -= 1
    grid.toPixel(index, pixel)
