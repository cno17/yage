package yage.graphics.fw

import org.lwjgl.glfw.GLFW.*

// TODO Profile

class WindowHints:
  
  var resizable = true
  var visible = true
  var decorated = true
  var floating = true
  var transparentFrameBuffer = false
  var focusOnShow = true
  var autoIconify = false
  var debugContext = true  

  def apply() =
    // window
    glfwWindowHint(GLFW_RESIZABLE, toInt(resizable))
    glfwWindowHint(GLFW_VISIBLE, toInt(visible))
    glfwWindowHint(GLFW_DECORATED, toInt(decorated))
    glfwWindowHint(GLFW_FOCUSED, GLFW_TRUE)
    glfwWindowHint(GLFW_FLOATING, GLFW_FALSE)
    glfwWindowHint(GLFW_CENTER_CURSOR, GLFW_TRUE)
    glfwWindowHint(GLFW_TRANSPARENT_FRAMEBUFFER, toInt(transparentFrameBuffer))
    glfwWindowHint(GLFW_FOCUS_ON_SHOW, toInt(focusOnShow))
    glfwWindowHint(GLFW_AUTO_ICONIFY, toInt(autoIconify))
    // context
    glfwWindowHint(GLFW_CLIENT_API, GLFW_OPENGL_API)
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4)
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 6)
    glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, toInt(debugContext)) 
    // glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_COMPAT_PROFILE) // for lwjgl demos
    glfwWindowHint(GLFW_CONTEXT_NO_ERROR, GLFW_FALSE)
    // frame buffer
    glfwWindowHint(GLFW_RED_BITS, 8)
    glfwWindowHint(GLFW_GREEN_BITS, 8)
    glfwWindowHint(GLFW_BLUE_BITS, 8)
    glfwWindowHint(GLFW_ALPHA_BITS, 8)
    glfwWindowHint(GLFW_DEPTH_BITS, 24)
    glfwWindowHint(GLFW_STENCIL_BITS, 8)

  private def toInt(b: Boolean) = if b == false then GLFW_FALSE else GLFW_TRUE


/*
  // frame buffer hints
  var redBits = 8
  var greenBits = 8
  var blueBits = 8
  var alphaBits = 8
  var depthBits = 24
  var stencilBits = 8


*/