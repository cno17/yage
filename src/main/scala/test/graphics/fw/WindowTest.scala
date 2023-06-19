package test.graphics.fw

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL.createCapabilities
import yage.graphics.fw.Keyboard
import yage.graphics.fw.Mouse
import yage.graphics.fw.Window
import yage.graphics.fw.Monitor


object WindowTest:

  def main(args: Array[String]) =
    glfwInit()
    val handle = glfwCreateWindow(800, 600, "Test", 0, 0)
    val window = new Window(handle)
    window.hide() // use hints!
    Monitor.primaryMonitor.center(window)
    window.show()
    glfwMakeContextCurrent(window.handle)
    createCapabilities()
    glfwSwapInterval(1)
    while !window.shouldClose() do
      window.swapBuffers()
      glfwPollEvents()
    glfwTerminate()

  def mouseMoved(x: Double, y: Double, dx: Double, dy: Double) =
    println(s"x = $x, y = $y, dx = $dx, dy = $dy")