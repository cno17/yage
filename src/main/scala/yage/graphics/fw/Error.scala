package yage.graphics.fw

import org.lwjgl.glfw.GLFW.*

object Error:
  def apply(v: Int) = values.find(_.value == v).get

enum Error(val value: Int):
  case NotInitialized extends Error(GLFW_NOT_INITIALIZED)
  case NoCurrentContext extends Error(GLFW_NO_ERROR)
  case NoWindowContext extends Error(GLFW_NOT_INITIALIZED)
  case OutOfMemory extends Error(GLFW_OUT_OF_MEMORY)
  case InvalidEnum extends Error(GLFW_INVALID_ENUM)
  case InvalidValue extends Error(GLFW_INVALID_VALUE)
  case ApiUnavailable extends Error(GLFW_API_UNAVAILABLE)
  case VersionUnavailable extends Error(GLFW_VERSION_UNAVAILABLE)
  case FormatUnavailable extends Error(GLFW_FORMAT_UNAVAILABLE)
  case PlatformError extends Error(GLFW_PLATFORM_ERROR)
  case NoError extends Error(GLFW_NO_ERROR)

