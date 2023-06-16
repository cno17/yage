package yage.math

import java.nio.ByteBuffer

// n -> dim
// def byteCount

trait Vec[V <: Vec[V]](val n: Int) extends VecOp[V]:

  val cmp = Array.ofDim[S](n)

  def copy(): V

  def x = this(0)
  def y = this(1)
  def z = this(2)
  def w = this(3)

  def x_=(s: Float) = this(0) = s
  def y_=(s: Float) = this(1) = s
  def z_=(s: Float) = this(2) = s
  def w_=(s: Float) = this(3) = s

  def apply(i: Int) = cmp(i)
  def update(i: Int, s: S) = cmp(i) = s

  def toZero() = foreach(i => cmp(i) = 0)
  def toUnit(d: Int) = foreach(i => cmp(i) = if i == d then 1 else 0)
  def toRandom(min: S, max: S) = foreach(i => cmp(i) = nextFloat(min, max))
  def toMin() = foreach(i => cmp(i) = Float.MinValue)
  def toMax() = foreach(i => cmp(i) = Float.MaxValue)

  def negate() = multiply(-1)
  def negated() = copy().negate()

  def normalize() = multiply(1.0f / length())
  def normalized() = copy().normalize()

  def dot(v: V) =
    var res = 0.0f
    foreach(i => res += cmp(i) * v(i))
    res

  def length() = sqrt(lengthSquared())

  def lengthSquared() =
    var res = 0.0f
    foreach(i => res += cmp(i) * cmp(i))
    res

  def distance(v: V) = sqrt(distanceSquared(v))

  def distanceSquared(v: V) =
    var res = 0.0f
    foreach(i => { val dif = cmp(i) - v(i); res += dif * dif })
    res

  def angle(v: V) =
    var arg = dot(v) / (length() * v.length())
    var abs = Math.abs(arg)
    if abs > 1 then arg /= abs
    acos(arg)

  def foreach(f: Int => Unit) =
    for i <- 0 to n - 1 do f(i)
    asInstanceOf[V]

  override def toString() =
    var s = "("
    for i <- 0 to n - 2 do
      s += f"${cmp(i)}%.2f "
    s + f"${cmp(n - 1)}%.2f)"

///////////////////////////////////////////////////////////////////////////////

class Vec3(xx: Float, yy: Float, zz: Float) extends Vec[Vec3](3):

  set(xx, yy, zz)

  def this() = this(0, 0, 0)
  def this(v: Vec3) = this(v.x, v.y, v.z)

  def set(x: Float, y: Float, z: Float) =
    this(0) = x
    this(1) = y
    this(2) = z
    this

  def toProductOf(m: Mat3, v: Vec3) =
    val tx = m.dotRV(0, v)
    val ty = m.dotRV(1, v)
    val tz = m.dotRV(2, v)
    set(tx, ty, tz)

  def det(v1: Vec3, v2: Vec3) = 0 // todo

  // todo operator form? v x= w

  def cross(v: Vec3): Vec3 =
    val tx = y * v.z - z * v.y
    val ty = z * v.x - x * v.z
    val tz = x * v.y - y * v.x
    set(tx, ty, tz)

  def crossed(v: Vec3) = copy().cross(v)

  override def copy() = Vec3(this)

  def :=(x: Float, y: Float, z: Float) = set(x, y, z)
  def =*(m: Mat3, v: Vec3) = toProductOf(m, v)

  // def x(v: Vec3) = crossed(v)

///////////////////////////////////////////////////////////////////////////////

class Vec4(xx: Float, yy: Float, zz: Float, ww: Float) extends Vec[Vec4](4):

  set(xx, yy, zz, ww)

  def this() = this(0, 0, 0, 0)
  def this(v: Vec4) = this(v.x, v.y, v.z, v.w)

  def set(x: Float, y: Float, z: Float, w: Float) =
    this(0) = x
    this(1) = y
    this(2) = z
    this(3) = w
    this

  def toProductOf(m: Mat4, v: Vec4) =
    val tx = m.dotRV(0, v)
    val ty = m.dotRV(1, v)
    val tz = m.dotRV(2, v)
    val tw = m.dotRV(3, v)
    :=(tx, ty, tz, tw)

  def det(v1: Vec4, v2: Vec4, v3: Vec4) = 0 // todo

  override def copy() = Vec4(this)

  def :=(x: Float, y: Float, z: Float, w: Float) = set(x, y, z, w)
  def =*(m: Mat4, v: Vec4) = toProductOf(m, v)
