package yage.graphics.ui

import yage.graphics.vg.Context
import yage.graphics.vg.shape.Shape

// extends Rect?

abstract class Widget(ctx: Context) extends Shape(ctx):

  def contains(x: Float, y: Float) = false
  
  // def box: ABox2
  
  def update() = {}
