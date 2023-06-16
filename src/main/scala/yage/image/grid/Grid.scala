package yage.image.grid

import scala.collection.mutable.ListBuffer
import yage.image.grid.iterator.ForwardIterator
import yage.image.util.Random

object Grid:

  def apply(g: Grid) = new Grid(Pixel(g.min), Pixel(g.max))
  def apply(a: Pixel, b: Pixel) = new Grid(a, b)
  def apply(as: Int*)(bs: Int*) = new Grid(Pixel(as*), Pixel(bs*))

  // def ofDim(d: Int) = new Grid(Pixel.ofDim(d), Pixel.ofDim(d))

/**
 * The purpose of inc and dec is the simplification of difference operators. 
 * Thanks to net.imglib2.util.IntervalIndexer for index-pixel-conversion.
 */

class Grid(val min: Pixel, val max: Pixel) extends Random, GridMapper, GridMetric, GridOp: // Iterable[Pixel], 

  require(min.dim == max.dim)

  val dim = min.dim

  def toPixel(index: Int): Pixel = toPixel(index, new Pixel(dim))

  def toPixel(index: Int, res: Pixel) =
    var i = index
    for d <- 0 to dim - 1 do
      res(d) = min(d) + i % size(d)
      i /= size(d)
    res

  def toIndex(p: Pixel) =
    var i = p(0) - min(0)
    for d1 <- 1 to dim - 1 do
      var s = 1
      for d2 <- 0 to d1 - 1 do
        s = s * size(d2)
      i = i + (p(d1) - min(d1)) * s
    i

  def contains(p: Pixel): Boolean = forallD(d => min(d) <= p(d) && p(d) <= max(d))
  
  def contains(g: Grid): Boolean = contains(g.min) && contains(g.max)

  def size: Int =
    var ret = 1
    foreachD(d => ret = ret * size(d))
    ret

  def size(d: Int): Int = extent(d) + 1

  def center(d: Int) = (min(d) + max(d)) * 0.5f

  def extent(d: Int) = (max(d) - min(d))

  def minExtent =
    var res = Int.MaxValue
    foreachD(d => res = Math.min(res, extent(d)))
    res

  def maxExtent =
    var res = 0
    foreachD(d => res = Math.max(res, extent(d)))
    res

  def dec(d: Int, x: Int): Int = if x == min(d) then max(d) - 1 else x - 1
  def dec(d: Int, p: Pixel): Unit = p.update(d, dec(d, p(d)))
  def inc(d: Int, x: Int): Int = if x == max(d) then min(d) + 1 else x + 1
  def inc(d: Int, p: Pixel): Unit = p.update(d, inc(d, p(d)))
  def map(d: Int, x: Int) = if x < min(d) then x + extent(d) else if x > max(d) then x - extent(d) else x
  def map(d: Int, p: Pixel): Unit = p.update(d, map(d, p(d)))
  def map(p: Pixel): Pixel = p.foreach(d => p.update(d, map(d, p(d))))

  def rndP =
    val res = new Pixel(dim)
    res.foreach(i => res(i) = rndI(min(i), max(i)))

  def foreachD(f: Int => Unit) = 
    for d <- 0 to dim - 1 do f(d)
  
  def foreachI(f: Int => Unit) = 
    for i <- 0 to size - 1 do f(i)
  
  def foreachP(f: Pixel => Unit) = 
    for i <- 0 to size - 1 do f(toPixel(i))

  def forallD(f: Int => Boolean): Boolean =
    foreachD(d => if !f(d) then return false)
    true

  def forallI(f: Int => Boolean): Boolean =
    foreachI(i => if !f(i) then return false)
    true

  def forallP(f: Pixel => Boolean): Boolean =
    foreachP(p => if !f(p) then return false)
    true

  override def toString =
    var s = ""
    for d <- 0 to dim - 1 do
      s += s"[${min(d)}, ${max(d)}]"
      if d < dim - 1 then s += " x "
    s

/*
  // override def iterator = ForwardIterator(this)


  // def take(n: Int) = Grid(min.take(n), max.take(n))
  // def drop(n: Int) = Grid(min.drop(n), max.drop(n))
  // def join(g: Grid) = Grid(min x g.min, max x g.max)

	def distanceOne(p: Pixel, q: Pixel) =
		var res = 0
		for d <- 0 to dim - 1 do
			var dif = Math.abs(p(d) - q(d))
			dif = Math.min(dif, extent(d) - dif)
			res += dif
		res

	def distanceTwo(p: Pixel, q: Pixel) =
		Math.sqrt(distanceTwoSquared(p, q)).toFloat

	def distanceTwoSquared(p: Pixel, q: Pixel) =
		var res = 0
		for d <- 0 to dim - 1 do
			var dif = Math.abs(p(d) - q(d))
			dif = Math.min(dif, extent(d) - dif)
			res += dif * dif
		res

	def distanceInf(p: Pixel, q: Pixel) =
		var res = 0
		for d <- 0 to dim - 1 do
			var dif = Math.abs(p(d) - q(d))
			dif = Math.min(dif, extent(d) - dif)
			if dif > res then res = dif
		res


	override def toString = s"($min, $max)"


	def toIndexOld(p: Pixel) =
		var i = p(0) - min(0)
		for d <- 1 to dim - 1 do
			i = i + (p(d) - min(d)) * iteratedSize(d - 1)
		i

	def iteratedSize(d: Int) =
		var res = 1
		for dim <- 0 to d do
			res = res * size(dim)
		res

 */
