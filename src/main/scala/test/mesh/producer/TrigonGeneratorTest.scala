package test.mesh.producer

import yage.math.Vec2
import yage.mesh.producer.generator.TrigonGenerator

import yage.mesh.Mesh2
import yage.mesh.Edge2

object TrigonGeneratorTest:

  def main(args: Array[String]) =
    val m = TrigonGenerator()(Vec2(0, 0), Vec2(100, 0), Vec2(100, 100))
    println(m.isValid())
