package yage.graphics.gl.shader.program.resource

/**
 * Either a uniform block or a storage block or a counter block.
 * 
 * bufferBinding: the index of the buffer binding point associated with this block
 * bufferSize: the minimum size of the associated buffer in bytes
 */


class Block extends Resource:

  var bufferBinding = 0
  var bufferSize = 0

  override def toString =
    var s = super.toString
    s += s"bufferBinding: $bufferBinding\n"
    s += s"bufferSize: $bufferSize\n"
    s


  /*	
  override def toString: String = {
  val buf = new StringBuffer
  buf.append(name + " (" + index + ")\n")
  buf.append("bufferBinding = " + bufferBinding + "\n")
  buf.append("bufferSize = " + bufferSize + "\n")
  buf.append("members: ")
  var i = 0
  while ( {
  i < members.size
  }) {
  val u = members.get(i)
  buf.append(u.name + ": " + u.`type`)
  if (i < members.size - 1) buf.append(", ")
  else buf.append("\n")

  i += 1
  }
  buf.toString
  }
  }
  */