package yage.graphics.fw

import org.lwjgl.glfw.GLFW.*
import yage.graphics.fw.MouseButton

// idFw, idNk

enum MouseButton(val id: Int):

  case Left extends MouseButton(GLFW_MOUSE_BUTTON_LEFT)
  case Middle extends MouseButton(GLFW_MOUSE_BUTTON_MIDDLE)
  case Right extends MouseButton(GLFW_MOUSE_BUTTON_RIGHT)

object MouseButton:

  def apply(i: Int) = MouseButton.values.find(_.id == i).get
