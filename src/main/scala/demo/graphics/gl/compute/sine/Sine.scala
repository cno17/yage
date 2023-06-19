package demo.graphics.gl.compute.sine

import java.io.File
import java.nio.ByteBuffer
import org.lwjgl.opengl.GL11C.glClear
import org.lwjgl.opengl.GL11C.glClearColor
import org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT
import org.lwjgl.opengl.GL42C.glMemoryBarrier
import org.lwjgl.opengl.GL42C.GL_SHADER_IMAGE_ACCESS_BARRIER_BIT
import org.lwjgl.opengl.GL43C.glDispatchCompute
import org.lwjgl.BufferUtils
import yage.graphics.fw.App
import yage.graphics.gl.resource.texture.Texture2
import yage.graphics.gl.resource.Access
import yage.graphics.gl.resource.Format
import yage.graphics.gl.shader.program.Program
import yage.graphics.gl.shader.Shader
import yage.graphics.gl.shader.ShaderStage
import yage.scene.geometry.Geometry
import yage.scene.geometry.Quad

object Sine extends App:

  val sizeI = 256
  val sizeJ = 256

  var step = 0
  var texture: Texture2 = null
  var geometry: Geometry = null
  var drawProgram: Program = null
  var stepProgram: Program = null

  override def init() =

    texture = new Texture2()
    texture.allocate(1, Format.FP32_4, sizeI, sizeJ)
    texture.bindToTextureUnit(0)
    texture.bindToImageUnit(0, Access.WriteOnly, Format.FP32_4)

    geometry = Quad(0.8, 0.8)

    val d = srcDir
    drawProgram = Program(File(d, "Draw.vert"), File(d, "Draw.frag"))
    drawProgram.setUniform1i("sampler", 0)
    stepProgram = Program(File(d, "Step.comp"))
    stepProgram.setUniform1i("image", 0)

    glClearColor(0, 0, 0, 1)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT);
    drawProgram.use()
    geometry.draw()

  override def step(dt: Int) =
    step += 1
    stepProgram.use()
    stepProgram.setUniform1i("step", step)
    glDispatchCompute(sizeI / 32, sizeJ / 32, 1)
    glMemoryBarrier(GL_SHADER_IMAGE_ACCESS_BARRIER_BIT)
