package yage.graphics.gl.resource.buffer

import org.lwjgl.opengl.GL15C.GL_BUFFER_SIZE
import org.lwjgl.opengl.GL45C.glGetNamedBufferParameteri64
import yage.graphics.gl.resource.buffer.Buffer

trait BufferInfo:

  this: Buffer =>

  def size = glGetNamedBufferParameteri64(id, GL_BUFFER_SIZE)
