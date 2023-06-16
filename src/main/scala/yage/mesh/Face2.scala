package yage.mesh

import yage.math.Vec2

class Face2(
  edge: Edge2
) extends Face[Vertex2, Edge2, Face2](edge):

  def center: Vec2 = null

//

object Face2:

  def apply() = new Face2(null)