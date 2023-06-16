package yage.image.grid

trait GridMetric:

  this: Grid =>

  def distanceOne(p: Pixel, q: Pixel) =
    var res = 0
    var dif = 0
    p.foreach(i =>
      dif = Math.abs(p(i) - q(i))
      dif = Math.min(dif, extent(i) - dif)
      res += dif
    )
    res

  def distanceTwo(p: Pixel, q: Pixel) = Math.sqrt(distanceTwoSquared(p, q)).toFloat

  def distanceTwoSquared(p: Pixel, q: Pixel) =
    var res = 0
    var dif = 0
    p.foreach(i =>
      dif = Math.abs(p(i) - q(i))
      dif = Math.min(dif, extent(i) - dif)
      res += dif * dif
    )
    res

  def distanceInf(p: Pixel, q: Pixel) =
    var res = 0
    var dif = 0
    p.foreach(i =>
      dif = Math.abs(p(i) - q(i))
      dif = Math.min(dif, extent(i) - dif)
      res = Math.max(res, dif)
    )
    res

  //

