package yage.image.grid

import scala.collection.mutable.ListBuffer
import yage.image.util.Random

/**
 * todo
 */

trait GridMapper:

  this: Grid =>

  def multiApplyAt(f: Pixel => Unit)(p: Pixel) =
    val iss = ListBuffer[List[Int]]()
    for d <- 0 to dim - 1 do
      iss += is(d, p)
    val ps = map(iss.toList).reduceLeft(cross)
    for p <- ps do
      f(p)

  private def is(d: Int, p: Pixel) =
    val is = ListBuffer[Int]()
    if p(d) > min(d) && p(d) < max(d) then
      is += p(d)
    else if p(d) < min(d) then
      is += p(d) + extent(d)
    else if p(d) > max(d) then
      is += p(d) - extent(d)
    else if p(d) == min(d) || p(d) == max(d) then
      is += min(d)
      is += max(d)
    is.toList

  private def map(iss: List[List[Int]]) =
    val res = ListBuffer[List[Pixel]]()
    for is <- iss do
      res += is.map(i => Pixel(i))
    res.toList

  private def cross(ps: List[Pixel], qs: List[Pixel]) =
    val res = ListBuffer[Pixel]()
    for p <- ps; q <- qs do
      res += p x q
    res.toList
