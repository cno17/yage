package yage.graphics.fw

import org.lwjgl.glfw.GLFW.*
import scala.collection.mutable.ArrayBuffer

import yage.graphics.Caller

object Mouse:
  
  def rawMotionSupported = glfwRawMouseMotionSupported()

/**
 * int, float coords? 
 * listeners without dx, dy?
 */

class Mouse(val window: Long) extends Caller:

  // bloop does not like this

  // type MouseListener2 = (Double, Double) => Unit
  // type MouseListener4 = (Double, Double, Double, Double) => Unit
  // type MouseListener = MouseListener2 | MouseListener4

  var cursor = Cursor(window, CursorShape.Arrow)
  val movedListeners = ArrayBuffer[(Float, Float, Float, Float) => Unit]()
  val draggedListeners = ArrayBuffer[(Float, Float, Float, Float) => Unit]()
  val buttonPressedListeners = ArrayBuffer[(MouseButton) => Unit]()
  val buttonReleasedListeners = ArrayBuffer[(MouseButton) => Unit]()
  val wheelRotatedListeners = ArrayBuffer[(Float) => Unit]()
  
  def posX = call2d(glfwGetCursorPos, window, 1)
  def posY = call2d(glfwGetCursorPos, window, 2)

  def setPos(x: Float, y: Float) = glfwSetCursorPos(window, x, y)

  def isPressed(button: MouseButton) = glfwGetMouseButton(window, button.id) == GLFW_PRESS

  def enableRawMotion = glfwSetInputMode(window, GLFW_RAW_MOUSE_MOTION, GLFW_TRUE)
  
  setCallbacks()

  private var oldX = 0.0
  private var oldY = 0.0

  private def movedCallback(window: Long, x: Double, y: Double) =
    val lbp = isPressed(MouseButton.Left)
    val mbp = isPressed(MouseButton.Middle)
    val rbp = isPressed(MouseButton.Right)
    val dx = x - oldX
    val dy = y - oldY
    oldX = x
    oldY = y
    if lbp | mbp | rbp then 
      draggedListeners.foreach(_(x.toFloat, y.toFloat, dx.toFloat, dy.toFloat))
    else 
      movedListeners.foreach(_(x.toFloat, y.toFloat, dx.toFloat, dy.toFloat))

  private def buttonCallback(window: Long, button: Int, action: Int, mods: Int) =
    val b = MouseButton(button)
    if action == GLFW_PRESS then buttonPressedListeners.foreach(_(b))
    else buttonReleasedListeners.foreach(_(b))

  private def wheelCallback(window: Long, xx: Double, wr: Double) =
    wheelRotatedListeners.foreach(_(wr.toFloat))

  private def setCallbacks() =
    glfwSetCursorPosCallback(window, movedCallback)
    glfwSetMouseButtonCallback(window, buttonCallback)
    glfwSetScrollCallback(window, wheelCallback)
