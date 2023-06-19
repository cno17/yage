package test.graphics.fw

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL.createCapabilities
import yage.graphics.fw.Keyboard
import yage.graphics.fw.Mouse


object MouseTest:

  def main(args: Array[String]) =
    glfwInit()
    val window = glfwCreateWindow(400, 300, "Test", 0, 0)
    val mouse = Mouse(window)
    mouse.movedListeners += mouseMoved
    val keyboard = Keyboard(window)
    glfwMakeContextCurrent(window)
    createCapabilities()
    glfwSwapInterval(1)
    while !glfwWindowShouldClose(window) do
      glfwSwapBuffers(window)
      glfwPollEvents()
    glfwTerminate()

  def mouseMoved(x: Double, y: Double, dx: Double, dy: Double) =
    println(s"x = $x, y = $y, dx = $dx, dy = $dy")