package yage.math

import scala.util.Random
import yage.math.{Vec, Vec2, Vec3, Vec4}

/**
 * i: row index j: col index
 */

trait Mat[M <: Mat[M]](val n: Int) extends MatOp[M]:

  val cmp = Array.ofDim[Float](n, n)

  toOne()

  // todo: clone?
  def copy(): M

  def apply(i: Int, j: Int) = cmp(i)(j)
  def update(i: Int, j: Int, s: Float) = cmp(i)(j) = s

  def toZero() = foreach((i, j) => cmp(i)(j) = 0)
  def toOne() = foreach((i, j) => cmp(i)(j) = if i == j then 1 else 0)
  def toRandom(min: Float, max: Float) = foreach((i, j) => cmp(i)(j) = nextFloat(min, max))

//   def toTransposeOf(m: M) = set(m).transpose
//   def toInverseOf(m: M) = set(m).invert

  def trace(): Float
  def determinant(): Float

  def transpose(): M
  def transposed() = copy().transpose()

  def invert(): M
  def inverted() = copy().invert()

  def foreach(f: (Int, Int) => Unit) =
    for i <- 0 to n - 1 do
      for j <- 0 to n - 1 do
        f(i, j)
    asInstanceOf[M]

  def dotRC(i: Int, j: Int, m: M) =
    var res = 0.0f
    for k <- 0 to n - 1 do
      res += cmp(i)(k) * m(k, j)
    res

  def dotRV[V <: Vec[V]](r: Int, v: V) =
    var res = 0.0f
    for i <- 0 to n - 1 do
      res += cmp(r)(i) * v(i)
    res

  override def toString() =
    var s = ""
    for i <- 0 to n - 1 do
      for j <- 0 to n - 1 do
        s += f"${cmp(i)(j)}%10.2f"
    s + "\n"

///////////////////////////////////////////////////////////////////////////////

object Mat2:

  def apply(): Mat2 = new Mat2()
  def apply(m: Mat2): Mat2 = new Mat2() := m

class Mat2 extends Mat[Mat2](2):

  override def copy() = Mat2(this)

  override def trace() =
    this(0, 0) + this(1, 1)

  override def determinant() =
    this(0, 0) * this(1, 1) - this(0, 1) * this(1, 0)

  override def transpose() =
    val t01 = this(0, 1)
    this(0, 1) = this(1, 0)
    this(1, 0) = t01
    this

  override def invert() =
    val t00 = +this(1, 1)
    val t01 = -this(0, 1)
    val t10 = -this(1, 0)
    val t11 = +this(0, 0)
    this(0, 0) = t00
    this(0, 1) = t01
    this(1, 0) = t10
    this(1, 1) = t11
    this *= (1.0f / determinant())

  override def multiply(m: Mat2) =
    val t00 = dotRC(0, 0, m)
    val t01 = dotRC(0, 1, m)
    val t10 = dotRC(1, 0, m)
    val t11 = dotRC(1, 1, m)
    this(0, 0) = t00
    this(0, 1) = t01
    this(1, 0) = t10
    this(1, 1) = t11
    this

  override def toProductOf(m1: Mat2, m2: Mat2) =
    val t00 = m1.dotRC(0, 0, m2)
    val t01 = m1.dotRC(0, 1, m2)
    val t10 = m1.dotRC(1, 0, m2)
    val t11 = m1.dotRC(1, 1, m2)
    this(0, 0) = t00
    this(0, 1) = t01
    this(1, 0) = t10
    this(1, 1) = t11
    this

  def *(v: Vec2) = v.copy() =* (this, v)

///////////////////////////////////////////////////////////////////////////////


object Mat3:

  def apply(): Mat3 = new Mat3()
  def apply(m: Mat3): Mat3 = new Mat3() := m

