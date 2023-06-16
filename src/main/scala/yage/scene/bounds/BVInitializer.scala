package yage.scene.bounds

import yage.math.Vec
import yage.shape.ABox

trait BVInitializer[V <: Vec[V]]:

  def init(box: ABox[V], ps: Iterable[V]) =
    val min = box.min
    val max = box.max
    min.toMax()
    max.toMin()
    for p <- ps do
      for i <- 0 to p.n - 1 do
        if p(i) < min(i) then min(i) = p(i)
        else if p(i) > max(i) then max(i) = p(i)
    box
