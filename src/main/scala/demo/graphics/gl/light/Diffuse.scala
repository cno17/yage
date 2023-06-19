package demo.graphics.gl.light

import java.io.File
import java.nio.ByteBuffer
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
import yage.math.Mat4
import yage.math.Vec4
import yage.scene.geometry.*
import yage.scene.TrafoController
import yage.scene.TrafoKeyController

object Diffuse extends App:

  var program: Program = null
  var geometry: Geometry = null
  var norGeometry: Geometry = null
  var controller: TrafoController = null

  var matWM = Mat4()
  var matWV = Mat4().toTranslation(0, 0, 50)
  var matVW = Mat4().set(matWV).invert() // TODO matWV.inverted
  var matCV = Mat4().toFrustum(-1, 1, -1, 1, 2, 1000)
  var matVM = Mat4()
  var buf = BufferUtils.createFloatBuffer(16)

  override def info() =
    val res = AppCreateInfo()
    res.eventMode = EventMode.Wait
    res

  override def init() =
    program = Program(File(srcDir, "Diffuse.vert"), File(srcDir, "Diffuse.frag"))
    // program.use()
    // geometry = Trigon()
    // geometry = Quad(2, 1)
    // geometry = Grid(1, 1, 8, 5)
    // geometry = Torus(0.2f, 1.0f, 32, 16)
    // geometry = Sphere(1.0f, 16, 8)
    // geometry = Cylinder(0.5f, 0.2f, 1.0f, 32, 4)
    geometry = ObjGeometry(File("src/main/resources/mesh/Lamp.obj"))
    norGeometry = NormalGeometry(geometry, 0.2)
    controller = TrafoKeyController(this, matWM)
    glClearColor(0, 0, 0, 1)
    glPolygonMode(GL_FRONT_AND_BACK, GL_FILL)
    glFrontFace(GL_CCW)
    glEnable(GL_CULL_FACE)
    glEnable(GL_DEPTH_TEST)

  override def draw() =
    updateUniforms()
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    program.use()
    geometry.draw()
    program.setUniform3f("dInt", 0.0f, 0.8f, 0.0f)
    program.setUniform3f("dRef", 0.0f, 0.8f, 0.0f)
    // if norGeometry != null then norGeometry.draw()

  def updateUniforms() =
    program.setUniform4f("lightPosV", 0, 0, 0, 1)
    program.setUniform3f("dInt", 0.8f, 0.8f, 0.8f)
    program.setUniform3f("dRef", 0.8f, 0.8f, 0.8f)
    matVM =* (matVW, matWM)
    matVM >> (buf, 0)
    program.setUniform4x4f("matVM", buf)
    matCV >> (buf, 0)
    program.setUniform4x4f("matCV", buf)

/*
  override def step(dt: Int) =
    matVM =*(matVW, matWM)
    matCM =*(matCV, matVM)
    matCM >> (buf, 0)
 */
