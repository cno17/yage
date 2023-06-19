package test.image.grid

import scala.collection.mutable.ListBuffer
import yage.image.grid.Pixel

/**
 * input: an array of lists, output: a list of pixels
 */

object ApplyAtTest:

  val is0 = List(1, 2)
  val is1 = List(3, 4, 5)
  val is2 = List(6, 7)
  val is3 = List(8, 9)

  val iss = List(is0, is1, is2) // , is3)

  def main(args: Array[String]) =
    val ps = map(iss).reduceLeft(cross)
    println(ps)

  def map(iss: List[List[Int]]) =
    val res = ListBuffer[List[Pixel]]()
    for is <- iss do
    res += is.map(i => Pixel(Array(i)))
    res.toList

  def cross(ps: List[Pixel], qs: List[Pixel]) =
    val res = ListBuffer[Pixel]()
    for p <- ps do
      for q <- qs do
        res += p x q
    res.toList

  def pixels(iss: List[List[Int]]): List[Pixel] =
    map(iss).reduceLeft(cross)
