package test.graphics.fw

import org.lwjgl.glfw.GLFW.*
import yage.graphics.fw.Monitor

object MonitorTest:

  def main(args: Array[String]) =
    glfwInit()
    for vm <- Monitor.primaryMonitor.supportedVideoModes do 
      println(vm)
    glfwTerminate()
