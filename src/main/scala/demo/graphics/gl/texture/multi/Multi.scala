package demo.graphics.gl.texture.multi

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL13C.*
import yage.math.Vec4
import yage.graphics.fw.App
import yage.graphics.gl.shader.Shader
import yage.graphics.gl.shader.ShaderStage
import yage.graphics.gl.shader.program.Program
import yage.scene.geometry.*

import java.io.File
import java.nio.ByteBuffer
import yage.graphics.gl.resource.texture.Texture2
import javax.imageio.ImageIO

object Multi extends App:

  var program: Program = null
  var geometry: Geometry = null
  var texture0: Texture2 = null
  var texture1: Texture2 = null

  override def init() =
    program = Program(File(srcDir, "Draw.vert"), File(srcDir, "Draw.frag"))
    geometry = Quad(0.8, 0.8)
    val f0 = File("src/main/resources/image/Woman1.jpg")
    val f1 = File("src/main/resources/image/Wood1.jpg")
    texture0 = Texture2(f0)
    texture1 = Texture2(f1)
    glClearColor(0, 0, 0, 1)
    glActiveTexture(GL_TEXTURE0)
    glBindTexture(GL_TEXTURE_2D, texture0.id)
    glActiveTexture(GL_TEXTURE1)
    glBindTexture(GL_TEXTURE_2D, texture1.id)
    program.setUniform1i("tex0", 0)
    program.setUniform1i("tex1", 1)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT)
    program.use()
    geometry.draw()
