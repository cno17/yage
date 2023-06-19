package demo.graphics.gl.draw.points

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
import yage.graphics.ByteBufferUtil

/**
 * todo: indices, colors, interleaved, separated, quad
 */

object Points extends App, ByteBufferUtil:

  var va: VertexArray = null
  var dp: Program = null

  override def init() =
    va = createVertexArray()
    dp = Program(File(srcDir, "Draw.vert"), File(srcDir, "Draw.frag"))
    glClearColor(0, 0, 0, 1)
    // glPolygonMode(GL_FRONT_AND_BACK, GL_FILL)
    glPointSize(24)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT)
    va.bind()
    dp.use()
    glDrawArrays(GL_POINTS, 0, 3)

  def createVertexArray() =
    val data = toByteBuffer(Array(-0.5f, -0.5f, 0.8f, 0.0f, 0.0f, 0.8f))
    val vb = Buffer(data, BufferStorageFlags())
    val va = VertexArray()
    va.bindVertexBuffer(vb, 0, 0, 8)
    va.enableAttribute(0)
    va.setAttributeFormat(0, 2, GL_FLOAT, false, 0)
    va.setAttributeBinding(0, 0)
    va

  def createProgram() =
    val d = srcDir
    Program(File(d, "Draw.vert"), File(d, "Draw.frag"))
