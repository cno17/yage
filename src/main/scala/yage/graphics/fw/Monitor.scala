package yage.graphics.fw

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWVidMode

object Monitor:

  def primaryMonitor = Monitor(glfwGetPrimaryMonitor())
  
  def connectedMonitors =
    val buf = glfwGetMonitors()
    val res = for i <- 0 to buf.capacity() - 1 yield Monitor(buf.get(i))
    res.toArray
    
// todo: needs work

class Monitor(val handle: Long):

  def currentVideoMode = VideoMode(glfwGetVideoMode(handle))
  
  def supportedVideoModes = 
    val buf = glfwGetVideoModes(handle)
    val res = for i <- 0 to buf.capacity() - 1 yield VideoMode(buf.get(i))
    res.toArray

  def center(w: Window) =
    val vm = currentVideoMode
    val px = (vm.sizeX - w.outerSizeX) / 2
    val py = (vm.sizeY - w.outerSizeY) / 2
    w.setOuterPos(px, py)
