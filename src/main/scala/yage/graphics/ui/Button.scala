package yage.graphics.ui

import scala.collection.mutable.ArrayBuffer
import yage.graphics.fw.Key
import yage.graphics.fw.Keyboard
import yage.graphics.fw.Mouse
import yage.graphics.fw.MouseButton
import yage.graphics.vg.Color
import yage.graphics.vg.Context
import yage.math.Vec2
import Button.*

object Button:

  val upColor = Color(0.8f, 0.8f, 0.8f)
  val focusedColor = Color(1.0f, 1.0f, 1.0f)
  val downColor = Color(0.6f, 0.6f, 0.6f)

  enum State:
    case Up, Down

class Button(ctx: Context, var min: Vec2, var max: Vec2) extends Widget(ctx):

  var state = State.Up
  var focused = false
  var clickedListeners = ArrayBuffer[(Button) => Unit]()

  def setBounds(minX: Float, minY: Float, maxX: Float, maxY: Float) =
    min.set(minX, minY)
    max.set(maxX, maxY)

  // TODO override def box(): Abox2

  override def contains(x: Float, y: Float) =
    min.x < x && min.y < y && x < max.x && y < max.y

  override def paint() =
    ctx.path.begin()
    ctx.path.rect(min.x, min.y, max.x - min.x, max.y - min.y)
    if focused then ctx.state.setFillColor(focusedColor)
    else ctx.state.setFillColor(upColor)
    if state == State.Down then ctx.state.setFillColor(downColor)
    ctx.fill()

  override def addListenersTo(k: Keyboard, m: Mouse) =
    m.movedListeners += mouseMoved
    m.buttonPressedListeners += buttonPressed
    m.buttonReleasedListeners += buttonReleased

  override def removeListenersFrom(k: Keyboard, m: Mouse) =
    m.movedListeners -= mouseMoved
    m.buttonPressedListeners -= buttonPressed
    m.buttonReleasedListeners -= buttonReleased

  private def mouseMoved(x: Float, y: Float, dx: Float, dy: Float) =
    focused = contains(x, y)

  private def buttonPressed(b: MouseButton) =
    if focused == true then
      state = State.Down

  private def buttonReleased(b: MouseButton) =
    if state == State.Down then
      state = State.Up
      clickedListeners.foreach(_(this))
