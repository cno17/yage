package test.shape

import yage.math.Vec
import yage.math.Vec2

object DesignTest1:

  trait Shape[V <: Vec[V], S <: Shape[V, S]]

  trait ABox[V <: Vec[V], B <: ABox[V, B]] extends Shape[V, B]

  class ABox2(val min: Vec2, val max: Vec2) extends ABox[Vec2, ABox2]


  def main(args: Array[String]) =
    println(2)
