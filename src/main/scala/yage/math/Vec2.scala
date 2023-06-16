package yage.math

// be careful: class params can create name conflicts!

class Vec2(xx: Float, yy: Float) extends Vec[Vec2](2):

  set(xx, yy)

  def this() = this(0, 0)
  def this(v: Vec2) = this(v.x, v.y)

  def set(x: S, y: S) =
    this(0) = x
    this(1) = y
    this

  def toProductOf(m: Mat2, v: Vec2) =
    val tx = m.dotRV(0, v)
    val ty = m.dotRV(1, v)
    set(tx, ty)

  def det(v: Vec2) = x * v.y - v.x * y

  // todo
  def rotate(a: Float) =
    val sa = sin(a)
    val ca = cos(a)
    val xx = ca * x - sa * y
    val yy = sa * x + ca * y
    set(xx, yy)
    // set(ca * x - sa * y, sa * x + ca * y)

  def rotated(a: Float) = copy().rotate(a)

  def distance(px: Float, py: Float) = sqrt(distanceSquared(px, py))
  
  def distanceSquared(px: Float, py: Float) =
    val dx = x - px
    val dy = y - py
    dx * dx + dy * dy  

  override def copy() = Vec2(this)

  def :=(x: S, y: S) = set(x, y)
  def =*(m: Mat2, v: Vec2) = toProductOf(m, v)
