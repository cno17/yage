package yage.image.producer

import yage.image.ImageF
import yage.image.grid.Pixel
import yage.image.grid.iterator.ForwardIterator
import yage.math.Mat3
import yage.math.Vec2

class EllipticHill2(val org: Vec2, val dir1: Vec2, val dir2: Vec2):
  
  val dim = org.n
  
  val matWH = createFrame
  val matHW = matWH.inverted()
  
  def valueAt(pH: Vec2) = Math.max(0, 1 - pH.lengthSquared())
  
  def createImage =
    val min = new Pixel(dim)
    val max = new Pixel(dim)
    val len = Math.max(dir1.length(), dir2.length())
    for d <- 0 to dim - 1 do 
      min(d) = (org(d) - len - 1).toInt
      max(d) = (org(d) + len + 1).toInt
    val img = ImageF(min, max)
    for p <- ForwardIterator(img) do
      val pW = Vec2(p.i.toFloat, p.j.toFloat)
      val pH = matHW.transformPos(pW)
      img(p) = valueAt(pH)
      println(pH)
    img
  
  override def toString = s"a"

  protected def createFrame =
    val res = Mat3().toOne()
    res(0, 0) = dir1(0)
    res(1, 0) = dir1(1)
    res(0, 1) = dir2(0)
    res(1, 1) = dir2(1)
    res(0, 2) = org(0)
    res(1, 2) = org(1)
    res
