package yage.graphics.vg

import org.lwjgl.opengl.GL11C.glClear
import org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT

import org.lwjgl.nanovg.NanoVG.*

import org.lwjgl.nanovg.NanoVGGL3
import java.io.File

class Context(val id: Long):

  val state = State(id)
  val path = Path(id)
  val text = Text(id)

  def clear() = glClear(GL_COLOR_BUFFER_BIT)

  def beginFrame(sx: Int, sy: Int) = nvgBeginFrame(id, sx.toFloat, sy.toFloat, 1.0f) 
  def beginFrame(sx: Float, sy: Float) = nvgBeginFrame(id, sx, sy, 1.0f) 
  def cancelFrame() = nvgCancelFrame(id)
  def endFrame() = nvgEndFrame(id)

  def stroke() = nvgStroke(id) // draw
  def fill() = nvgFill(id)

//

object Context:
  
  class CreateFlags:
    var antialias =  false
    var stencilStrokes = false
    var debug = false

  def create = NanoVGGL3.nvgCreate(0)


