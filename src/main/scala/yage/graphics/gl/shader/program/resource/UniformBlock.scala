package yage.graphics.gl.shader.program.resource

class UniformBlock extends Block:
  
  var members: Array[UniformVariable] = null

  override def toString =
    var s = super.toString
    for m <- members do
      s += s"    ${m.name}: ${m.typ}\n" 
    s
