package test.graphics.vg.pure

import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NVGColor

object Test:

  def main(args: Array[String]) =
    val a = 3.1457f
    val s = f"$a%.2f"
    println(s)