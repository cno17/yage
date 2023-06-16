package yage.graphics.vg.shape

import yage.math.MathUtil
import yage.graphics.vg.Context
import yage.math.Vec2
import yage.graphics.fw.Keyboard
import yage.graphics.fw.Mouse

trait Shape(val ctx: Context) extends MathUtil:

  def draw() = {}
  def fill() = {}

  def paint() = {}

  def addListenersTo(k: Keyboard, m: Mouse) = {}
  def removeListenersFrom(k: Keyboard, m: Mouse) = {}

  // move to math

  def ccw(p1: Vec2, p2: Vec2, p3: Vec2): Boolean = 
    ccw(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y)
  
  def ccw(p1: Vec2, p2: Vec2, x3: Float, y3: Float): Boolean = 
    ccw(p1.x, p1.y, p2.x, p2.y, x3, y3) 
  
  def ccw(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float): Boolean = 
    (x1 * y2 - x2 * y1) - (x1 * y3 - x3 * y1) + (x2 * y3 - x3 * y2) < 0
