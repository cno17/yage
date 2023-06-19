package test.scene.bounds

import scala.collection.mutable.ListBuffer
import yage.scene.bounds.BVInitializer
import yage.math.Vec2
import yage.shape.ABox2

object InitTest extends BVInitializer[Vec2]:

  def main(args: Array[String]) =
    val box = ABox2()
    val ps = ListBuffer[Vec2]()
    for i <- 0 to 100 do
      ps += Vec2().toRandom(-100, 100)
    println(box)
    init(box, ps)
    println(box)
