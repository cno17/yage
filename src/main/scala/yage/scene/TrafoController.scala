package yage.scene

import yage.math.Mat4
import yage.graphics.fw.App
import yage.graphics.fw.Key
import yage.graphics.fw.MouseButton

// rename: TrafoManipulator

trait TrafoController(var app: App, var mat: Mat4):

  enum Trafo:
    case S, R, T

  enum Axis:
    case X, Y, Z

  enum Order:
    case L, R

  case class Mode(var trafo: Trafo, var axis: Axis, var order: Order)

  var mode = Mode(Trafo.R, Axis.X, Order.L)

  var sca = 0.0f
  var rot = 0.0f
  var tra = 0.0f

  register()

  def register(): Unit
