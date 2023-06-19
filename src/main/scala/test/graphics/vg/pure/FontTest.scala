package test.graphics.vg.pure
import java.io.File
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVGGL3

import yage.graphics.fw.App

object FontTest extends App:

  var ctxp: Long = 0

  override def init() =
    ctxp = NanoVGGL3.nvgCreate(0)
    val f1 = File(resDir, "font/Roboto-Regular.ttf")
    val f2 = File(resDir, "font/Roboto-Bold.ttf")
    val h1 = nvgCreateFont(ctxp, "font1", f1.getAbsolutePath())
    val h2 = nvgCreateFont(ctxp, "font2", f1.getAbsolutePath())
    println(h1)
    println(h2)
