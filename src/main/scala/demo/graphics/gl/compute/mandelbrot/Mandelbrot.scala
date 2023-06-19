package demo.graphics.gl.compute.mandelbrot

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL13C.*
import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL42C.*
import org.lwjgl.opengl.GL43C.*
import yage.math.Vec4
import yage.graphics.fw.App
import yage.graphics.fw.EventMode
import yage.graphics.gl.shader.Shader
import yage.graphics.gl.shader.ShaderStage
import yage.graphics.gl.shader.program.Program
import yage.scene.geometry.*

import java.io.File
import java.nio.ByteBuffer
import yage.graphics.gl.resource.texture.Texture2
import yage.graphics.gl.resource.Access
import yage.graphics.gl.resource.Format
import yage.graphics.fw.AppCreateInfo
import yage.graphics.fw.MouseButton

// todo vec2d

object Mandelbrot extends App:

  val si = 1024
  val sj = 1024

  var minX = -2.0f
  var minY = -2.0f
  var maxX = +2.0f
  var maxY = +2.0f

  var texture: Texture2 = null
  var geometry: Geometry = null
  var computeProgram: Program = null
  var drawProgram: Program = null

  override def info() =
    val info = AppCreateInfo()
    info.eventMode = EventMode.Wait
    info 

  override def init() =
    mouse.draggedListeners += mouseDragged
    mouse.wheelRotatedListeners += mouseWheelRotated
    texture = Texture2(Format.FP32_4, si, sj)
    texture.bindToTextureUnit(0)
    texture.bindToImageUnit(0, 0, Access.WriteOnly, Format.FP32_4)
    geometry = Quad(1.0, 1.0)
    val d = srcDir
    computeProgram = Program(File(d, "Compute.comp"))
    computeProgram.setUniform1i("image", 0)
    computeProgram.setUniform1i("si", si)
    computeProgram.setUniform1i("sj", sj)
    computeProgram.setUniform1i("maxI", 100)
    drawProgram = Program(File(d, "Draw.vert"), File(d, "Draw.frag"))
    drawProgram.setUniform1i("sampler", 0)
    glClearColor(0, 0, 0, 1)

  override def draw() = 
    val t1 = timer.microTime
    computeProgram.use()
    computeProgram.setUniform2f("minZ", minX, minY)
    computeProgram.setUniform2f("maxZ", maxX, maxY)
    glDispatchCompute(si / 32, sj / 32, 1)
    glMemoryBarrier(GL_SHADER_IMAGE_ACCESS_BARRIER_BIT)
    val t2 = timer.microTime
    val dt = t2 - t1
    println(s"$dt[micros]")
    glClear(GL_COLOR_BUFFER_BIT)
    drawProgram.use()
    geometry.draw()

  def mouseDragged(x: Double, y: Double, dx: Double, dy: Double) =
    val dx2 = (maxX - minX) * dx / 500
    val dy2 = (maxY - minY) * dy / 500
    minX -= dx2.toFloat
    minY += dy2.toFloat
    maxX -= dx2.toFloat
    maxY += dy2.toFloat

  def mouseWheelRotated(wr: Double) =
    val dx = wr * (maxX - minX) / 10
    val dy = wr * (maxY - minY) / 10
    minX -= dx.toFloat
    minY -= dy.toFloat
    maxX += dx.toFloat
    maxY += dy.toFloat