class Mat3 extends Mat[Mat3](3), Trafo2:

  override def copy() = Mat3(this)

  override def trace() =
    this(0, 0) + this(1, 1) + this(2, 2)

  override def determinant() =
    val c00 = this(1, 1) * this(2, 2) - this(1, 2) * this(2, 1)
    val c01 = this(1, 2) * this(2, 0) - this(1, 0) * this(2, 2)
    val c02 = this(1, 0) * this(2, 1) - this(1, 1) * this(2, 0)
    this(0, 0) * c00 + this(0, 1) * c01 + this(0, 2) * c02

  override def transpose() =
    val t01 = this(0, 1)
    val t02 = this(0, 2)
    val t12 = this(1, 2)
    this(0, 1) = this(1, 0)
    this(0, 2) = this(2, 0)
    this(1, 2) = this(2, 1)
    this(1, 0) = t01
    this(2, 0) = t02
    this(2, 1) = t12
    this

  override def invert() =
    val t00 = this(1, 1) * this(2, 2) - this(1, 2) * this(2, 1)
    val t01 = this(0, 2) * this(2, 1) - this(0, 1) * this(2, 2)
    val t02 = this(0, 1) * this(1, 2) - this(0, 2) * this(1, 1)
    val t10 = this(1, 2) * this(2, 0) - this(1, 0) * this(2, 2)
    val t11 = this(0, 0) * this(2, 2) - this(0, 2) * this(2, 0)
    val t12 = this(0, 2) * this(1, 0) - this(0, 0) * this(1, 2)
    val t20 = this(1, 0) * this(2, 1) - this(1, 1) * this(2, 0)
    val t21 = this(0, 1) * this(2, 0) - this(0, 0) * this(2, 1)
    val t22 = this(0, 0) * this(1, 1) - this(0, 1) * this(1, 0)
    this(0, 0) = t00
    this(0, 1) = t01
    this(0, 2) = t02
    this(1, 0) = t10
    this(1, 1) = t11
    this(1, 2) = t12
    this(2, 0) = t20
    this(2, 1) = t21
    this(2, 2) = t22
    this *= (1.0f / determinant())

  override def multiply(m: Mat3) =
    val t00 = dotRC(0, 0, m);
    val t01 = dotRC(0, 1, m);
    val t02 = dotRC(0, 2, m);
    val t10 = dotRC(1, 0, m);
    val t11 = dotRC(1, 1, m);
    val t12 = dotRC(1, 2, m);
    val t20 = dotRC(2, 0, m);
    val t21 = dotRC(2, 1, m);
    val t22 = dotRC(2, 2, m);
    this(0, 0) = t00
    this(0, 1) = t01
    this(0, 2) = t02
    this(1, 0) = t10
    this(1, 1) = t11
    this(1, 2) = t12
    this(2, 0) = t20
    this(2, 1) = t21
    this(2, 2) = t22
    this

  override def toProductOf(m1: Mat3, m2: Mat3) =
    val t00 = m1.dotRC(0, 0, m2);
    val t01 = m1.dotRC(0, 1, m2);
    val t02 = m1.dotRC(0, 2, m2);
    val t10 = m1.dotRC(1, 0, m2);
    val t11 = m1.dotRC(1, 1, m2);
    val t12 = m1.dotRC(1, 2, m2);
    val t20 = m1.dotRC(2, 0, m2);
    val t21 = m1.dotRC(2, 1, m2);
    val t22 = m1.dotRC(2, 2, m2);
    this(0, 0) = t00
    this(0, 1) = t01
    this(0, 2) = t02
    this(1, 0) = t10
    this(1, 1) = t11
    this(1, 2) = t12
    this(2, 0) = t20
    this(2, 1) = t21
    this(2, 2) = t22
    this

  def *(v: Vec3) = v.copy() =* (this, v)

///////////////////////////////////////////////////////////////////////////////


object Mat4:

  def apply(): Mat4 = new Mat4()
  def apply(m: Mat4): Mat4 = new Mat4() := m

