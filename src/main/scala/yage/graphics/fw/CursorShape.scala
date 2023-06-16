package yage.graphics.fw

import org.lwjgl.glfw.GLFW.*

enum CursorShape(val value: Int):
  case Arrow extends CursorShape(GLFW_ARROW_CURSOR)
  case IBeam extends CursorShape(GLFW_IBEAM_CURSOR)
  case Crosshair extends CursorShape(GLFW_CROSSHAIR_CURSOR)
  case PointingHand extends CursorShape(GLFW_POINTING_HAND_CURSOR)
  case ResizeEW extends CursorShape(GLFW_RESIZE_EW_CURSOR)
  case ResizeNS extends CursorShape(GLFW_RESIZE_NS_CURSOR)
  case ResizeNWSE extends CursorShape(GLFW_RESIZE_NWSE_CURSOR)
  case ResizeNESW extends CursorShape(GLFW_RESIZE_NESW_CURSOR)
  case ResizeAll extends CursorShape(GLFW_RESIZE_ALL_CURSOR)
  case NotAllowed extends CursorShape(GLFW_NOT_ALLOWED_CURSOR)