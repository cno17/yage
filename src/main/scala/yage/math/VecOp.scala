package yage.math

import java.nio.ByteBuffer
import java.nio.FloatBuffer

trait VecOp[V <: Vec[V]] extends MathUtil:

  this: Vec[V] =>

  def :=(v: V) = set(v)

  def +=(v: V) = add(v)
  def +=(v: V, s: S): V = add(v, s)
  def -=(v: V) = subtract(v)
  def -=(v: V, s: S): V = subtract(v, s)
  def *=(s: S): V = multiply(s)

  def =+(v1: V, v2: V) = toSumOf(v1, v2)
  def =+(v1: V, s1: S, v2: V, s2: S) = toSumOf(v1, s1, v2, s2)
  def =-(v1: V, v2: V) = toDifferenceOf(v1, v2)
  def =-(v1: V, s1: S, v2: V, s2: S) = toDifferenceOf(v1, s1, v2, s2)
  def =*(v: V, s: S) = toProductOf(v, s)

  def +(v: V) = added(v)
  def +(v: V, s: S) = added(v, s)
  def -(v: V) = subtracted(v)
  def -(v: V, s: S) = subtracted(v, s)
  def *(s: S) = multiplied(s)
  
  def <<(buf: ByteBuffer) = load(buf)
  def <<(buf: ByteBuffer, off: Int) = load(buf, off)
  def <<(buf: FloatBuffer) = load(buf)
  def <<(buf: FloatBuffer, off: Int) = load(buf, off)
  def >>(buf: ByteBuffer) = store(buf)
  def >>(buf: ByteBuffer, off: Int) = store(buf, off)
  def >>(buf: FloatBuffer) = store(buf)
  def >>(buf: FloatBuffer, off: Int) = store(buf, off)

  def set(v: V) = foreach(i => cmp(i) = v(i))

  def add(v: V) = foreach(i => cmp(i) += v(i))
  def add(v: V, s: S) = foreach(i => cmp(i) += v(i) * s)
  def subtract(v: V): V = foreach(i => cmp(i) -= v(i))
  def subtract(v: V, s: S): V = foreach(i => cmp(i) -= v(i) * s)
  def multiply(s: S) = foreach(i => cmp(i) *= s)

  def toSumOf(v1: V, v2: V) = foreach(i => cmp(i) = v1(i) + v2(i))
  def toSumOf(v1: V, s1: S, v2: V, s2: S) = foreach(i => cmp(i) = v1(i) * s1 + v2(i) * s2)
  def toDifferenceOf(v1: V, v2: V) = foreach(i => cmp(i) = v1(i) - v2(i))
  def toDifferenceOf(v1: V, s1: S, v2: V, s2: S) = foreach(i => cmp(i) = v1(i) * s1 - v2(i) * s2)
  def toProductOf(v: V, s: S) = foreach(i => cmp(i) = v(i) * s)

  // sum?
  def added(v: V) = copy() += v
  def added(v: V, s: S) = copy() += (v, s)
  // difference?
  def subtracted(v: V) = copy() -= v
  def subtracted(v: V, s: S) = copy() -= (v, s)
  // product?
  def multiplied(s: S) = copy() *= s

  // float buffer support needed?

  def load(buf: ByteBuffer) = foreach(i => cmp(i) = buf.getFloat())
  def load(buf: ByteBuffer, off: Int) = foreach(i => cmp(i) = buf.getFloat(off + 4 * i))
  def load(buf: FloatBuffer) = foreach(i => cmp(i) = buf.get())
  def load(buf: FloatBuffer, off: Int) = foreach(i => cmp(i) = buf.get(off + i))
  def store(buf: ByteBuffer) = foreach(i => buf.putFloat(cmp(i)))
  def store(buf: ByteBuffer, off: Int) = foreach(i => buf.putFloat(off + 4 * i, cmp(i)))
  def store(buf: FloatBuffer) = foreach(i => buf.put(cmp(i)))
  def store(buf: FloatBuffer, off: Int) = foreach(i => buf.put(off + i, cmp(i)))

  
  // todo def =*[M <: Mat[V, M]](m: M, v: V): V
