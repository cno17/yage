package yage.image.grid

import yage.math.MathUtil

// extend MathUtil

trait PixelMetric extends MathUtil:

  this: Pixel =>

  def lengthOne =
    var res = 0
    foreach(i => res += abs(this(i)))
    res

  // def lengthTwo = sqrt(lengthTwoSquared)

  def lengthTwoSquared =
    var res = 0
    foreach(i => res += this(i) * this(i))
    res

  def lengthInf =
    var res = 0
    foreach(i => res = max(res, abs(this(i))))
    res

  def distanceOne(p: Pixel) =
    var res = 0
    foreach(i => res += abs(this(i) - p(i)))
    res

  def distanceTwo(p: Pixel) = Math.sqrt(distanceTwoSquared(p)).toFloat

  def distanceTwoSquared(p: Pixel) =
    var res = 0
    foreach(i => { val dif = this(i) - p(i); res += dif * dif })
    res

  def distanceInf(p: Pixel) =
    var res = 0
    foreach(i => res = Math.max(res, Math.abs(this(i) - p(i))))
    res

  //

