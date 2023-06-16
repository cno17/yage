package yage.image.grid

// TODO added(p) p + q ...

trait PixelOp:

  this: Pixel =>

  def :=(is: Int*) = set(is*)
  def :=(p: Pixel) = set(p)

  def *=(s: Int) = multiply(s)
  def +=(p: Pixel) = add(p)
  def -=(p: Pixel) = subtract(p)

  def =+(p: Pixel, q: Pixel) = toSumOf(p, q)
  def =-(p: Pixel, q: Pixel) = toDifferenceOf(p, q)
  def =*(p: Pixel, s: Int) = toProductOf(p, s)

  def x(p: Pixel) = join(p)

  def set(is: Int*) = foreach(i => this (i) = is(i))
  def set(p: Pixel) = foreach(i => this (i) = p(i))

  def add(p: Pixel) = foreach(i => this(i) += p(i))
  def subtract(p: Pixel) = foreach(i => this(i) -= p(i))
  def multiply(s: Int) = foreach(i => this(i) *= s)

  def toSumOf(p: Pixel, q: Pixel) = foreach(i => this(i) = p(i) + q(i))
  def toDifferenceOf(p: Pixel, q: Pixel) = foreach(i => this(i) = p(i) - q(i))
  def toProductOf(p: Pixel, s: Int) = foreach(i => this(i) = p(i) * s)

  // cartesian product
  def join(p: Pixel) =
    val res = new Pixel(dim + p.dim)
    val c = Array[Int](dim + p.dim)
    for i <- 0 to dim - 1 do res(i) = this(i)
    for i <- 0 to p.dim - 1 do res(dim + i) = p.cmp(i)
    res
