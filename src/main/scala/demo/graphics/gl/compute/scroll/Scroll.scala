package demo.graphics.gl.compute.scroll

import java.io.File
import java.nio.ByteBuffer
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL13C.*
import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL42C.*
import org.lwjgl.opengl.GL43C.*
import org.lwjgl.BufferUtils
import yage.graphics.fw.App
import yage.graphics.gl.resource.texture.sampler.MagFilter
import yage.graphics.gl.resource.texture.sampler.MinFilter
import yage.graphics.gl.resource.texture.Texture2
import yage.graphics.gl.resource.Access
import yage.graphics.gl.resource.Format
import yage.graphics.gl.shader.program.Program
import yage.graphics.ByteBufferUtil
import yage.math.Vec4
import yage.scene.geometry.*

object Scroll extends App:

  var step = 0

  var geometry: Geometry = null

  var texture0: Texture2 = null
  var texture1: Texture2 = null

  var sizeI = 0
  var sizeJ = 0
  var numGroupsI = 0
  var numGroupsJ = 0

  var drawProgram: Program = null
  var stepProgram: Program = null

  override def init() =
    
    val d = srcDir
    
    geometry = Quad(0.8f, 0.8f)
        
    texture0 = Texture2(File("src/main/resources/icon/Frame-16.png"))
    texture0.setMinFilter(MinFilter.Nearest)
    texture0.setMagFilter(MagFilter.Nearest)
    texture0.bindToTextureUnit(0)
    texture0.bindToImageUnit(0, Access.ReadWrite, Format.FP32_4)

    texture1 = Texture2(File("src/main/resources/icon/Frame-16.png"))
    texture1.setMinFilter(MinFilter.Nearest)
    texture1.setMagFilter(MagFilter.Nearest)
    texture1.bindToTextureUnit(1)
    texture1.bindToImageUnit(1, Access.ReadWrite, Format.FP32_4)

    sizeI = texture0.sizeI
    sizeJ = texture0.sizeJ
    numGroupsI = Math.ceil(sizeI.toFloat / 32).toInt
    numGroupsJ = Math.ceil(sizeJ.toFloat / 32).toInt

    drawProgram = Program(File(d, "Draw.vert"), File(d, "Draw.frag"))
    stepProgram = Program(File(d, "Step.comp"))
    stepProgram.setUniform1i("si", sizeI)
    stepProgram.setUniform1i("sj", sizeJ)
    
    glClearColor(0, 0, 0, 1)

  override def draw() =
    drawProgram.use()
    drawProgram.setUniform1i("sampler", step % 2)
    geometry.draw()
    stepProgram.use()
    stepProgram.setUniform1i("imageR", (step + 0) % 2)
    stepProgram.setUniform1i("imageW", (step + 1) % 2)
    glDispatchCompute(numGroupsI, numGroupsJ, 1)
    glMemoryBarrier(GL_SHADER_IMAGE_ACCESS_BARRIER_BIT)
    step += 1
    Thread.sleep(100)
