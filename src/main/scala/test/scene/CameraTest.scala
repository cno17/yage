package test.scene

import yage.math.Vec2
import yage.scene.Camera

object CameraTest:

  def main(args: Array[String]) =
    val c = Camera()
    c.matWV.toTranslation(2, 3, 5)
    c.update
    println(c)