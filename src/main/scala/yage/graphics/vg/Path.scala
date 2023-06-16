package yage.graphics.vg

import org.lwjgl.nanovg.NanoVG.*
import yage.math.Vec2

/**
 * subpath? 
 */

class Path(val ctx: Long):

  def begin() = nvgBeginPath(ctx)

  def moveTo(p: Vec2) = nvgMoveTo(ctx, p.x, p.y)
  def moveTo(x: Float, y: Float) = nvgMoveTo(ctx, x, y)
  def lineTo(p: Vec2) = nvgLineTo(ctx, p.x, p.y)
  def lineTo(x: Float, y: Float) = nvgLineTo(ctx, x, y)
  def quadToTo(p1: Vec2, p: Vec2) = nvgQuadTo(ctx, p1.x, p1.y, p.x, p.y)
  def quadToTo(x1: Float, y1: Float, x: Float, y: Float) = nvgQuadTo(ctx, x1, y1, x, y)
  def cubicTo(p1: Vec2, p2: Vec2, p: Vec2) = nvgBezierTo(ctx, p1.x, p1.y, p2.x, p2.y, p.x, p.y)
  def cubicTo(x1: Float, y1: Float, x2: Float, y2: Float, x: Float, y: Float) = nvgBezierTo(ctx, x1, y1, x2, y2, x, y)
  def arcTo(p1: Vec2, p2: Vec2, r: Float) = nvgArcTo(ctx, p1.x, p1.y, p2.x, p2.y, r)
  def arcTo(x1: Float, y1: Float, x2: Float, y2: Float, r: Float) = nvgArcTo(ctx, x1, y1, x2, y2, r)
  def close() = nvgClosePath(ctx)
  
  // subpathes

  def arc = 0
  def circle(c: Vec2, r: Float) = nvgCircle(ctx, c.x, c.y, r)
  def circle(x: Float, y: Float, r: Float) = nvgCircle(ctx, x, y, r)
  def ellipse(c: Vec2, rx: Float, ry: Float) = nvgEllipse(ctx, c.x, c.y, rx, ry)
  def ellipse(x: Float, y: Float, rx: Float, ry: Float) = nvgEllipse(ctx, x, y, rx, ry)
  // min, max form
  def rect(x: Float, y: Float, w: Float, h: Float) = nvgRect(ctx, x, y, w, h)
  def roundedRect(x: Float, y: Float, w: Float, h: Float, r: Float) = nvgRoundedRect(ctx, x, y, w, h, r)
  
  // def roundedRectVarying(x: Float, y: Float, w: Float, h: Float, r: Float) = nvgRoundedRect(ctx, x, y, w, h, r)

