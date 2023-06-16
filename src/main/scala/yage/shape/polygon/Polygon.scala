package yage.shape.polygon

class Polygon[V <: Vertex[V, E], E <: Edge[E, V]]:

  //val vertices: ListBuffer[V]
  
  def isClockwise = false
  def isSimple = false
  def isConvex = false
  
  // Geometric queries.
  // def computeCenter: VectorN = null
  
  def computePerimeter = 0.0f
  
  def computeArea = 0.0f

