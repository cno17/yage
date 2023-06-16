package yage.graphics.gl.shader.program.resource

/**
 * A uniform variable.
 *
 * location: -1 for members of a uniform block, the location of this uniform as specified by a layout qualifier or assigned by the linker
 * blockIndex: -1 for members of the default uniform block
 * offset: the offset of this uniform in the uniform buffer or -1 for members of the default uniform block
 * arraySize: 1 for non-array uniforms
 * arrayStride: -1 for uniforms not backed by a buffer, 0 for non-array uniforms,
 * stride between consecutive array elements else
 * matrixStride: the stride between the columns of a matrix
 */

class UniformVariable extends Variable:

  var blockIndex = 0
  var typ = UniformType.B1 
  var location = 0
  var offset = 0
  var arraySize = 0
  var arrayStride = 0
  var matrixStride = 0

  override def toString =
    var s = super.toString()
    s += s"blockIndex: $blockIndex\n"
    s += s"type: $typ\n"
    s += s"location: $location\n"
    s += s"offset: $offset\n"
    s += s"arraySize = $arraySize\n"
    s += s"arrayStride = $arrayStride\n"
    s += s"matrixStride = $matrixStride\n"
    s
