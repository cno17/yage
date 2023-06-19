package test.graphics.gl

// import org.lwjgl.glfw.*
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.*
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryUtil.*

object HelloWorld:

  val width = 800
  val height = 600
  var window = 0L

  def start =
    init
    loop
    glfwDestroyWindow(window)
    glfwTerminate

  def init =
    glfwInit
    glfwDefaultWindowHints
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)
    window = glfwCreateWindow(width, height, "Hello World!", NULL, NULL)
    val videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor)
    glfwSetWindowPos(window, (videoMode.width - width) / 2, (videoMode.height - height) / 2)
    glfwMakeContextCurrent(window)
    glfwSwapInterval(1)
    glfwShowWindow(window)
    GL.createCapabilities
    glClearColor(0.0f, 1.0f, 0.0f, 0.0f)
    println(glGetString(GL_VERSION))

  def loop =
    while !glfwWindowShouldClose(window) do
      glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
      glfwSwapBuffers(window)
      glfwPollEvents

  def main(args: Array[String]) = start
