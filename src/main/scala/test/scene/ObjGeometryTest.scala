package test.scene

import java.io.File
import yage.graphics.fw.App
import yage.scene.geometry.ObjGeometry

object ObjGeometryTest extends App:

  override def init() =
    val g = ObjGeometry(File("src/main/resources/meshes/Lamp.obj"))
    println(g.box)
    close()
    println("bye")