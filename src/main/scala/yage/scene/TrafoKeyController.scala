package yage.scene

import yage.math.Mat4
import yage.graphics.fw.App
import yage.graphics.fw.Key
import yage.graphics.fw.MouseButton


class TrafoKeyController(app: App, mat: Mat4) extends TrafoController(app, mat):

  override def register() =
    app.keyboard.keyPressedListeners += keyPressed
    app.keyboard.keyRepeatedListeners += keyRepeated

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
    if k == Key.U then mat.toOne()
    if k == Key.Left then
      sca = +0.90f
      rot = -0.05f
      tra = -1.00f
      exec()
    if k == Key.Right then
      sca = +1.10f
      rot = +0.05f
      tra = +1.00f
      exec()

  def keyRepeated(k: Key) =
    if k == Key.Left then
      sca = +0.90f
      rot = -0.05f
      tra = -1.00f
      exec()
    if k == Key.Right then
      sca = +1.10f
      rot = +0.05f
      tra = +1.00f
      exec()

  def exec() =
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
    