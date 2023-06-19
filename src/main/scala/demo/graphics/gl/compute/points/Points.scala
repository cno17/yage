package demo.graphics.gl.compute.points

import test.graphics.gl.HelloWorld.width

import java.io.File
import java.nio.ByteBuffer
// import org.joml.Vector2f
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL15C.*
import org.lwjgl.opengl.GL20C.*
import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL42C.*
import org.lwjgl.opengl.GL43C.*
import org.lwjgl.opengl.GL45C.*
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL40C.GL_PATCHES
import yage.graphics.fw.App
import yage.graphics.fw.AppCreateInfo
import yage.graphics.gl.primitive.VertexArray
import yage.graphics.gl.resource.buffer.Buffer
import yage.graphics.gl.resource.buffer.BufferStorageFlags
import yage.graphics.gl.resource.buffer.BufferTarget
import yage.graphics.gl.shader.program.Program

import scala.collection.mutable.ArrayBuffer

/**
 * todo: indices, colors, interleaved, separated, quad
 */

object Points extends App:

  var vb: Buffer = null
  var va: VertexArray = null
  var cp: Program = null
  var dp: Program = null
  
  var num = 50
  var time = 0.0f

  override def info() =
    val res = AppCreateInfo()
    res.hints.debugContext = true
    res

  override def init() =
    vb = Buffer(8 * num, BufferStorageFlags())
    vb.bindTo(BufferTarget.Storage, 0)
    va = VertexArray()
    va.bind()
    va.bindVertexBuffer(vb, 0, 0, 8)
    va.enableAttribute(0)
    va.setAttributeFormat(0, 2, GL_FLOAT, false, 0)
    va.setAttributeBinding(0, 0)
    cp = Program(File(srcDir, "Compute.comp"))
    dp = Program(File(srcDir, "Draw.vert"), File(srcDir, "Draw.frag"))
    glClearColor(0, 0, 0, 1)
    glPointSize(4)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT)
    dp.use()
    glDrawArrays(GL_LINES, 0, num / 2)

  override def step(dt: Int) =
    time += 0.001f * dt.toFloat
    cp.use()
    cp.setUniform1f("time", time)
    glDispatchCompute(num / 1, 1, 1)
    glMemoryBarrier(GL_SHADER_STORAGE_BARRIER_BIT);
