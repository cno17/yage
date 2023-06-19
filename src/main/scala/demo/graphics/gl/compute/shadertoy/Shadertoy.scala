package demo.graphics.gl.compute.shadertoy

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL13C.*
import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL42C.*
import org.lwjgl.opengl.GL43C.*
import yage.math.Vec4
import yage.graphics.fw.App
import yage.graphics.gl.shader.Shader
import yage.graphics.gl.shader.ShaderStage
import yage.graphics.gl.shader.program.Program
import yage.scene.geometry.*

import java.io.File
import java.nio.ByteBuffer
import yage.graphics.gl.resource.texture.Texture2
import yage.graphics.gl.resource.Access
import yage.graphics.gl.resource.Format

// uniforms:

object Shadertoy extends App:

  val sizeI = 4096
  val sizeJ = 4096

  var startTime = 0L
  var texture: Texture2 = null
  var geometry: Geometry = null
  var computeProgram: Program = null
  var drawProgram: Program = null

  override def init() =
    mouse.movedListeners += mouseMoved
    texture = Texture2(Format.FP32_4, sizeI, sizeJ)
    texture.bindToTextureUnit(0)
    texture.bindToImageUnit(0, 0, Access.WriteOnly, Format.FP32_4)
    geometry = Quad(1.0, 1.0)
    val d = srcDir
    computeProgram = Program(File(d, "CompCellNoise.comp"))
    computeProgram.setUniform1i("image", 0)
    drawProgram = Program(File(d, "Draw.vert"), File(d, "Draw.frag"))
    drawProgram.setUniform1i("sampler", 0)
    glClearColor(0, 0, 0, 1)
    startTime = System.nanoTime()

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT);
    drawProgram.use()
    geometry.draw()

  override def step(dt: Int) =
    val time = ((System.nanoTime() - startTime) / 1000000).toFloat
    computeProgram.use()
    computeProgram.setUniform1f(0, time)
    glDispatchCompute(sizeI / 32, sizeJ / 32, 1)
    glMemoryBarrier(GL_SHADER_IMAGE_ACCESS_BARRIER_BIT);
    Thread.sleep(300);
  
  def mouseMoved(x: Float, y: Float, dx: Float, dy: Float) = 
    val mouseU = x / window.innerSizeX
    val mouseV = y / window.innerSizeY
    computeProgram.setUniform1f(1, mouseU)
    computeProgram.setUniform1f(2, mouseV)

