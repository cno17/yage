package yage.graphics.vg

import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.BufferUtils
import yage.graphics.vg.Trafo
import yage.graphics.StackUser

// components
// |a c e|
// |b d f|

// cuurent trafo of context

// nvgTransformPoint​(), ...

/**
 * Paths, gradients, patterns and scissors are transformed by the current trafo
 * at the time they are passed to the API. 
 */

class Trafo:

  val cur = BufferUtils.createFloatBuffer(6)
  val tmp = BufferUtils.createFloatBuffer(6)

  reset()

  def apply(r: Int, c: Int) = cur.get(r + 2 * c)
  def update(r: Int, c: Int, v: Float) = cur.put(r + 2 * c, v)

  def reset() = { nvgTransformIdentity(cur); this }
  def invert() = { nvgTransformInverse(cur, cur); this }

  def toRot(a: Float) = { nvgTransformRotate(cur, a); this }
  def toTra(tx: Float, ty: Float) = { nvgTransformTranslate(cur, tx, ty); this }
  def toSca(sx: Float, sy: Float) = { nvgTransformScale(cur, sx, sy); this }

  def rotL(a: Float) = { nvgTransformRotate(tmp, a); nvgTransformPremultiply(cur, tmp); this }
  def rotR(a: Float) = { nvgTransformRotate(tmp, a); nvgTransformMultiply(cur, tmp); this }

  def traL(tx: Float, ty: Float) = { nvgTransformTranslate(tmp, tx, ty); nvgTransformPremultiply(cur, tmp); this }
  def traR(tx: Float, ty: Float) = { nvgTransformTranslate(tmp, tx, ty); nvgTransformMultiply(cur, tmp); this }

  def scaL(sx: Float, sy: Float) = { nvgTransformScale(tmp, sx, sy); nvgTransformPremultiply(cur, tmp); this }
  def scaR(sx: Float, sy: Float) = { nvgTransformScale(tmp, sx, sy); nvgTransformMultiply(cur, tmp); this }

  // correct?

  def mulL(t: Trafo) =
    nvgTransformPremultiply(cur, t.cur)
    this

  def mulR(t: Trafo) =
    nvgTransformMultiply(cur, t.cur)
    this

  override def toString() =
    var s0 = f"${apply(0, 0)}%5.2f ${apply(0, 1)}%5.2f ${apply(0, 2)}%5.2f"
    var s1 = f"${apply(1, 0)}%5.2f ${apply(1, 1)}%5.2f ${apply(1, 2)}%5.2f"
    s0 + "\n" + s1 + "\n"
