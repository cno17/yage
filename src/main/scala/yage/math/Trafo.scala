package yage.math


// import scala.language.postfixOps

/**
 * toRot(i, j, a) creates a rotation in the plane spanned by the i-th and j-th basis vectors
 * translateL/R should be easy to implement!      
 */

trait Trafo[V <: Vec[V], M <: Mat[M]]:

  this: M =>

  def toScale(s: S) =
    toOne()
    for i <- 0 to n - 2 do 
      this(i, i) = s
    this
  
  def toRotation(i: Int, j: Int, a: S) = this
  
  def toTranslation(v: V) =
    toOne()
    for i <- 0 to n - 2 do
      this(i, n - 1) = v(i)
    this

  def scaleL(s: S) =
    for i <- 0 to n - 2 do 
      for j <- 0 to n - 2 do
        this(i, j) *= s
    
  def scaleR(s: S) =
    for i <- 0 to n - 2 do
      for j <- 0 to n - 2 do
        this(i, j) *= s
  
  def translateL(v: V): M
  def translateR(v: V): M
  
  def composeL(m: M) = toProductOf(m, this)
  def composeR(m: M) = toProductOf(this, m)

  def transformDir(dir: V): V = transformDir(dir, dir)
  def transformDir(dir: V, res: V): V
  def transformPos(pos: V): V = transformPos(pos, pos)
  def transformPos(pos: V, res: V): V
  
/*	
  def traL(v: V) =
    for i <- 0 to n - 2 do 
      this(i, n - 1) += v(i)
    this

  def leftTranslate(v: V): M
    // vec += v
    // asInstanceOf[H]

  protected def setLastRow =
    for j <- 0 to n - 1 do
      this(n - 1, j) = if j < n - 1 then 0 else 1
    this
*/

///////////////////////////////////////////////////////////////////////////////


trait Trafo2 extends Trafo[Vec2, Mat3] :

  this: Mat3 =>

  def toRotation(a: S) =
    toOne()
    val sa = sin(a)
    val ca = cos(a)
    this (0, 0) = +ca
    this (0, 1) = -sa
    this (1, 0) = +sa
    this (1, 1) = +ca
    this

  def toTranslation(x: S, y: S) =
    toOne()
    this(0, 2) = x
    this(1, 2) = y
    this

  // todo: 
  
  def rotateL(a: S) = toProductOf(Mat3().toRotation(a), this)
  
  def rotateR(a: S) = toProductOf(this, Mat3().toRotation(a))

  def translateL(x: S, y: S) =
    this(0, 2) += x
    this(1, 2) += y
    this

  def translateR(x: S, y: S) =
    this(0, 2) += this(0, 0) * x + this(0, 1) * y
    this(1, 2) += this(1, 0) * x + this(1, 1) * y
    this

  override def translateL(v: Vec2) = translateL(v.x, v.y)

  override def translateR(v: Vec2) = translateR(v.x, v.y)

  override def transformPos(pos: Vec2, res: Vec2) =
    val tx = this(0, 0) * pos.x + this(0, 1) * pos.y + this(0, 2)
    val ty = this(1, 0) * pos.x + this(1, 1) * pos.y + this(1, 2)
    res := (tx, ty)

  override def transformDir(dir: Vec2, res: Vec2) =
    val tx = this(0, 0) * dir.x + this(0, 1) * dir.y
    val ty = this(1, 0) * dir.x + this(1, 1) * dir.y
    res := (tx, ty)

///////////////////////////////////////////////////////////////////////////////

