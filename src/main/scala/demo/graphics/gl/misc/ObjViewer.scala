package demo.graphics.gl.misc

import java.io.File
import java.nio.ByteBuffer
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.BufferUtils
import yage.graphics.fw.App
import yage.graphics.fw.AppCreateInfo
import yage.graphics.fw.EventMode
import yage.graphics.gl.shader.program.Program
import yage.graphics.gl.shader.Shader
import yage.graphics.gl.shader.ShaderStage
import yage.scene.geometry.*
import yage.scene.geometry.*
import yage.scene.TrafoController
import yage.scene.TrafoKeyController

import yage.math.Mat4
import yage.math.Vec4
import yage.graphics.fw.Key
import yage.graphics.fw.MouseButton

object ObjViewer extends App:

  var program: Program = null
  var geometry: Geometry = null
  var controller: TrafoController = null

  var matWM = Mat4()
  var matWV = Mat4().toTranslation(0, 0, 50)
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
    val d = File("src/main/scala/demo/graphics/gl/misc")
    program = Program(File(d, "Simple.vert"), File(d, "Simple.frag"))
    // geometry = ObjGeometry(File("src/main/resources/mesh/BobbleTree.obj"))
    geometry = Sphere(10, 32, 16)
    // geometry = Ellipsoid(10, 15, 5, 32, 16)
    println(geometry.posA.size)
    controller = new TrafoKeyController(this, matWM)
    glClearColor(0, 0, 0, 1)
    glFrontFace(GL_CCW)
    glPolygonMode(GL_FRONT_AND_BACK, GL_FILL)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT);
    program.use()
    program.setUniform4x4f("matCM", buf)
    geometry.draw()

  override def step(dt: Int) =
    matVM =*(matVW, matWM)
    matCM =*(matCV, matVM)
    matCM >> (buf, 0)

/*    
  def offZ = 
    val min = geometry.box.mMin
    val max = geometry.box.mMax
    var abs = 0f
    abs = Math.max(Math.abs(min.x), abs)
    abs = Math.max(Math.abs(min.y), abs)
    abs = Math.max(Math.abs(min.z), abs)
    abs = Math.max(Math.abs(max.x), abs)
    abs = Math.max(Math.abs(max.y), abs)
    abs = Math.max(Math.abs(max.z), abs)
    abs * 5
*/
