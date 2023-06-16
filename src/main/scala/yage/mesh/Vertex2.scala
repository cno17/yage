package yage.mesh

import yage.math.Vec2

class Vertex2(
  edge: Edge2
) extends Vec2, Vertex[Vertex2, Edge2, Face2](edge)

//

object Vertex2:
  
  def apply(): Vertex2 = apply(0, 0)
  def apply(p: Vec2): Vertex2 = apply(p.x, p.y)
  def apply(x: Float, y: Float) = new Vertex2(null).set(x, y).asInstanceOf[Vertex2]