class Mat4 extends Mat[Mat4](4), Trafo3:

  override def copy() = Mat4(this)

  override def trace() =
    this(0, 0) + this(1, 1) + this(2, 2) + this(3, 3)

  override def determinant() =
    val a0 = this(0, 0) * this(1, 1) - this(0, 1) * this(1, 0)
    val a1 = this(0, 0) * this(1, 2) - this(0, 2) * this(1, 0)
    val a2 = this(0, 0) * this(1, 3) - this(0, 3) * this(1, 0)
    val a3 = this(0, 1) * this(1, 2) - this(0, 2) * this(1, 1)
    val a4 = this(0, 1) * this(1, 3) - this(0, 3) * this(1, 1)
    val a5 = this(0, 2) * this(1, 3) - this(0, 3) * this(1, 2)
    val b0 = this(2, 0) * this(3, 1) - this(2, 1) * this(3, 0)
    val b1 = this(2, 0) * this(3, 2) - this(2, 2) * this(3, 0)
    val b2 = this(2, 0) * this(3, 3) - this(2, 3) * this(3, 0)
    val b3 = this(2, 1) * this(3, 2) - this(2, 2) * this(3, 1)
    val b4 = this(2, 1) * this(3, 3) - this(2, 3) * this(3, 1)
    val b5 = this(2, 2) * this(3, 3) - this(2, 3) * this(3, 2)
    a0 * b5 - a1 * b4 + a2 * b3 + a3 * b2 - a4 * b1 + a5 * b0

  override def transpose() =
    val m01 = this(0, 1)
    val m02 = this(0, 2)
    val m03 = this(0, 3)
    val m12 = this(1, 2)
    val m13 = this(1, 3)
    val m23 = this(2, 3)
    this(0, 1) = this(1, 0)
    this(0, 2) = this(2, 0)
    this(0, 3) = this(3, 0)
    this(1, 2) = this(2, 1)
    this(1, 3) = this(3, 1)
    this(2, 3) = this(3, 1)
    this(1, 0) = m01
    this(2, 0) = m02
    this(2, 1) = m12
    this(3, 0) = m03
    this(3, 1) = m13
    this(3, 2) = m23
    this

  override def invert() =
    val a0 = this(0, 0) * this(1, 1) - this(0, 1) * this(1, 0)
    val a1 = this(0, 0) * this(1, 2) - this(0, 2) * this(1, 0)
    val a2 = this(0, 0) * this(1, 3) - this(0, 3) * this(1, 0)
    val a3 = this(0, 1) * this(1, 2) - this(0, 2) * this(1, 1)
    val a4 = this(0, 1) * this(1, 3) - this(0, 3) * this(1, 1)
    val a5 = this(0, 2) * this(1, 3) - this(0, 3) * this(1, 2)
    val b0 = this(2, 0) * this(3, 1) - this(2, 1) * this(3, 0)
    val b1 = this(2, 0) * this(3, 2) - this(2, 2) * this(3, 0)
    val b2 = this(2, 0) * this(3, 3) - this(2, 3) * this(3, 0)
    val b3 = this(2, 1) * this(3, 2) - this(2, 2) * this(3, 1)
    val b4 = this(2, 1) * this(3, 3) - this(2, 3) * this(3, 1)
    val b5 = this(2, 2) * this(3, 3) - this(2, 3) * this(3, 2)
    val det = a0 * b5 - a1 * b4 + a2 * b3 + a3 * b2 - a4 * b1 + a5 * b0
    val t00 = +this(1, 1) * b5 - this(1, 2) * b4 + this(1, 3) * b3
    val t10 = -this(1, 0) * b5 + this(1, 2) * b2 - this(1, 3) * b1
    val t20 = +this(1, 0) * b4 - this(1, 1) * b2 + this(1, 3) * b0
    val t30 = -this(1, 0) * b3 + this(1, 1) * b1 - this(1, 2) * b0
    val t01 = -this(0, 1) * b5 + this(0, 2) * b4 - this(0, 3) * b3
    val t11 = +this(0, 0) * b5 - this(0, 2) * b2 + this(0, 3) * b1
    val t21 = -this(0, 0) * b4 + this(0, 1) * b2 - this(0, 3) * b0
    val t31 = +this(0, 0) * b3 - this(0, 1) * b1 + this(0, 2) * b0
    val t02 = +this(3, 1) * a5 - this(3, 2) * a4 + this(3, 3) * a3
    val t12 = -this(3, 0) * a5 + this(3, 2) * a2 - this(3, 3) * a1
    val t22 = +this(3, 0) * a4 - this(3, 1) * a2 + this(3, 3) * a0
    val t32 = -this(3, 0) * a3 + this(3, 1) * a1 - this(3, 2) * a0
    val t03 = -this(2, 1) * a5 + this(2, 2) * a4 - this(2, 3) * a3
    val t13 = +this(2, 0) * a5 - this(2, 2) * a2 + this(2, 3) * a1
    val t23 = -this(2, 0) * a4 + this(2, 1) * a2 - this(2, 3) * a0
    val t33 = +this(2, 0) * a3 - this(2, 1) * a1 + this(2, 2) * a0
    this(0, 0) = t00
    this(0, 1) = t01
    this(0, 2) = t02
    this(0, 3) = t03
    this(1, 0) = t10
    this(1, 1) = t11
    this(1, 2) = t12
    this(1, 3) = t13
    this(2, 0) = t20
    this(2, 1) = t21
    this(2, 2) = t22
    this(2, 3) = t23
    this(3, 0) = t30
    this(3, 1) = t31
    this(3, 2) = t32
    this(3, 3) = t33
    this *= (1.0f / det)

  override def multiply(m: Mat4) =
    val t00 = dotRC(0, 0, m)
    val t01 = dotRC(0, 1, m)
    val t02 = dotRC(0, 2, m)
    val t03 = dotRC(0, 3, m)
    val t10 = dotRC(1, 0, m)
    val t11 = dotRC(1, 1, m)
    val t12 = dotRC(1, 2, m)
    val t13 = dotRC(1, 3, m)
    val t20 = dotRC(2, 0, m)
    val t21 = dotRC(2, 1, m)
    val t22 = dotRC(2, 2, m)
    val t23 = dotRC(2, 3, m)
    val t30 = dotRC(3, 0, m)
    val t31 = dotRC(3, 1, m)
    val t32 = dotRC(3, 2, m)
    val t33 = dotRC(3, 3, m)
    this(0, 0) = t00
    this(0, 1) = t01
    this(0, 2) = t02
    this(0, 3) = t03
    this(1, 0) = t10
    this(1, 1) = t11
    this(1, 2) = t12
    this(1, 3) = t13
    this(2, 0) = t20
    this(2, 1) = t21
    this(2, 2) = t22
    this(2, 3) = t23
    this(3, 0) = t30
    this(3, 1) = t31
    this(3, 2) = t32
    this(3, 3) = t33
    this

  override def toProductOf(m1: Mat4, m2: Mat4) =
    val t00 = m1.dotRC(0, 0, m2)
    val t01 = m1.dotRC(0, 1, m2)
    val t02 = m1.dotRC(0, 2, m2)
    val t03 = m1.dotRC(0, 3, m2)
    val t10 = m1.dotRC(1, 0, m2)
    val t11 = m1.dotRC(1, 1, m2)
    val t12 = m1.dotRC(1, 2, m2)
    val t13 = m1.dotRC(1, 3, m2)
    val t20 = m1.dotRC(2, 0, m2)
    val t21 = m1.dotRC(2, 1, m2)
    val t22 = m1.dotRC(2, 2, m2)
    val t23 = m1.dotRC(2, 3, m2)
    val t30 = m1.dotRC(3, 0, m2)
    val t31 = m1.dotRC(3, 1, m2)
    val t32 = m1.dotRC(3, 2, m2)
    val t33 = m1.dotRC(3, 3, m2)
    this(0, 0) = t00
    this(0, 1) = t01
    this(0, 2) = t02
    this(0, 3) = t03
    this(1, 0) = t10
    this(1, 1) = t11
    this(1, 2) = t12
    this(1, 3) = t13
    this(2, 0) = t20
    this(2, 1) = t21
    this(2, 2) = t22
    this(2, 3) = t23
    this(3, 0) = t30
    this(3, 1) = t31
    this(3, 2) = t32
    this(3, 3) = t33
    this

  def *(v: Vec4) = v.copy() =* (this, v)

