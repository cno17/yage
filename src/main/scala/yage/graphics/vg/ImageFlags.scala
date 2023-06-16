package yage.graphics.vg

import org.lwjgl.nanovg.NanoVG.NVG_IMAGE_FLIPY
import org.lwjgl.nanovg.NanoVG.NVG_IMAGE_GENERATE_MIPMAPS
import org.lwjgl.nanovg.NanoVG.NVG_IMAGE_NEAREST
import org.lwjgl.nanovg.NanoVG.NVG_IMAGE_PREMULTIPLIED
import org.lwjgl.nanovg.NanoVG.NVG_IMAGE_REPEATX
import org.lwjgl.nanovg.NanoVG.NVG_IMAGE_REPEATY
import yage.graphics.Flags

class ImageFlags extends Flags:

  var generateMipMaps = false
  var repeatX = false
  var repeatY = false
  var flipY = false
  var premultiplied = false
  var nearest = false

  override def toInt() =
    var ret = 0
    if generateMipMaps then ret |= NVG_IMAGE_GENERATE_MIPMAPS
    ret
