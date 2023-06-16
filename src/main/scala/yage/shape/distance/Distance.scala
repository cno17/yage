package yage.shape.distance

import yage.math.Vec
import yage.shape.Shape

/**
 * Compute 
 * (1) the closest points (proxima) on and
 * (2) the squared euclidean distance between 
 * objects which can be points or shapes. 
 */

abstract class Distance[V <: Vec[V], A <: V | Shape[V], B <: Shape[V]]:

  abstract class Info:
    val cp1: V
    val cp2: V

  protected var info: Info

  def get(a: A, b: B): Info
