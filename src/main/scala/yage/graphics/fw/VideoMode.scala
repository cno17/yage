package yage.graphics.fw

import org.lwjgl.glfw.GLFWVidMode

class VideoMode(m: GLFWVidMode):

  val sizeX = m.width()
  val sizeY = m.height()
  val redBits = m.redBits()
  val greenBits = m.greenBits()
  val blueBits = m.blueBits()
  
  override def toString() =
    s"(sx = $sizeX, sy = $sizeY, rb = $redBits, gb = $greenBits, bb = $blueBits)"

  