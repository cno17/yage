package yage.graphics.gl.resource.buffer

import java.nio.ByteBuffer
import org.lwjgl.opengl.GL15C.glBindBuffer
import org.lwjgl.opengl.GL15C.glDeleteBuffers
import org.lwjgl.opengl.GL15C.GL_ARRAY_BUFFER
import org.lwjgl.opengl.GL15C.GL_ELEMENT_ARRAY_BUFFER
import org.lwjgl.opengl.GL21C.GL_PIXEL_PACK_BUFFER
import org.lwjgl.opengl.GL21C.GL_PIXEL_UNPACK_BUFFER
import org.lwjgl.opengl.GL30C.glBindBufferBase
import org.lwjgl.opengl.GL30C.glBindBufferRange
import org.lwjgl.opengl.GL30C.GL_MAP_FLUSH_EXPLICIT_BIT
import org.lwjgl.opengl.GL30C.GL_MAP_INVALIDATE_BUFFER_BIT
import org.lwjgl.opengl.GL30C.GL_MAP_INVALIDATE_RANGE_BIT
import org.lwjgl.opengl.GL30C.GL_MAP_READ_BIT
import org.lwjgl.opengl.GL30C.GL_MAP_WRITE_BIT
import org.lwjgl.opengl.GL31C.GL_COPY_READ_BUFFER
import org.lwjgl.opengl.GL31C.GL_COPY_WRITE_BUFFER
import org.lwjgl.opengl.GL31C.GL_TEXTURE_BUFFER
import org.lwjgl.opengl.GL31C.GL_UNIFORM_BUFFER
import org.lwjgl.opengl.GL40C.GL_DRAW_INDIRECT_BUFFER
import org.lwjgl.opengl.GL42C.GL_ATOMIC_COUNTER_BUFFER
import org.lwjgl.opengl.GL43C.glInvalidateBufferData
import org.lwjgl.opengl.GL43C.glInvalidateBufferSubData
import org.lwjgl.opengl.GL43C.GL_DISPATCH_INDIRECT_BUFFER
import org.lwjgl.opengl.GL43C.GL_SHADER_STORAGE_BUFFER
import org.lwjgl.opengl.GL44C.GL_CLIENT_STORAGE_BIT
import org.lwjgl.opengl.GL44C.GL_DYNAMIC_STORAGE_BIT
import org.lwjgl.opengl.GL44C.GL_MAP_COHERENT_BIT
import org.lwjgl.opengl.GL44C.GL_MAP_PERSISTENT_BIT
import org.lwjgl.opengl.GL44C.GL_QUERY_BUFFER
import org.lwjgl.opengl.GL45C.glClearNamedBufferData
import org.lwjgl.opengl.GL45C.glCreateBuffers
import org.lwjgl.opengl.GL45C.glFlushMappedNamedBufferRange
import org.lwjgl.opengl.GL45C.glGetNamedBufferSubData
import org.lwjgl.opengl.GL45C.glMapNamedBuffer
import org.lwjgl.opengl.GL45C.glMapNamedBufferRange
import org.lwjgl.opengl.GL45C.glNamedBufferStorage
import org.lwjgl.opengl.GL45C.glNamedBufferSubData
import org.lwjgl.opengl.GL45C.glUnmapNamedBuffer
import yage.graphics.gl.resource.Access
import yage.graphics.gl.resource.Resource
// import yage.graphics.gl.Flags
import org.lwjgl.opengl.GL45C.glGetNamedBufferSubData
import org.lwjgl.opengl.GL45C.glNamedBufferSubData
import org.lwjgl.BufferUtils
import yage.graphics.ByteBufferUtil

object Buffer extends ByteBufferUtil:

  // default args?

  def apply(size: Long): Buffer = apply(size, BufferStorageFlags())

  def apply(size: Long, flags: BufferStorageFlags) =
    val res = new Buffer()
    res.allocate(size, flags)
    res

  def apply(data: ByteBuffer): Buffer = apply(data, BufferStorageFlags())
  
  def apply(data: ByteBuffer, flags: BufferStorageFlags) =
    val res = new Buffer()
    res.allocate(data.rewind(), flags)
    res

  // DRY  

  def apply(data: Array[Int], flags: BufferStorageFlags) =
    val res = new Buffer()
    res.allocate(toByteBuffer(data), flags)
    res

  def apply(data: Array[Float], flags: BufferStorageFlags) =
    val res = new Buffer()
    res.allocate(toByteBuffer(data), flags)
    res

/**
 * Todo: 
 * - clear, copy
 * - c++ stream operators: <<, >>   
 */

class Buffer extends Resource, BufferInfo:

  override def create() = glCreateBuffers

  def allocate(size: Long, flags: BufferStorageFlags) = glNamedBufferStorage(id, size, flags.toInt()())
  def allocate(data: ByteBuffer, flags: BufferStorageFlags) = glNamedBufferStorage(id, data, flags.toInt()())

  def load(off: Long, data: ByteBuffer) = glNamedBufferSubData(id, off, data)
  def store(off: Long, data: ByteBuffer) = glGetNamedBufferSubData(id, off, data)

  def invalidate() = glInvalidateBufferData(id);
  def invalidate(off: Long, len: Long) = glInvalidateBufferSubData(id, off, len);

  def bindTo(t: BufferTarget) = glBindBuffer(t.value, id)
  def bindTo(t: BufferTarget, i: Int) = glBindBufferBase(t.value, i, id)
  def bindTo(t: BufferTarget, i: Int, off: Long, len: Long) = glBindBufferRange(t.value, i, id, off, len)

  def unbindFrom(t: BufferTarget) = glBindBuffer(t.value, 0)

  def map(a: Access) = glMapNamedBuffer(id, a.value)
  def map(a: Access, off: Long, len: Long) = glMapNamedBufferRange(id, off, len, a.value)

  def unmap = glUnmapNamedBuffer(id)

  def flush(off: Long, len: Long) = glFlushMappedNamedBufferRange(id, off, len)

  override def destroy() = glDeleteBuffers(id)

  def <<(off: Long, data: ByteBuffer) = load(off, data)
  def >>(off: Long, data: ByteBuffer) = store(off, data)


/*


  def clear(off: Long, ext: Long) =
    val internalFormat = 0
    val format = 0
    val typ = 0
    glClearNamedBufferData(id, internalFormat, format, typ, null.asInstanceOf[ByteBuffer])
 */
