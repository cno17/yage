package yage.image.producer

import yage.image.grid.Pixel
import yage.image.grid.iterator.ForwardIterator
import yage.image.ImageF
import yage.image.producer.ImageGenerator


// TODO Hill extends Grid or Image?
// TODO Hill <- ParaHill, Expo(Bell)Hill, SineHill
// TODO elliptical domain

class Hill(val center: Pixel, val radius: Int):
  
  val dim = center.dim
  
  val radiusSquared = radius * radius
  
  def valueAt(p: Pixel) = Math.max(0, radiusSquared - center.distanceTwoSquared(p))
  
  def createImage =
    val min = new Pixel(dim)
    val max = new Pixel(dim)
    for d <- 0 to dim - 1 do
      min(d) = center(d) - radius
      max(d) = center(d) + radius
    val img = ImageF(min, max)
    img.foreachP(p => img(p) = valueAt(p).toFloat)
    img
  
  override def toString = s"($center, $radius"

class HillGeneratorF(min: Pixel, max: Pixel, var num: Int, var minRad: Int, var maxRad: Int)
extends ImageGenerator[Float](min, max):

  override def name = "HillGeneratorF"

  override def info = ""

  override def exec =
    val res = ImageF(min, max)
    val r1 = (res.minExtent * minRad) / 200
    val r2 = (res.maxExtent * maxRad) / 200
    for i <- 1 to num do
      // println(s"$i of $num")
      val img = Hill(res.rndP, rndI(r1, r2)).createImage
      for pos <- ForwardIterator(img) do
        val v = img(pos)
        res.multiApplyAt(p => res(p) = res(p) + v)(pos)
    res
