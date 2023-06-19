package test.math

object DesignTest1:

  private trait Vec[V <: Vec[V]](n: Int):
    def cmp: Array[Float]
  
  private trait Mat[V <: Vec[V], M <: Mat[V, M]](n: Int):
    def col: Array[V]
  
  private trait Hom[V <: Vec[V], M <: Mat[V, M], H <: Hom[V, M, H]](n: Int):
    var mat: M
    var vec: V
  
  private trait Frame[V <: Vec[V]]:
    def org: V
    def dir(i: Int): V
    
    
  private trait Trafo
  
  private trait LinearTrafo extends Trafo
  
  private trait AffineTrafo extends Trafo
  
  private trait ProjectiveTrafo extends Trafo