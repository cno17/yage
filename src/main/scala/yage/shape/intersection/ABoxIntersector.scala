package yage.shape.intersection

import yage.math.Vec
import yage.shape.ABox

class ABoxIntersector[V <: Vec[V]] extends ShapeIntersector[V, ABox[V], ABox[V]]:

  override def test(box1: ABox[V], box2: ABox[V]) = false

  // override def find(box1: ABox, box2: ABox) = null
