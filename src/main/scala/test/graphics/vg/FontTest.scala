package test.graphics.vg

import java.io.File

import yage.graphics.fw.App
import yage.graphics.vg.Font

object FontTest extends App:

  override def init() =
    // val id1 = ctx.text.loadFont(File(resDir, "font/Roboto-Regular.ttf"), "f1")
    // val id2 = ctx.text.loadFont(File(resDir, "font/Roboto-Bold.ttf"), "f2")
    val font = Font(ctx, File(resDir, "font/Roboto-Bold.ttf"))
    println(2)
