package yage.graphics.gl.primitive

class VertexDescriptor(val attributeCount: Int):

  val attributeDescriptors = new Array[AttributeDescriptor](attributeCount)

  def byteCount = attributeDescriptors.map(ad => ad.format.byteCount).sum
