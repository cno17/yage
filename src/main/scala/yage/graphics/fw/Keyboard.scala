package yage.graphics.fw

import org.lwjgl.glfw.GLFW.glfwGetKey
import org.lwjgl.glfw.GLFW.glfwSetKeyCallback
import org.lwjgl.glfw.GLFW.GLFW_PRESS
import org.lwjgl.glfw.GLFW.GLFW_RELEASE
import org.lwjgl.glfw.GLFW.GLFW_REPEAT
import scala.collection.mutable.ArrayBuffer

class Keyboard(val window: Long):

  val keyPressedListeners = ArrayBuffer[(Key) => Unit]()
  val keyRepeatedListeners = ArrayBuffer[(Key) => Unit]()
  val keyReleasedListeners = ArrayBuffer[(Key) => Unit]()
  
  // TODO charListeners

  def isPressed(key: Key) = glfwGetKey(window, key.id) == GLFW_PRESS
  
  def isAltKeyPressed = isPressed(Key.LeftAlt) || isPressed(Key.RightAlt)
  def isControlKeyPressed = isPressed(Key.LeftControl) || isPressed(Key.RightControl)
  def isShiftKeyPressed = isPressed(Key.LeftShift) || isPressed(Key.RightShift)

  setCallbacks()

  private def keyCallback(window: Long, key: Int, code: Int, action: Int, mods: Int) =
    val k = Key(key)
    if action == GLFW_PRESS then keyPressedListeners.foreach(_(k))
    else if action == GLFW_REPEAT then keyRepeatedListeners.foreach(_(k))
    else if action == GLFW_RELEASE then keyReleasedListeners.foreach(_(k))

  private def setCallbacks() =
    glfwSetKeyCallback(window, keyCallback)