trait Trafo3 extends Trafo[Vec3, Mat4] :

  this: Mat4 =>

  def toRotation(v: Vec3, a: S): Mat4 = toRotation(v.x, v.y, v.z, a)

  def toRotation(x: S, y: S, z: S, a: S): Mat4 =
    toOne()
    val sa = sin(a)
    val ca = cos(a)
    val ac = 1 - ca
    val xx = x * x
    val xy = x * y
    val xz = x * z
    val yx = y * x
    val yy = y * y
    val yz = y * z
    val zx = z * x
    val zy = z * y
    val zz = z * z
    this (0, 0) = xx + ca * (1 - xx)
    this (0, 1) = xy * ac - z * sa
    this (0, 2) = xz * ac + y * sa
    this (1, 0) = yx * ac + z * sa
    this (1, 1) = yy + ca * (1 - yy)
    this (1, 2) = yz * ac - x * sa
    this (2, 0) = zx * ac - y * sa
    this (2, 1) = zy * ac + x * sa
    this (2, 2) = zz + ca * (1 - zz)
    this

  def toTranslation(x: S, y: S, z: S) =
    toOne()
    this(0, 3) = x
    this(1, 3) = y
    this(2, 3) = z
    this

  // def toLookAt(p1: Vec3, p2: Vec3) = toLookAt(p1.x, p1.y, p1.z, p2.x, p2.y, p2.z)

  def toLookAt(x1: S, y1: S, z1: S, x2: S, y2: S, z2: S) = this // todo

  def toOrtho(l: S, r: S, b: S, t: S, n: S, f: S) =
    toOne()
    this(0, 0) = 2 / (r - l)
    this(0, 3) = (l + r) / (l - r)
    this(1, 1) = 2 / (t - b)
    this(1, 3) = (b + t) / (b - t)
    this(2, 2) = 2 / (n - f)
    this(2, 3) = (n + f) / (n - f)
    this

  def toFrustum(l: S, r: S, b: S, t: S, n: S, f: S) =
    toZero()
    this(0, 0) = (2 * n) / (r - l)
    this(0, 2) = (r + l) / (r - l)
    this(1, 1) = (2 * n) / (t - b)
    this(1, 2) = (t + b) / (t - b)
    this(2, 2) = (n + f) / (n - f)
    this(2, 3) = (2 * n * f) / (n - f)
    this(3, 2) = -1
    this

  def toPerspective = 0

  // 

  def rotateL(x: S, y: S, z: S, a: S) = toProductOf(Mat4().toRotation(x, y, z, a), this)
  def rotateR(x: S, y: S, z: S, a: S) = toProductOf(this, Mat4().toRotation(x, y, z, a))
  
  def rotateL(v: Vec3, a: S): Mat4 = rotateL(v.x, v.y, v.z, a)
  def rotateR(v: Vec3, a: S): Mat4 = rotateR(v.x, v.y, v.z, a)
  
  def translateL(x: S, y: S, z: S) =
    this(0, 3) += x
    this(1, 3) += y
    this(2, 3) += z
    this

  def translateR(x: S, y: S, z: S) =
    this(0, 3) += this(0, 0) * x + this(0, 1) * y + this(0, 2) * z
    this(1, 3) += this(1, 0) * x + this(1, 1) * y + this(1, 2) * z
    this(2, 3) += this(2, 0) * x + this(2, 1) * y + this(2, 2) * z
    this

  override def translateL(v: Vec3) = translateL(v.x, v.y, v.z)

  override def translateR(v: Vec3) = translateR(v.x, v.y, v.z)

  override def transformPos(pos: Vec3, res: Vec3) =
    val tx = this(0, 0) * pos.x + this(0, 1) * pos.y + this(0, 2) * pos.z + this(0, 3)
    val ty = this(1, 0) * pos.x + this(1, 1) * pos.y + this(1, 2) * pos.z + this(1, 3)
    val tz = this(2, 0) * pos.x + this(2, 1) * pos.y + this(2, 2) * pos.z + this(2, 3)
    res := (tx, ty, tz)

  override def transformDir(dir: Vec3, res: Vec3) =
    val tx = this(0, 0) * dir.x + this(0, 1) * dir.y + this(0, 2) * dir.z
    val ty = this(1, 0) * dir.x + this(1, 1) * dir.y + this(1, 2) * dir.z
    val tz = this(2, 0) * dir.x + this(2, 1) * dir.y + this(2, 2) * dir.z
    res := (tx, ty, tz)

/*    
  def toRotationX(a: S) = toRotation(1, 0, 0, a)
  def toRotationY(a: S) = toRotation(0, 1, 0, a)
  def toRotationZ(a: S) = toRotation(0, 0, 1, a)


  def rotateXL(a: S) = rotateL(1, 0, 0, a)
  def rotateXR(a: S) = rotateR(1, 0, 0, a)
  def rotateYL(a: S) = rotateL(0, 1, 0, a)
  def rotateYR(a: S) = rotateR(0, 1, 0, a)
  def rotateZL(a: S) = rotateL(0, 0, 1, a)
  def rotateZR(a: S) = rotateR(0, 0, 1, a)


  def translateXL(t: S) = translateL(t, 0, 0)
  def translateXR(t: S) = translateR(t, 0, 0)
  def translateYL(t: S) = translateL(0, t, 0)
  def translateYR(t: S) = translateR(0, t, 0)
  def translateZL(t: S) = translateL(0, 0, t)
  def translateZR(t: S) = translateR(0, 0, t)

*/  