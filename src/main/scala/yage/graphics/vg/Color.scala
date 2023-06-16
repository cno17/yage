package yage.graphics.vg

import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NanoVG.nvgRGB
import org.lwjgl.nanovg.NanoVG.nvgRGBA
import org.lwjgl.nanovg.NanoVG.nvgRGBAf
import org.lwjgl.nanovg.NanoVG.nvgRGBf

class Color:

  val handle = NVGColor.create()

  def r = handle.r()
  def g = handle.g()
  def b = handle.b()
  def a = handle.a()

  def r_=(v: Float) = {handle.r(v); this}
  def g_=(v: Float) = {handle.g(v); this}
  def b_=(v: Float) = {handle.b(v); this}
  def a_=(v: Float) = {handle.a(v); this}

  def set(c: Color): Unit = set(c.r, c.g, c.b, c.a)
  def set(r: Float, g: Float, b: Float) = {nvgRGBf(r, g, b, handle); this}
  def set(r: Float, g: Float, b: Float, a: Float) = {nvgRGBAf(r, g, b, a, handle); this}

  def interpolate(c1: Color, c2: Color, a: Float) = 0

  override def toString() = s"($r, $g, $b, $a)"

object Color:

  def apply() = new Color  
  def apply(r: Float, g: Float, b: Float) = new Color set(r, g, b)
  def apply(r: Float, g: Float, b: Float, a: Float) = new Color set(r, g, b, a)




  /*
  def apply(r: Int, g: Int, b: Int) = new Color set(r, g, b)
  def apply(r: Int, g: Int, b: Int, a: Int) = new Color set(r, g, b, a)
  def set(r: Int, g: Int, b: Int) = {nvgRGB(r.toByte, g.toByte, b.toByte, handle); this}
  def set(r: Int, g: Int, b: Int, a: Int) = {nvgRGBA(r.toByte, g.toByte, b.toByte, a.toByte, handle); this}


  */