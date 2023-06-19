package test.mesh.producer

import yage.math.Vec2
import yage.mesh.producer.generator.PolygonGenerator

import yage.mesh.Mesh2
import yage.mesh.Edge2

object PolygonGeneratorTest:

  def main(args: Array[String]) =
    val ps = Array(Vec2(0, 0), Vec2(100, 0), Vec2(100, 100), Vec2(0, 100))
    val m = PolygonGenerator()(ps)
    println(m.isValid())
