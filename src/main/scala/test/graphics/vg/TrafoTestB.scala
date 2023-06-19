package test.graphics.vg
import java.io.File
import java.nio.ByteBuffer
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.BufferUtils
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVGGL3
import org.lwjgl.glfw.GLFW.*

import yage.graphics.fw.App
import org.lwjgl.nanovg.NVGColor
import yage.graphics.fw.MouseButton
import yage.graphics.fw.EventMode
import yage.graphics.vg.Trafo
import java.nio.FloatBuffer
import yage.graphics.fw.AppCreateInfo
import yage.graphics.fw.Key
import yage.graphics.vg.Color

object TrafoTestB extends App:

  val color = Color(0.2f, 0.8f, 0.2f)
  val trafo = Trafo()


  override def info() =
    val res = AppCreateInfo()
    res.sizeX = 1200
    res.sizeY = 800
    res.eventMode = EventMode.Wait
    res

  override def init() =
    // glfwSetInputMode(window.handle, GLFW_STICKY_KEYS, GLFW_TRUE)
    keyboard.keyPressedListeners += onKeyPressed
    mouse.draggedListeners += onMouseDragged
    mouse.wheelRotatedListeners += onWheelRotated
    ctx.state.setClearColor(Color(0, 0, 0))

  override def draw() =
    val sx = window.innerSizeX
    val sy = window.innerSizeY
    ctx.clear()
    ctx.beginFrame(sx, sy)
    ctx.state.setFillColor(color)
    ctx.state.setTrafo(trafo)
    ctx.path.begin()
    ctx.path.rect(-100, -50, 200, 100)
    ctx.fill()
    ctx.endFrame()

  def onKeyPressed(k: Key) = {} // println("a")

  def onMouseDragged(x: Float, y: Float, dx: Float, dy: Float) =
    if mouse.isPressed(MouseButton.Left) then trafo.traL(dx, dy)
    if mouse.isPressed(MouseButton.Right) then trafo.traR(dx, dy)

  def onWheelRotated(wr: Float) =
    if keyboard.isShiftKeyPressed then trafo.rotR(wr * 0.1f)
    else trafo.rotL(wr * 0.1f)
