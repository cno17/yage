package demo.graphics.gl.texture.chess

import java.io.File
import java.nio.ByteBuffer
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL13C.*
import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL42C.*
import org.lwjgl.opengl.GL43C.*
import org.lwjgl.BufferUtils
import yage.graphics.fw.App
import yage.graphics.gl.resource.texture.sampler.MagFilter
import yage.graphics.gl.resource.texture.sampler.MinFilter
import yage.graphics.gl.resource.texture.Texture2
import yage.graphics.gl.resource.Access
import yage.graphics.gl.resource.Format
import yage.graphics.gl.shader.program.Program
import yage.graphics.ByteBufferUtil
import yage.math.Vec4
import yage.scene.geometry.*

object Chess extends App, ByteBufferUtil:

  var sizeI = 2
  var sizeJ = 2
  var geometry: Geometry = null
  var texture: Texture2 = null
  var drawProgram: Program = null

  val dataFP32_1 = toByteBuffer(Array(
    1f, 
    0f,
    0f,
    1f
  ))

  val dataFP32_3 = toByteBuffer(Array(
    1.0f, 0.0f, 0.0f,
    0.0f, 1.0f, 0.0f,
    0.0f, 0.0f, 1.0f,
    1.0f, 1.0f, 0.0f
  ))

  val dataFP32_4 = toByteBuffer(Array(
    1f, 1f, 0f, 1f,
    0f, 0f, 1f, 1f,
    0f, 0f, 1f, 1f,
    1f, 1f, 0f, 1f
  ))

  val dataSI08_1 = toByteBuffer(Array(
    127, 0, 0, 127
  ))

  override def init() =
    val d = srcDir
    geometry = Quad(0.8, 0.8)
    texture = new Texture2()
    texture.setMinFilter(MinFilter.Nearest)
    texture.setMagFilter(MagFilter.Nearest)
    texture.allocate(1, Format.FP32_1, sizeI, sizeJ)
    texture.load(0, Format.FP32_1, dataFP32_1)
    texture.bindToTextureUnit(0)
    drawProgram = Program(File(d, "Draw.vert"), File(d, "Draw.frag"))
    glClearColor(0, 0, 0, 1)

  override def draw() =
    drawProgram.use()
    geometry.draw()
