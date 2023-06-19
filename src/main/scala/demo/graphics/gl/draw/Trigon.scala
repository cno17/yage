package demo.graphics.gl.draw

import test.graphics.gl.HelloWorld.width

import java.io.File
import java.nio.ByteBuffer
// import org.joml.Vector2f
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL15C.*
import org.lwjgl.opengl.GL20C.*
import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL45C.*
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL40C.GL_PATCHES
import yage.graphics.fw.App
import yage.graphics.fw.AppCreateInfo
import yage.graphics.gl.primitive.VertexArray
import yage.graphics.gl.resource.buffer.Buffer
import yage.graphics.gl.resource.buffer.BufferStorageFlags
import yage.graphics.gl.shader.program.Program

import scala.collection.mutable.ArrayBuffer

/**
 * todo: indices, colors, interleaved, separated, quad
 */

object Trigon extends App:

  var va: VertexArray = null
  var dp: Program = null

  override def info() =
    val res = AppCreateInfo()
    res.hints.debugContext = true
    res

  override def init() =
    va = createVertexArray()
    dp = Program(File(srcDir, "Simple.vert"), File(srcDir, "Simple.frag"))
    glClearColor(0, 0, 0, 1)
    glPolygonMode(GL_FRONT_AND_BACK, GL_FILL)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT)
    glViewport(0, 0, window.innerSizeX / 2, window.innerSizeY / 2)
    va.bind()
    dp.use()
    glDrawArrays(GL_TRIANGLES, 0, 3)

  def createVertexArray() =
    val ab = ArrayBuffer[Float]()
    ab ++= Array(-0.5f, -0.5f, 1.0f, 0.0f, 0.0f, 1.0f)
    ab ++= Array(+0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 1.0f)
    ab ++= Array(+0.0f, +0.5f, 0.0f, 0.0f, 1.0f, 1.0f)
    val bb = BufferUtils.createByteBuffer(72)
    for f <- ab do bb.putFloat(f)
    bb.rewind()
    val buf = Buffer(0, BufferStorageFlags()) // needs work Buffer(bb)
    val res = VertexArray()
    res.bindVertexBuffer(buf, 0, 0, 24)
    res.enableAttribute(0)
    res.enableAttribute(1)
    res.setAttributeFormat(0, 2, GL_FLOAT, false, 0)
    res.setAttributeFormat(1, 4, GL_FLOAT, false, 0)
    res.setAttributeBinding(0, 0)
    res.setAttributeBinding(1, 0)
    res
