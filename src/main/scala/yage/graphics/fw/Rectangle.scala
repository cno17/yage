package yage.graphics.fw

import java.nio.IntBuffer
import org.lwjgl.glfw.GLFW.glfwGetFramebufferSize
import org.lwjgl.glfw.GLFW.glfwGetWindowFrameSize
import org.lwjgl.glfw.GLFW.glfwGetWindowPos
import org.lwjgl.glfw.GLFW.glfwGetWindowSize
import org.lwjgl.glfw.GLFW.glfwSetWindowAspectRatio
import org.lwjgl.glfw.GLFW.glfwSetWindowPos
import org.lwjgl.glfw.GLFW.glfwSetWindowSize
import org.lwjgl.glfw.GLFW.glfwSetWindowSizeLimits
import org.lwjgl.glfw.GLFW.GLFW_DONT_CARE
import yage.graphics.Caller

/**
 * A window rectangle is conceptually composed of an inner canvas which represents 
 * the content area and an outer frame which represents the border decorations.
 */

trait Rectangle extends Caller:

  this: Window =>

  def frameSizeL = call4i(glfwGetWindowFrameSize, handle, 1)
  def frameSizeR = call4i(glfwGetWindowFrameSize, handle, 3)
  def frameSizeT = call4i(glfwGetWindowFrameSize, handle, 2)
  def frameSizeB = call4i(glfwGetWindowFrameSize, handle, 4)

  def innerPosX = call2i(glfwGetWindowPos, handle, 1)
  def innerPosY = call2i(glfwGetWindowPos, handle, 2)
  def innerSizeX = call2i(glfwGetWindowSize, handle, 1)
  def innerSizeY = call2i(glfwGetWindowSize, handle, 2)

  def outerPosX = innerPosX - frameSizeL
  def outerPosY = innerPosY - frameSizeT
  def outerSizeX = innerSizeX + frameSizeL + frameSizeR
  def outerSizeY = innerSizeY + frameSizeT + frameSizeB

  def setInnerPos(px: Int, py: Int) = glfwSetWindowPos(handle, px, py)
  def setInnerSize(sx: Int, sy: Int) = glfwSetWindowSize(handle, sx, sy)
  def setInnerMinSize(sx: Int, sy: Int) = glfwSetWindowSizeLimits(handle, sx, sy, GLFW_DONT_CARE, GLFW_DONT_CARE)
  def setInnerMaxSize(sx: Int, sy: Int) = glfwSetWindowSizeLimits(handle, GLFW_DONT_CARE, GLFW_DONT_CARE, sx, sy)
  def setInnerAspectRatio(sx: Int, sy: Int) = glfwSetWindowAspectRatio(handle, sx, sy)

  def setOuterPos(px: Int, py: Int) = setInnerPos(px + frameSizeL, py + frameSizeT)
  def setOuterSize(sx: Int, sy: Int) = setInnerSize(sx - frameSizeL - frameSizeR, sy - frameSizeT - frameSizeB)
  def setOuterMinSize(sx: Int, sy: Int) = setInnerMinSize(sx - frameSizeL - frameSizeR, sy - frameSizeT - frameSizeB)
  def setOuterMaxSize(sx: Int, sy: Int) = setInnerMaxSize(sx - frameSizeL - frameSizeR, sy - frameSizeT - frameSizeB)
  def setOuterAspectRatio(sx: Int, sy: Int) = setInnerAspectRatio(sx - frameSizeL - frameSizeR, sy - frameSizeT - frameSizeB)

  // def width = call2(glfwGetFramebufferSize, 1)
  // def height = call2(glfwGetFramebufferSize, 2)
