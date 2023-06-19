package test.graphics.gl.resource

import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL15C.*
import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL31C.*
import org.lwjgl.opengl.GL44C.*
import org.lwjgl.opengl.GL45C.*
import yage.graphics.fw.App
import yage.graphics.gl.ClassUtil
import org.lwjgl.BufferUtils
import yage.graphics.gl.resource.buffer.BufferStorageFlags
import yage.graphics.gl.resource.buffer.Buffer
import yage.graphics.gl.resource.Access
import yage.graphics.gl.resource.buffer.BufferMapFlags
import yage.graphics.fw.AppCreateInfo

object BufferTest extends App:

  override def info() =
    val res = AppCreateInfo()
    res.hints.debugContext = true
    res

  override def init() =
    val bsf = BufferStorageFlags()
    bsf.dynamicStorage = true
    bsf.mapWrite = true
    val buffer = Buffer(1024, bsf)
    // val data = BufferUtils.createByteBuffer(1024)
    // glNamedBufferSubData(id, 0, data)
    val bmf = BufferMapFlags()
    bmf.read = true
    // buffer.map(bmf, 0, 1024)
    close()


/*    
  def init2() =
    println(ClassUtil.findDeclaringClassOf("GL_MAP_PERSISTENT_BIT"))
    val id = glCreateBuffers()
    glNamedBufferStorage(id, 1024, GL_DYNAMIC_STORAGE_BIT | GL_MAP_READ_BIT)
    // val data = BufferUtils.createByteBuffer(1024)
    // glNamedBufferSubData(id, 0, data)
    glMapNamedBufferRange(id, 0, 1024, GL_MAP_READ_BIT)
    close()
*/
