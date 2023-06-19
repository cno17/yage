package test.graphics.gl

import org.lwjgl.opengl.GL30C.GL_MAP_READ_BIT
import org.lwjgl.opengl.GL30C.GL_MAP_WRITE_BIT
import org.lwjgl.opengl.GL44C.GL_CLIENT_STORAGE_BIT
import org.lwjgl.opengl.GL44C.GL_DYNAMIC_STORAGE_BIT
import org.lwjgl.opengl.GL44C.GL_MAP_COHERENT_BIT
import org.lwjgl.opengl.GL44C.GL_MAP_PERSISTENT_BIT


object FlagsDesignTest:

  private enum BufferStorageFlag(val value: Int):

    case ClientStorage extends BufferStorageFlag(GL_CLIENT_STORAGE_BIT)
    case DynamicStorage extends BufferStorageFlag(GL_DYNAMIC_STORAGE_BIT)
    case MapRead extends BufferStorageFlag(GL_MAP_READ_BIT)
    case MapWrite extends BufferStorageFlag(GL_MAP_WRITE_BIT)
    case MapPersistent extends BufferStorageFlag(GL_MAP_PERSISTENT_BIT)
    case MapCoherent extends BufferStorageFlag(GL_MAP_COHERENT_BIT)

  private class BufferStorageFlags(val flags: BufferStorageFlag*):

    def toInt =
      var res = 0
      for f <- flags do res |= f.value
      res

  def main(args: Array[String]) =
    import BufferStorageFlag.*
    val f = BufferStorageFlags(DynamicStorage, MapRead, MapPersistent, MapWrite)
    println(f.toInt)
