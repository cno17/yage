package test.math

object DesignTest2:

  private trait Vec[V <: Vec[V]](val n: Int)

  private trait Mat[V <: Vec[V], M <: Mat[V, M]](val n: Int)

  private trait Hom[V <: Vec[V], M <: Mat[V, M], H <: Hom[V, M, H]](val n: Int):
    var mat: M
    var vec: V

  private trait Trafo(val n: Int)
  
  private trait LinearTrafo[V <: Vec[V], M <: Mat[V, M], T <: LinearTrafo[V, M, T]](n: Int) extends Trafo
  
  private trait AffineTrafo extends Trafo
  
  private trait ProjectiveTrafo extends Trafo

  private class Vec2 extends Vec[Vec2](2)

  def main(args: Array[String]) =
    println(2)