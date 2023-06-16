package yage.graphics.stb.font

import java.nio.ByteBuffer

/**
 *
 */

class GlyphBitmap:

  var extX = 0
  var extY = 0
  var offX = 0
  var offY = 0

  var data: ByteBuffer = null

  override def toString() = s"(extX = $extX, extY = $extY, offX = $offX, offY = $offY, dataSize = ?)"
