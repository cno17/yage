package yage.graphics.fw

import org.lwjgl.glfw.GLFW.*

object Cursor:

  def apply(window: Long, shape: CursorShape) =
    val handle = glfwCreateStandardCursor(shape.value)
    new Cursor(window, handle)

  // todo: custom image  

class Cursor(val window: Long, val handle: Long):

  def show() = glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL)
  def hide() = glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED)

  def destroy() = glfwDestroyCursor(handle)

