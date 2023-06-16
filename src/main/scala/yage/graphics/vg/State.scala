package yage.graphics.vg

import org.lwjgl.opengl.GL11C.glClearColor

import org.lwjgl.nanovg.NanoVG.*
import yage.graphics.vg.Paint

/**
 * The nanovg state attributes define how paths will be rendered.
 * State = (trafo, fill and stroke styles, text and font styles, scissor clipping. 
 */

class State(val ctx: Long):
 
  def push() = nvgSave(ctx)
  def pop() = nvgRestore(ctx)
  def reset() = nvgReset(ctx)

  def setTrafo(t: Trafo) = 
    nvgResetTransform(ctx)
    nvgTransform(ctx, t.cur.get(0), t.cur.get(1), t.cur.get(2), t.cur.get(3), t.cur.get(4), t.cur.get(5))

  def setClearColor(c: Color) = glClearColor(c.r, c.g, c.b, c.a)

  def setFillColor(c: Color) = nvgFillColor(ctx, c.handle)
  def setFillPaint(p: Paint) = {p.apply(); nvgFillPaint(ctx, p.handle)}
  
  def setStrokeColor(c: Color) = nvgStrokeColor(ctx, c.handle)
  def setStrokePaint(p: Paint) = {p.apply(); nvgStrokePaint(ctx, p.handle)}
  def setStrokeWidth(w: Float) = nvgStrokeWidth(ctx, w)

  def setFont(f: Font) = nvgFontFaceId(ctx, f.id)
  def setFontSize(fs: Float) = nvgFontSize(ctx, fs)
  def setFontBlur(fb: Float) = nvgFontBlur(ctx, fb)

  def setTextAlign(ta: TextAlign) = nvgTextAlign(ctx, ta.id)
  def setTextLetterSpacing(tls: Float) = nvgTextLetterSpacing(ctx, tls)
  def setTextLineHight(tlh: Float) = nvgTextLineHeight(ctx, tlh)

// todo

  def globalAlpha() = 0

  def pathWinding(pw: PathWinding) = nvgPathWinding(ctx, pw.id)

  // LineStyle?
  def lineCap(lc: LineCap) = nvgLineCap(ctx, lc.id)
  def lineJoin(lj: LineJoin) = nvgLineJoin(ctx, lj.id)
  def lineMiterLimit(ml: Float) = nvgMiterLimit(ctx, ml)

  def scissor(b: Box) = 0


//////////////

  def shapeAntiAlias(enabled: Boolean) = 0 // set wether to render with anialiasing