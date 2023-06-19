package test.graphics.vg

import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NVGColor
import yage.graphics.vg.Trafo

object TrafoTest:

  val ra = 3.1415f / 2

  def main(args: Array[String]) =
    val t = Trafo()
    t.toRot(ra).traL(2, 3)
    println(t)
    t.toRot(ra).traR(2, 3)
    println(t)