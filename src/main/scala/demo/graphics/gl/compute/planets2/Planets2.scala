package demo.graphics.gl.compute.planets2

import java.io.File
import java.nio.ByteBuffer
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL11C.glDrawArrays
import org.lwjgl.opengl.GL32C.GL_PROGRAM_POINT_SIZE
import org.lwjgl.opengl.GL42C.glMemoryBarrier
import org.lwjgl.opengl.GL43C.glDispatchCompute
import org.lwjgl.opengl.GL43C.GL_SHADER_STORAGE_BARRIER_BIT
import org.lwjgl.BufferUtils
import scala.util.Random
import yage.graphics.fw.App
import yage.graphics.gl.primitive.VertexArray
import yage.graphics.gl.resource.buffer.Buffer
import yage.graphics.gl.resource.buffer.BufferMapFlags
import yage.graphics.gl.resource.buffer.BufferStorageFlags
import yage.graphics.gl.resource.buffer.BufferTarget
import yage.graphics.gl.resource.texture.Texture2
import yage.graphics.gl.resource.Access
import yage.graphics.gl.shader.program.Program
import yage.graphics.gl.shader.Shader
import yage.graphics.gl.shader.ShaderStage
import yage.math.Vec4
import yage.scene.geometry.*

// suns and planets

object Planets2 extends App:

  val numS = 2
  val numP = 8
  var bufSM: Buffer = null
  var bufSP: Buffer = null
  var bufPM: Buffer = null
  var bufPP: Buffer = null
  var bufPV: Buffer = null
  var vaS: VertexArray = null
  var vaP: VertexArray = null
  var dp: Program = null
  var sp: Program = null
  val rnd = Random()

  override def init() =
    bufSM = createSunMassBuffer
    bufSP = createSunPosBuffer
    bufPM = createPlanetMassBuffer
    bufPP = createPlanetPosBuffer
    bufPV = createPlanetVelBuffer
    bufSM.bindTo(BufferTarget.Storage, 0)
    bufSP.bindTo(BufferTarget.Storage, 1)
    bufPM.bindTo(BufferTarget.Storage, 2)
    bufPP.bindTo(BufferTarget.Storage, 3)
    bufPV.bindTo(BufferTarget.Storage, 4)
    vaS = VertexArray()
    vaS.bind()
    vaS.bindVertexBuffer(bufSP, 0, 0, 8)
    vaS.enableAttribute(0)
    vaS.setAttributeFormat(0, 2, GL_FLOAT, false, 0)
    vaS.setAttributeBinding(0, 0)
    vaP = VertexArray()
    vaP.bind()
    vaP.bindVertexBuffer(bufPP, 0, 0, 8)
    vaP.enableAttribute(0)
    vaP.setAttributeFormat(0, 2, GL_FLOAT, false, 0)
    vaP.setAttributeBinding(0, 0)
    dp = Program(File(srcDir, "Draw.vert"), File(srcDir, "Draw.frag"))
    sp = Program(File(srcDir, "Step.comp"))
    glEnable(GL_PROGRAM_POINT_SIZE)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT)
    dp.use()
    vaS.bind()
    glDrawArrays(GL_POINTS, 0, numS)
    vaP.bind()
    glDrawArrays(GL_POINTS, 0, numP)
    sp.use()
    glDispatchCompute(numP / 4, 1, 1)
    glMemoryBarrier(GL_SHADER_STORAGE_BARRIER_BIT)

    // todo: math.util:
    // create random points
    // write array of points (iterable) to byte buffer
    // ByteBuffer extensions: putVec

  def createSunMassBuffer =
    val bb = BufferUtils.createByteBuffer(4 * numS)
    for i <- 0 to numS - 1 do
      bb.putFloat(5.0f + 15.0f * rnd.nextFloat())
    Buffer(bb)

  def createSunPosBuffer =
    val bb = BufferUtils.createByteBuffer(8 * numS)
    for i <- 0 to numS - 1 do
      bb.putFloat(-0.8f + 1.6f * rnd.nextFloat())
      bb.putFloat(-0.8f + 1.6f * rnd.nextFloat())
    Buffer(bb)

  def createPlanetMassBuffer =
    val bb = BufferUtils.createByteBuffer(4 * numP)
    for i <- 0 to numP - 1 do
      bb.putFloat(1.0f + 2.0f * rnd.nextFloat())
    Buffer(bb)

  def createPlanetPosBuffer =
    val bb = BufferUtils.createByteBuffer(8 * numP)
    for i <- 0 to numP - 1 do
      bb.putFloat(-1.0f + 2.0f * rnd.nextFloat())
      bb.putFloat(-1.0f + 2.0f * rnd.nextFloat())
    Buffer(bb)

  def createPlanetVelBuffer =
    val bb = BufferUtils.createByteBuffer(8 * numP)
    for i <- 0 to numP - 1 do
      bb.putFloat(-0.1f + 0.2f * rnd.nextFloat())
      bb.putFloat(-0.1f + 0.2f * rnd.nextFloat())
    Buffer(bb)
