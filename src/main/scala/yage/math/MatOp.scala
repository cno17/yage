package yage.math

import java.nio.ByteBuffer
import java.nio.FloatBuffer

/**
 * The "stream operator" >> writes this matrix in column major order as expected by opengl. FloatBuffers needed?
 */

trait MatOp[M <: Mat[M]] extends MathUtil:

  this: Mat[M] =>

  def :=(m: M) = set(m)

  def +=(m: M) = add(m)
  def -=(m: M) = subtract(m)
  def *=(s: S): M = multiply(s)
  def *=(m: M): M = multiply(m)

  def =+(m1: M, m2: M) = toSumOf(m1, m2)
  def =-(m1: M, m2: M) = toDifferenceOf(m1, m2)
  def =*(m: M, s: S) = toProductOf(m, s)
  def =*(m1: M, m2: M) = toProductOf(m1, m2)

  def +(m: M) = added(m)
  def -(m: M) = subtracted(m)
  def *(s: S) = multiplied(s)
  def *(m: M) = multiplied(m)

  def <<(buf: ByteBuffer) = load(buf)
  def <<(buf: ByteBuffer, off: Int) = load(buf, off)
  def <<(buf: FloatBuffer) = load(buf)
  def <<(buf: FloatBuffer, off: Int) = load(buf, off)
  def >>(buf: ByteBuffer) = store(buf)
  def >>(buf: ByteBuffer, off: Int) = store(buf, off)
  def >>(buf: FloatBuffer) = store(buf)
  def >>(buf: FloatBuffer, off: Int) = store(buf, off)

  def set(m: M) = foreach((i, j) => cmp(i)(j) = m(i, j))

  def add(m: M) = foreach((i, j) => cmp(i)(j) += m(i, j))
  def subtract(m: M) = foreach((i, j) => cmp(i)(j) -= m(i, j))
  def multiply(s: S) = foreach((i, j) => cmp(i)(j) *= s)
  def multiply(m: M): M

  def toSumOf(m1: M, m2: M) = foreach((i, j) => cmp(i)(j) = m1(i, j) + m2(i, j))
  def toDifferenceOf(m1: M, m2: M) = foreach((i, j) => cmp(i)(j) = m1(i, j) - m2(i, j))
  def toProductOf(m: M, s: S) = foreach((i, j) => cmp(i)(j) = m(i, j) * s)
  def toProductOf(m1: M, m2: M): M

  // sum?
  def added(m: M) = copy() += m
  def subtracted(m: M) = copy() -= m
  def multiplied(s: S) = copy() *= s
  def multiplied(m: M) = copy() *= m

  // todo
  def load(buf: ByteBuffer) = this
  def load(buf: ByteBuffer, off: Int) = this
  def load(buf: FloatBuffer) = this
  def load(buf: FloatBuffer, off: Int) = this
  def store(buf: ByteBuffer) = this // foreach((i, j) => buf.putFloat(off + 4 * (i * n + j), this(j, i)))
  def store(buf: ByteBuffer, off: Int) = foreach((i, j) => buf.putFloat(off + 4 * (i * n + j), cmp(j)(i)))
  def store(buf: FloatBuffer) = this // foreach((i, j) => buf.put(off + i * n + j, this(j, i)))
  def store(buf: FloatBuffer, off: Int) = foreach((i, j) => buf.put(off + i * n + j, cmp(j)(i)))

// def *(v: V) = v.copy =* (this, v)

/*
def <<(buf: FloatBuffer, off: Int) = this
def >>(buf: FloatBuffer, off: Int) = foreach((i, j) => buf.put(off + i * n + j, this(j, i)))



  def +=(m: M) = foreach((i, j) => set(i, j, get(i, j) + m(i, j)))
  def -=(m: M) = foreach((i, j) => set(i, j, get(i, j) - m(i, j)))

  def =+(m1: M, m2: M) = foreach((i, j) => set(i, j, m1(i, j) + m2(i, j)))
  def =-(m1: M, m2: M) = foreach((i, j) => set(i, j, m1(i, j) - m2(i, j)))

  def +(m: M) = copy += m
  def -(m: M) = copy -= m



        public void putf(Matrix4d m, int offset, FloatBuffer dest) {
            dest.put(offset,    (float)m.m00())
            .put(offset+1,  (float)m.m01())
            .put(offset+2,  (float)m.m02())
            .put(offset+3,  (float)m.m03())
            .put(offset+4,  (float)m.m10())
            .put(offset+5,  (float)m.m11())
            .put(offset+6,  (float)m.m12())
            .put(offset+7,  (float)m.m13())
            .put(offset+8,  (float)m.m20())
            .put(offset+9,  (float)m.m21())
            .put(offset+10, (float)m.m22())
            .put(offset+11, (float)m.m23())
            .put(offset+12, (float)m.m30())
            .put(offset+13, (float)m.m31())
            .put(offset+14, (float)m.m32())
            .put(offset+15, (float)m.m33());
        }

 */
