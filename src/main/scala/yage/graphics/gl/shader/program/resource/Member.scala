package yage.graphics.gl.shader.program.resource

// needed?

class Member extends Resource:

  var blockIndex = 0
  var offset = 0
  var arraySize = 0
  var arrayStride = 0
  var matrixStride = 0

  override def toString =
    var s = ""
    s += s"name: $name\n"
    s += s"index = $index\n"
    s += s"blockIndex: $blockIndex\n"
    s += s"offset: $offset\n"
    s += s"arraySize = $arraySize\n"
    s += s"arrayStride = $arrayStride\n"
    s += s"matrixStride = $matrixStride\n"
    s

