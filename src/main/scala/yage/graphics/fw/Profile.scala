package yage.graphics.fw

import org.lwjgl.glfw.GLFW.GLFW_OPENGL_COMPAT_PROFILE
import org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE

object Profile:
  def apply(i: Int) = Profile.values.find(_.id == i).get

enum Profile(val id: Int):
  case Core extends Profile(GLFW_OPENGL_CORE_PROFILE)
  case Compatibility extends Profile(GLFW_OPENGL_COMPAT_PROFILE)
