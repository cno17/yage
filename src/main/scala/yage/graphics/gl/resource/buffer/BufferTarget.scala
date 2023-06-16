package yage.graphics.gl.resource.buffer

import org.lwjgl.opengl.GL15C.GL_ARRAY_BUFFER
import org.lwjgl.opengl.GL15C.GL_ELEMENT_ARRAY_BUFFER
import org.lwjgl.opengl.GL21C.GL_PIXEL_PACK_BUFFER
import org.lwjgl.opengl.GL21C.GL_PIXEL_UNPACK_BUFFER
import org.lwjgl.opengl.GL31C.GL_COPY_READ_BUFFER
import org.lwjgl.opengl.GL31C.GL_COPY_WRITE_BUFFER
import org.lwjgl.opengl.GL31C.GL_TEXTURE_BUFFER
import org.lwjgl.opengl.GL31C.GL_UNIFORM_BUFFER
import org.lwjgl.opengl.GL40C.GL_DRAW_INDIRECT_BUFFER
import org.lwjgl.opengl.GL42C.GL_ATOMIC_COUNTER_BUFFER
import org.lwjgl.opengl.GL43C.GL_DISPATCH_INDIRECT_BUFFER
import org.lwjgl.opengl.GL43C.GL_SHADER_STORAGE_BUFFER
import org.lwjgl.opengl.GL44C.GL_QUERY_BUFFER

/**
 * Vertex -> Array? isIndexed?
 */

enum BufferTarget(val value: Int):

  case Vertex extends BufferTarget(GL_ARRAY_BUFFER)
  case Index extends BufferTarget(GL_ELEMENT_ARRAY_BUFFER)
  case Uniform extends BufferTarget(GL_UNIFORM_BUFFER)
  case Storage extends BufferTarget(GL_SHADER_STORAGE_BUFFER)
  case Counter extends BufferTarget(GL_ATOMIC_COUNTER_BUFFER)
  case Texture extends BufferTarget(GL_TEXTURE_BUFFER)
  case DrawIndirect extends BufferTarget(GL_DRAW_INDIRECT_BUFFER)
  case DispatchIndirect extends BufferTarget(GL_DISPATCH_INDIRECT_BUFFER)
  case CopyRead extends BufferTarget(GL_COPY_READ_BUFFER)
  case CopyWrite extends BufferTarget(GL_COPY_WRITE_BUFFER)
  case PixelPack extends BufferTarget(GL_PIXEL_PACK_BUFFER)
  case PixelUnpack extends BufferTarget(GL_PIXEL_UNPACK_BUFFER)
  case Query extends BufferTarget(GL_QUERY_BUFFER)
