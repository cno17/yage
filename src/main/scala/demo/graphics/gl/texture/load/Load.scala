package demo.graphics.gl.texture.load

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL13C.*
import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL31C.*
import org.lwjgl.opengl.GL45C.*
import yage.graphics.fw.App
import yage.graphics.ByteBufferUtil
import yage.graphics.gl.resource.texture.Texture2
import yage.graphics.gl.shader.Shader
import yage.graphics.gl.shader.ShaderStage
import yage.graphics.gl.shader.program.Program
import yage.math.Vec4
import yage.scene.geometry.*

import java.io.File
import java.nio.ByteBuffer
import javax.imageio.ImageIO
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object Load extends App, ByteBufferUtil:

  val rand = Random()
  var program: Program = null
  var geometry: Geometry = null
  // var texture: Texture2 = null
  var texture = 0

  override def init() =
    program = Program(File(srcDir, "Draw.vert"), File(srcDir, "Draw.frag"))
    geometry = Quad(0.8, 0.8)
    texture = createTexture()
    // texture = Texture2(File("src/main/resources/image/misc/grass1.jpg")).id
    glClearColor(0, 0, 0, 1)
    glActiveTexture(GL_TEXTURE0)
    glBindTexture(GL_TEXTURE_2D, texture)
    program.setUniform1i("tex", 0)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT)
    program.use()
    geometry.draw()

  def createTexture() =
    val si = 10
    val sj = 10
    val nt = si * sj
    val data = BufferUtils.createByteBuffer(4 * nt)
    for i <- 0 to nt - 1 do
      val nb = nextByte
      println(nb)
      data.put(nb)
      data.put(nb)
      data.put(nb)
      data.put(-1.toByte)
    data.rewind()
    val tex = glCreateTextures(GL_TEXTURE_2D)
    glTextureStorage2D(tex, 1, GL_RGBA32F, si, sj)
    glTextureSubImage2D(tex, 0, 0, 0, si, sj, GL_RGBA, GL_UNSIGNED_BYTE, data)
    // glTextureParameteri(tex, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
    // glTextureParameteri(tex, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
    tex

  def nextByte = (-128 + Math.abs(rand.nextInt()) % 256).toByte
