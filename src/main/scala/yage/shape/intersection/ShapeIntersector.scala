package yage.shape.intersection

import yage.math.Vec
import yage.shape.Shape

/**
 * 
 */

trait ShapeIntersector[V <: Vec[V], A <: Shape[V], B <: Shape[V]]:

  // type Info

  // val info: Info

  def test(a: A, b: B): Boolean
  
  // def find(a: A, b: B): Info
