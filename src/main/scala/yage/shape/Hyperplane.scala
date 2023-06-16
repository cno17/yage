package yage.shape

import yage.math.Vec

/**
 * Classify by signed (pseudo) distance.
 * Move computations to HyperplaneClassifier?
 */

trait Hyperplane[V <: Vec[V]]:
  
  def point: V
  def normal: V

  def classify(pos: V): Float
  def classify(box: ABox[V]): Float
  def classify(box: OBox[V]): Float
  def classify(sph: Sphere[V]): Float