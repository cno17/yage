package test.graphics.vg

import java.io.File

import yage.graphics.fw.App
import yage.graphics.vg.ImageFlags
import yage.graphics.vg.Image

object ImageTest extends App:

  override def init() =
    // ctx.text.loadFont(File(resDir, "font/Roboto-Bold.ttf"), "f2")
    val flags = ImageFlags()
    val img = Image(ctx, File(resDir, "icon/Tree-96.png"), flags)
    println(img.sizeI)
    println(img.sizeJ)
    println(img.id)
