package demo.graphics.fw


import org.lwjgl.opengl.GL11C.*
import yage.graphics.fw.*
import yage.graphics.fw.App
import yage.graphics.fw.Key
import yage.graphics.fw.MouseButton

// todo: method order!

object EventDemo extends App:

  override def init() =
    window.movedListeners += windowMoved
    window.resizedListeners += windowResized
    window.iconifiedListeners += windowIconified
    window.restoredListeners += windowRestored
    window.closeRequestedListeners += windowCloseRequested
    window.focusGainedListeners += focusGained
    window.focusLostListeners += focusLost
    window.mouseEnteredListeners += mouseEntered
    window.mouseLeftListeners += mouseLeft
    keyboard.keyPressedListeners += keyPressed
    keyboard.keyReleasedListeners += keyReleased
    keyboard.keyRepeatedListeners += keyRepeated
    mouse.movedListeners += mouseMoved
    mouse.draggedListeners += mouseDragged
    mouse.buttonPressedListeners += mouseButtonPressed
    mouse.buttonReleasedListeners += mouseButtonReleased
    mouse.wheelRotatedListeners += mouseWheelRotated
    glClearColor(0.0f, 1.0f, 0.0f, 1.0f)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

  def focusGained() = 
    println("focus gained")
  
  def focusLost() = 
    println("focus lost")

  def keyPressed(key: Key) = 
    println(s"key pressed: $key")

  def keyRepeated(key: Key) =
    println(s"key repeated: $key")

  def keyReleased(key: Key) = 
    println(s"key released: $key")

  def mouseEntered() = 
    println("mouse entered")

  def mouseLeft() = 
    println("mouse left")
  
  def mouseMoved(x: Float, y: Float, dx: Float, dy: Float) = 
    println(s"mouse moved: x = $x, y = $y, dx = $dx, dy = $dy")

  def mouseDragged(x: Float, y: Float, dx: Float, dy: Float) =
    println(s"mouse dragged: x = $x, y = $y, dx = $dx, dy = $dy")

  def mouseButtonPressed(button: MouseButton) = 
    println(s"mouse button pressed: $button")

  def mouseButtonReleased(button: MouseButton) =
    println("mouse button released")

  def mouseWheelRotated(wr: Double) =
    println(s"mouse wheel rotated: wr = $wr")

  def windowMoved(x: Int, y: Int) =
    println(s"window moved: x = $x, y = $y")

  def windowResized(w: Int, h: Int) =
    println(s"window resized: w = $w, h = $h")

  def windowIconified() =
    println("window iconified")

  def windowRestored() =
    println("window restored")

  def windowCloseRequested() =
    println("window close requested")