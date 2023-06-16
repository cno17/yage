package yage.image

import yage.image.grid.Grid
import yage.image.grid.Pixel

class Image[V](min: Pixel, max: Pixel) extends Grid(min, max):

  var values: Array[V] = null

  def apply(i: Int): V = values(i)
  def apply(p: Pixel): V = apply(toIndex(p))

  def update(i: Int, v: V) = values(i) = v
  def update(p: Pixel, v: V) = values(toIndex(p)) = v

  def isToroidal: Boolean =
    for d <- 0 to dim - 1 do
      val g1 = Grid(this); g1.max(d) = g1.min(d)
      val g2 = Grid(this); g2.min(d) = g2.max(d)
      for i <- 0 to g1.size - 1 do 
        if this(g1.toPixel(i)) != this(g2.toPixel(i)) then return false
    true


//  def apply(x: Float, y: Float) = sampleAt(x, y)

