package yage.scene.geometry

import test.graphics.gl.HelloWorld.height
import test.graphics.gl.HelloWorld.height
import yage.image.ImageF
import yage.image.grid.Pixel
import yage.math.Vec4

/**
 * radB = bottom radius radT = top radius
 */

class TerrainGeometry(img: ImageF)
extends Surface(0, img.size(0).toFloat - 1, 0, img.size(1).toFloat - 1, img.size(0) - 1, img.size(1) - 1):

  init()
  create()

  override def pos(u: Float, v: Float) = 
    // println(s"$u, $v")
    Vec4(u, v, img(Pixel(u.toInt, v.toInt)), 1)
