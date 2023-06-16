package yage.shape.distance

import yage.math.Vec
import yage.shape.OBox

abstract class PointOBoxDistance[V <: Vec[V]] extends Distance[V, V, OBox[V]]:
  
  protected val dif: V
  protected val pro: V
    
  override def get(pos: V, box: OBox[V]) =
    dif =- (pos, box.org)
    info.cp1 := pos
    for i <- 0 to pos.n - 1 do
      var dot = dif.dot(box.dir(i))
      if dot < -box.rad(i) then dot = -box.rad(i)
      if dot > +box.rad(i) then dot = +box.rad(i)
      // dot = Math.min(Math.max(pos(i), box.min(i)), box.max(i))
      // info.p2(i) = box.org(i) + box.dir(i) * dot
    info
