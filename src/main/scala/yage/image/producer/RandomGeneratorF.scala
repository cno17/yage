package yage.image.producer

import yage.image.grid.Grid
import yage.image.grid.Pixel
import yage.image.ImageF
import yage.image.producer.ImageGenerator

class RandomGeneratorF(min: Pixel, max: Pixel) extends ImageGenerator[Float](min, max):

  var minV = 0.0f
  var maxV = 1.0f

  override def name = "RandomGeneratorF"

  override def info = ""

  override def exec =
    val res = ImageF(min, max)
    res.foreachI(i => res(i) = rndF(minV, maxV))
    res
