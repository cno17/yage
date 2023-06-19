package test.math

import yage.math.*

import java.nio.ByteBuffer
import org.joml.*
import scala.util.Random

object VecTest:

  def main(args: Array[String]) =
    val p = Vec2(102, 103)
    println(p.distance(100, 105))
