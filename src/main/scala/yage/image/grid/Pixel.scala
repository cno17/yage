package yage.image.grid

import yage.image.util.Random

object Pixel:

  def apply(p: Pixel) =
    val res = new Pixel(p.dim)
    p.cmp.copyToArray(res.cmp)
    res
    
  // bequem aber missverständlich: drop it  
  def apply(cs: Int*) =
    val res = new Pixel(cs.length)
    res.cmp = cs.toArray
    res

  def apply(cs: Array[Int]) =
    val res = new Pixel(cs.length)
    res.cmp = cs
    res

// val cmp: Array[Int]!    
class Pixel(val dim: Int) extends Random, PixelMetric, PixelOp:

  var cmp = Array.ofDim[Int](dim)

  def apply(i: Int) = cmp(i)
  def update(i: Int, v: Int) = cmp(i) = v //  this} // do not return this!

  def i = this(0)
  def j = this(1)
  def k = this(2)
  def l = this(3)

  def i_=(s: Int) = this(0) = s
  def j_=(s: Int) = this(1) = s
  def k_=(s: Int) = this(2) = s
  def l_=(s: Int) = this(3) = s

  def toZero = foreach(i => this(i) = 0)
  def toUnit(d: Int) = foreach(i => if i == d then this(i) = 1 else this(i) = 0)
  def toRandom(min: Int, max: Int) = foreach(i => this(i) = rndI(min, max))

  def take(n: Int) = Pixel(cmp.take(n))
  def drop(n: Int) = Pixel(cmp.drop(n))

  def foreach(f: Int => Unit) =
    // cmp.foreach(f) // TODO why does this not work?
    for i <- 0 to dim - 1 do f(i)
    this

  def forall(p: Int => Boolean): Boolean =
    for i <- 0 to dim - 1 do
      if p(i) == false then return false
    true

  override def equals(a: Any) =
    if a.isInstanceOf[Pixel] then
      val p = a.asInstanceOf[Pixel]
      forall(i => this(i) == p(i))
    else false

  override def toString = cmp.mkString("(", ", ", ")")
