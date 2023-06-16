package yage.scene.geometry

import yage.math.Vec4

import yage.graphics.gl.primitive.PrimitiveMode
import scala.collection.mutable.ArrayBuffer

class NormalGeometry(geo: Geometry, length: Float = 1, color: Vec4 = Vec4(0, 1, 0, 1)) extends Geometry:

  mode = PrimitiveMode.Lines
  
  init()
  create()

  override def init() =
    val n = geo.posA.size
    posA = new Array[Vec4](2 * n)
    colA = new Array[Vec4](2 * n) // use static attrib!
    indA = new Array[Int](2 * n)
    for i <- 0 to n - 1 do
      val p = geo.posA(i)
      val n = geo.norA(i).normalized().multiply(length)
      posA(2 * i + 0) = p
      posA(2 * i + 1) = p + n
      colA(2 * i + 0) = color
      colA(2 * i + 1) = color
      indA(2 * i + 0) = 2 * i + 0
      indA(2 * i + 1) = 2 * i + 1 
