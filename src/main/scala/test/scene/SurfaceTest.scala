package test.scene

import yage.graphics.fw.App
import yage.scene.geometry.Torus

object SurfaceTest extends App:

  override def init() =
    val t = Torus(0.2f, 0.8f, 5, 4)
    close()
    println("bye")