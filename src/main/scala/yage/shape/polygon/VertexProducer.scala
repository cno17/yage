package yage.shape.polygon

/**
 * apply with traits?
 */

trait VertexProducer[V]:
  
  def createVertex: V
