package yage.image

import yage.image.grid.Pixel
import yage.image.grid.iterator.ForwardIterator

/**
 * def apply(x: Float, y: Float) = sampleAt(x, y)
 */

class ImageF(min: Pixel, max: Pixel) extends Image[Float](min, max):

  values = Array.fill(size)(0)

  // normalize()!!!
  def normalize = // (minVal: Float, maxVal: Float) =
    val min = values.min
    val dif = values.max - min
    foreachI(i => this(i) = (this(i) - min) / dif)
    this

  def scale(s: Float) =
    foreachI(i => this(i) *= s)
    this

  // move to image!  
    
  def slice(d: Int, i: Int) =
    val a = min.take(d - 1) x min.drop(d)
    val b = max.take(d - 1) x max.drop(d)
    val res = ImageF(a, b)
    /*
    for p <- ForwardIterator(res) do
      val q = p.take(d) x Pixel(Array(i)) x p.drop(d)
      res(p) = this(q)
    */
    res
    

