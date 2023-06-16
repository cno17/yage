package yage.math

// homogeneous vectors?

trait Frame[V <: Vec[V], F <: Frame[V, F]]:

  def org: V
  def dir: Array[V]

  // def <<(t: AffineTrafo): F
  // def >>(t: AffineTrafo): T

  // def orthonormalize: Unit
  // def orthonormalized: F // type param

///////////////////////////////////////////////////////////////////////////////  
  
trait Frame2 extends Frame[Vec2, Frame2] :

  val org = Vec2()
  val dir = Array(Vec2(), Vec2())

  def dirX = dir(0)
  def dirY = dir(1)

///////////////////////////////////////////////////////////////////////////////  
  
trait Frame3 extends Frame[Vec3, Frame3] :

  val org = Vec3()
  val dir = Array(Vec3(), Vec3(), Vec3())

  def dirX = dir(0)
  def dirY = dir(1)
  def dirZ = dir(2)
