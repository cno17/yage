package test.math.noise

import scala.util.control.Breaks.break

class Image2(val si: Int, val sj: Int):

  val a = Array.ofDim[Double](si, sj)

  def apply(i: Int, j: Int) = a(i)(j)
  def update(i: Int, j: Int, v: Double) = a(i)(j) = v

  def isToroidal =
    var res = true
    for i <- 0 to si - 1 do
      if a(i)(0) != a(i)(sj - 1) then
        res = false
        break
    for j <- 0 to sj - 1 do
      if a(0)(j) != a(si - 1)(j) then
        res = false
        break
    res