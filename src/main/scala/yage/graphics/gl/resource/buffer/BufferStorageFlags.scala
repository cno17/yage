package yage.graphics.gl.resource.buffer

import org.lwjgl.opengl.GL30C.GL_MAP_READ_BIT
import org.lwjgl.opengl.GL30C.GL_MAP_WRITE_BIT
import org.lwjgl.opengl.GL44C.GL_DYNAMIC_STORAGE_BIT
import org.lwjgl.opengl.GL44C.GL_MAP_COHERENT_BIT
import org.lwjgl.opengl.GL44C.GL_MAP_PERSISTENT_BIT
import yage.graphics.Flags

class BufferStorageFlags extends Flags:

  var dynamicStorage = false
  var mapRead = false
  var mapWrite = false
  var mapPersistent = false
  var mapCoherent = false

  override def clear() =
    dynamicStorage = false
    mapRead = false
    mapWrite = false
    mapPersistent = false
    mapCoherent = false

  override def toInt() =
    var ret = 0
    if dynamicStorage then ret |= GL_DYNAMIC_STORAGE_BIT
    if mapRead then ret |= GL_MAP_READ_BIT
    if mapWrite then ret |= GL_MAP_WRITE_BIT
    if mapPersistent then ret |= GL_MAP_PERSISTENT_BIT
    if mapCoherent then ret |= GL_MAP_COHERENT_BIT
    ret


// var clientStorage = false
// if clientStorage then ret |= GL_CLIENT_STORAGE_BIT
