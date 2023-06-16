package yage.scene

import yage.math.Mat4
import yage.graphics.fw.App
import yage.graphics.fw.Key
import yage.graphics.fw.MouseButton


class TrafoKeyMouseController(app: App, mat: Mat4) extends TrafoController(app, mat):

  var oldX = 0.0
  var oldY = 0.0

  override def register() =
    app.keyboard.keyPressedListeners += keyPressed
    app.mouse.draggedListeners += mouseDragged

  def keyPressed(k: Key) =
    if k == Key.S then mode.trafo = Trafo.S
    if k == Key.R then mode.trafo = Trafo.R
    if k == Key.T then mode.trafo = Trafo.T
    if k == Key.X then mode.axis = Axis.X
    if k == Key.Y then mode.axis = Axis.Y
    if k == Key.Z then mode.axis = Axis.Z
    if k == Key.F1 then mode.order = Order.L
    if k == Key.F2 then mode.order = Order.R
    app.window.setTitle(s"$mode")

  def mouseDragged(x: Float, y: Float, dx: Float, dy: Float) =
    // val dx = x - oldX
    // val dy = y - oldY
    // oldX = x
    // oldY = y
    val sca = if dx > 0 then 1.1f else if dx < 0 then 0.9f else 1.0f
    val rot = dx * 0.1f
    val tra = dx * 1.0f
    mode match
      case Mode(Trafo.S, _, Order.L) => mat.scaleL(sca)
      case Mode(Trafo.S, _, Order.R) => mat.scaleR(sca)
      case Mode(Trafo.R, Axis.X, Order.L) => mat.rotateL(1, 0, 0, rot)
      case Mode(Trafo.R, Axis.Y, Order.L) => mat.rotateL(0, 1, 0, rot)
      case Mode(Trafo.R, Axis.Z, Order.L) => mat.rotateL(0, 0, 1, rot)
      case Mode(Trafo.R, Axis.X, Order.R) => mat.rotateR(1, 0, 0, rot)
      case Mode(Trafo.R, Axis.Y, Order.R) => mat.rotateR(0, 1, 0, rot)
      case Mode(Trafo.R, Axis.Z, Order.R) => mat.rotateR(0, 0, 1, rot)
      case Mode(Trafo.T, Axis.X, Order.L) => mat.translateL(tra, 0, 0)
      case Mode(Trafo.T, Axis.Y, Order.L) => mat.translateL(0, tra, 0)
      case Mode(Trafo.T, Axis.Z, Order.L) => mat.translateL(0, 0, tra)
      case Mode(Trafo.T, Axis.X, Order.R) => mat.translateR(tra, 0, 0)
      case Mode(Trafo.T, Axis.Y, Order.R) => mat.translateR(0, tra, 0)
      case Mode(Trafo.T, Axis.Z, Order.R) => mat.translateR(0, 0, tra)
    