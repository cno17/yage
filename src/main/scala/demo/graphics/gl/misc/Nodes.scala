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
import yage.scene.Node
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object Nodes extends App:

  val maxDepth = 2
  val minTra = 10.0f
  val maxTra = 20.0f
  val minVel = 0.01f
  val maxVel = 0.10f

  var program: Program = null
  var geometry: Geometry = null
  var node: Node = null

  // var matWN = Mat4()
  var matWV = Mat4().toTranslation(0, 0, 100)
  var matVW = Mat4().set(matWV).invert()
  var matCV = Mat4().toFrustum(-1, 1, -1, 1, 2, 1000)
  var matVN = Mat4()
  var matCN = Mat4()
  var buf = BufferUtils.createFloatBuffer(16)

  override def init() =
    debugMessenger.disable()
    program = Program(File(srcDir, "Nodes.vert"), File(srcDir, "Nodes.frag"))
    program.use()
    // geometry = ObjGeometry(File("src/main/resources/meshes/BobbleTree.obj"))
    geometry = Torus(1, 10, 4, 16)
    node = new Node()
    node.geometry = Some(geometry)
    createChildren(node, 1)
    node.updateTrafos()
    glClearColor(0, 0, 0, 1)
    glPolygonMode(GL_FRONT_AND_BACK, GL_LINE)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT);
    // program.setUniform4x4f("matCM", buf)
    // geometry.draw()
    draw(node)

/*    
  override def step(dt: Int) =
    matVM =*(matVW, matWM)
    matCM =*(matCV, matVM)
    matCM >> (buf, 0)
*/

  private def createChildren(n: Node, depth: Int): Unit =
    if depth < maxDepth then
      val n1 = Node(n, geometry)
      val n2 = Node(n, geometry)
      val n3 = Node(n, geometry)
      n1.matPN.toTranslation(rnd(minTra, maxTra), 0, 0)
      n2.matPN.toTranslation(0, rnd(minTra, maxTra), 0)
      n3.matPN.toTranslation(0, 0, rnd(minTra, maxTra))
      n.children = Some(Array(n1, n2, n3))
      createChildren(n1, depth + 1)
      createChildren(n2, depth + 1)
      createChildren(n3, depth + 1)

  private def rnd(min: Float, max: Float) = min + (max - min) * Random.nextFloat()

  private def draw(n: Node) =
    matVN =*(matVW, node.matWN)
    matCN =*(matCV, matVN)
    matCN >> (buf, 0)
    program.setUniform4x4f("matCN", buf)
    for g <- node.geometry do
      g.draw()
