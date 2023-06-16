package yage.image.util

import java.awt.image.BufferedImage
import yage.image.grid.Pixel
import yage.image.Image

object AwtAdapter:

  def toBufferedImageNew(img: Image[Float]) =
    val s0 = img.size(0)
    val s1 = img.size(1)
    val min = img.values.min
    val dif = img.values.max - min
    val res = BufferedImage(s0, s1, BufferedImage.TYPE_BYTE_GRAY)
    img.foreachP(p => 
      val c = (255 * (img(p) - min) / dif).toInt
      res.setRGB(p(0), p(1), rgb(c, c, c))
    )
    res

  def toBufferedImage(img: Image[Float]) =
    val s0 = img.size(0)
    val s1 = img.size(1)
    val min = img.values.min
    val dif = img.values.max - min
    val res = new BufferedImage(s0, s1, BufferedImage.TYPE_BYTE_GRAY)
    for i0 <- 0 to s0 - 1 do
      for i1 <- 0 to s1 - 1 do
        val v = img(Pixel(img.min(0) + i0, img.min(1) + i1))
        val c = (255 * (v - min) / dif).toInt
        res.setRGB(i0, i1, rgb(c, c, c))
    res

  def rgb(r: Int, g: Int, b: Int) =
    var rgb = r
    rgb = (rgb << 8) + g
    rgb = (rgb << 8) + b
    rgb

  def r(rgb: Int) = 0xff & (rgb >> 16) // FF?
  def g(rgb: Int) = 0xff & (rgb >> 8)
  def b(rgb: Int) = 0xff & (rgb)
