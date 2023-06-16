package yage.image.grid

import scala.language.postfixOps

trait GridOp:

  this: Grid =>

  def :=(as: Int*)(bs: Int*) = set(as: _*)(bs: _*)

  // def x(g: Grid) = Grid(min x g.min, max x g.max)

  def set(as: Int*)(bs: Int*) =
    min.set(as*)
    max.set(bs*)
    this
    
  def le(g: Grid) = false

