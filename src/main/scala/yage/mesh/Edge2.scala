package yage.mesh

// class param names must be different from those of base class!

class Edge2(
    st: Vertex2,
    pr: Edge2,
    tw: Edge2,
    fa: Face2
) extends Edge[Vertex2, Edge2, Face2](st, pr, tw, fa):

  def length = start.distance(end)
  def lengthSquared = start.distanceSquared(end)

  override def end = twin.start
  override def succ = twin.pred.twin

//

object Edge2:  

  def apply() = new Edge2(null, null, null, null)
  def apply(start: Vertex2, face: Face2) = new Edge2(start, null, null, face)
