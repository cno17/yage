package yage.shape.intersection

import yage.math.Vec
import yage.shape.Segment

class SegmentIntersector[V <: Vec[V]] extends ShapeIntersector[V, Segment[V], Segment[V]] :

  override def test(s1: Segment[V], s2: Segment[V]): Boolean = false