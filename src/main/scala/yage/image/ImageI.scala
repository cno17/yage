package yage.image

import yage.image.grid.Pixel

/**
 * def apply(x: Float, y: Float) = sampleAt(x, y)
 */

class ImageI(min: Pixel, max: Pixel) extends Image[Int](min, max):

  values = Array.fill(size)(0)

  def scale(s: Int) =
    foreachI(i => this(i) *= s)
    this

  override def toString =
    assert(dim == 2)
    var res = ""
    for j <- min(1) to max(1) do
      for i <- min(0) to max(0) do
        val v = this(Pixel(i, j))
        res += s"$v " // s"${this(Pixel(i1, i1)) }"
      res += "\n"
    res
  