package yage.shape.distance

import yage.math.Vec
import yage.shape.ABox

abstract class PointABoxDistance[V <: Vec[V]] extends Distance[V, V, ABox[V]]:
  
  override def get(pos: V, box: ABox[V]) =
    info.cp1 := pos
    for i <- 0 to pos.n - 1 do
      info.cp2(i) = Math.min(Math.max(pos(i), box.min(i)), box.max(i))
    info
  
/*	
  override def get(pos: Vec, box: ABox) = {
    info.p1 := pos
    for (i <- 0 to n - 1) {
      info.p2(i) = Math.min(Math.max(pos(i), box.min(i)), box.max(i))
    }
    info.ds = info.p1.distanceSquared(info.p2)
    info
  }
}
*/