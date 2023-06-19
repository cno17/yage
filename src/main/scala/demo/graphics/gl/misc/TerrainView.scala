package demo.graphics.gl.misc

import org.lwjgl.opengl.GL11C.*
import org.lwjgl.BufferUtils
import yage.graphics.fw.App
import yage.graphics.fw.AppCreateInfo
import yage.graphics.fw.EventMode
import yage.graphics.fw.Key
import yage.graphics.fw.MouseButton
import yage.graphics.gl.shader.program.Program
import yage.graphics.gl.shader.Shader
import yage.graphics.gl.shader.ShaderStage
import yage.image.ImageF
import yage.image.grid.Pixel
import yage.image.producer.HillGeneratorF
import yage.math.Mat4
import yage.math.Vec4
import yage.scene.geometry.*
import yage.scene.geometry.*
import yage.scene.TrafoController
import yage.scene.TrafoKeyController

import java.io.File
import java.nio.ByteBuffer

object TerrainView extends App:

  var program: Program = null
  var geometry: Geometry = null
  var controller: TrafoController = null

  var matWM = Mat4()
  var matWV = Mat4().toTranslation(0, 0, 100)
  var matVW = Mat4().set(matWV).invert()
  var matCV = Mat4().toFrustum(-1, 1, -1, 1, 2, 1000)
  var matVM = Mat4()
  var matCM = Mat4()
  var buf = BufferUtils.createFloatBuffer(16)

  override def info() =
    val res = AppCreateInfo()
    res.eventMode = EventMode.Wait
    res

  override def init() =
    debugMessenger.disable()
    program = Program(File(srcDir, "Simple.vert"), File(srcDir, "Simple.frag"))
    geometry = TerrainGeometry(createImage)
    // geometry = Sphere(10, 32, 16)
    controller = new TrafoKeyController(this, matWM)
    glClearColor(0, 0, 0, 1)
    glFrontFace(GL_CCW)
    glPolygonMode(GL_FRONT_AND_BACK, GL_LINE)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT);
    program.use()
    program.setUniform4x4f("matCM", buf)
    geometry.draw()

  override def step(dt: Int) =
    matVM =*(matVW, matWM)
    matCM =*(matCV, matVM)
    matCM >> (buf, 0)

  protected def createImage =
    val min = Pixel(0, 0)
    val max = Pixel(100, 100)
    val img = HillGeneratorF(min, max, 100, 10, 20).exec.asInstanceOf[ImageF]
    val sca = img.values.max
    img.scale(10.0f / img.values.max)
    println(img.values.max)
    img
