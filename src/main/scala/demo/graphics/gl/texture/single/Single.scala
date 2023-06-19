package demo.graphics.gl.texture.single
import java.io.File
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.Hashtable
import javax.imageio.ImageIO
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL13C.*
import org.lwjgl.BufferUtils
import yage.graphics.fw.App
import yage.graphics.gl.resource.texture.Texture2
import yage.graphics.gl.resource.Format
import yage.graphics.gl.shader.program.Program
import yage.graphics.gl.shader.Shader
import yage.graphics.gl.shader.ShaderStage
import yage.math.Vec4
import yage.scene.geometry.*

object Single extends App:

  var program: Program = null
  var geometry: Geometry = null
  var texture: Texture2 = null

  override def init() =
    program = Program(File(srcDir, "Draw.vert"), File(srcDir, "Draw.frag"))
    geometry = Quad(0.8, 0.8)
    val f1 = File("src/main/resources/image/Woman1jpg")
    val f2 = File("src/main/resources/image/Wood1.jpg")
    texture = Texture2(ImageIO.read(f1))
    glClearColor(0, 0, 0, 1)
    glActiveTexture(GL_TEXTURE0)
    glBindTexture(GL_TEXTURE_2D, texture.id)
    program.setUniform1i("tex", 0)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT)
    program.use()
    geometry.draw()
