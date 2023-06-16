package yage.graphics.fw

import java.nio.IntBuffer
import java.util
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL.createCapabilities
import org.lwjgl.BufferUtils
import scala.collection.mutable.ArrayBuffer

class Window(val handle: Long) extends Rectangle:

  val movedListeners = ArrayBuffer[(Int, Int) => Unit]()
  val resizedListeners = ArrayBuffer[(Int, Int) => Unit]()
  val iconifiedListeners = ArrayBuffer[() => Unit]()
  val restoredListeners = ArrayBuffer[() => Unit]()
  val closeRequestedListeners = ArrayBuffer[() => Unit]()
  val focusGainedListeners = ArrayBuffer[() => Unit]()
  val focusLostListeners = ArrayBuffer[() => Unit]()
  val mouseEnteredListeners = ArrayBuffer[() => Unit]()
  val mouseLeftListeners = ArrayBuffer[() => Unit]()

  def setTitle(s: String) = glfwSetWindowTitle(handle, s)
  def setIcon() = glfwSetWindowIcon(handle, null) // needs work
  
  def show() = glfwShowWindow(handle)
  def hide() = glfwHideWindow(handle)
  def iconify() = glfwIconifyWindow(handle)
  def restore() = glfwRestoreWindow(handle)
  def focus() = glfwFocusWindow(handle)
  // def close() = glfwSetWindowShouldClose(handle, true) // moved to App

  def swapBuffers() = glfwSwapBuffers(handle)

  def shouldClose() = glfwWindowShouldClose(handle)

  def destroy() = glfwDestroyWindow(handle)

  setCallbacks()

  private def movedCallback(window: Long, x: Int, y: Int) =
    movedListeners.foreach(_(x, y))

  private def resizedCallback(window: Long, w: Int, h: Int) =
    resizedListeners.foreach(_(w, h))

  private def iconifiedCallback(window: Long, iconified: Boolean) =
    if iconified then iconifiedListeners.foreach(_())
    else restoredListeners.foreach(_())

  private def closeCallback(window: Long) =
    closeRequestedListeners.foreach(_())

  private def focusCallback(window: Long, gained: Boolean) =
    if gained then focusGainedListeners.foreach(_())
    else focusLostListeners.foreach(_())

  private def mouseEnteredCallback(window: Long, entered: Boolean) =
    if entered then mouseEnteredListeners.foreach(_())
    else mouseLeftListeners.foreach(_())

  private def setCallbacks() =
    glfwSetWindowFocusCallback(handle, focusCallback)
    glfwSetCursorEnterCallback(handle, mouseEnteredCallback)
    glfwSetWindowPosCallback(handle, movedCallback)
    glfwSetWindowSizeCallback(handle, resizedCallback)
    glfwSetWindowIconifyCallback(handle, iconifiedCallback)
    glfwSetWindowCloseCallback(handle, closeCallback)


  // useful?
  
  // def setOpacity(v: Double) =
  // def getAttribute(k: AttributeKey) = glfwGetWindowAttrib
  // def requestAttention()
  // text input: glfwCharCallback
  // def isFullscreen() = 0
  // def toggleFullScreen() = 0

