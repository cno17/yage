package yage.image.grid.iterator

import yage.image.grid.Grid
import yage.image.grid.Pixel

class ForwardIterator(grid: Grid) extends GridIterator(grid):

  var index = -1
  var pixel = new Pixel(grid.dim)

  override def reset =
    index = -1

  override def hasNext =
    index < grid.size - 1

  override def next =
    index += 1
    grid.toPixel(index, pixel)
