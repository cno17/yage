package test.graphics.fw

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL.createCapabilities
import yage.graphics.fw.Keyboard
import yage.graphics.fw.Mouse
import yage.graphics.fw.Monitor


object TimerTest:

  def main(args: Array[String]) =
    glfwInit()
    val f = glfwGetTimerFrequency()
    println(f)
    glfwTerminate()
