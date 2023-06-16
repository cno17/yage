package yage.graphics.gl.resource.buffer

import org.lwjgl.opengl.GL30C.GL_MAP_READ_BIT
import org.lwjgl.opengl.GL30C.GL_MAP_WRITE_BIT
import org.lwjgl.opengl.GL30C.GL_MAP_INVALIDATE_RANGE_BIT
import org.lwjgl.opengl.GL30C.GL_MAP_INVALIDATE_BUFFER_BIT
import org.lwjgl.opengl.GL30C.GL_MAP_FLUSH_EXPLICIT_BIT
import org.lwjgl.opengl.GL44C.GL_MAP_PERSISTENT_BIT
import org.lwjgl.opengl.GL44C.GL_MAP_COHERENT_BIT
import yage.graphics.Flags

class BufferMapFlags extends Flags:

  var read = false
  var write = false
  var persistent = false
  var coherent = false
  var invalidateRange = false
  var invalidateBuffer = false
  var flushExplicit = false

  override def toInt() =
    var res = 0
    if read then res |= GL_MAP_READ_BIT
    if write then res |= GL_MAP_WRITE_BIT
    if persistent then res |= GL_MAP_PERSISTENT_BIT
    if coherent then res |= GL_MAP_COHERENT_BIT
    if invalidateRange then res |= GL_MAP_INVALIDATE_RANGE_BIT
    if invalidateBuffer then res |= GL_MAP_INVALIDATE_BUFFER_BIT
    if flushExplicit then res |= GL_MAP_FLUSH_EXPLICIT_BIT

    res
