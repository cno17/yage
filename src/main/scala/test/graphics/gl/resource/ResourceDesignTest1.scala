package test.graphics.gl.resource

import java.nio.ByteBuffer

private object ResourceDesignTest1:

  class Offset
  class Offset1(var offI: Int) extends Offset
  class Offset2(var offI: Int, var offJ: Int) extends Offset

  class Extent
  class Extent1(var extI: Int) extends Extent
  class Extent2(var extI: Int, var extJ: Int) extends Extent

  class Range[O <: Offset, E <: Extent](var off: O, var ext: E)
  class Range1(off: Offset1, ext: Extent1) extends Range(off, ext)
  class Range2(off: Offset2, ext: Extent2) extends Range(off, ext)
  
  trait Texture[O <: Offset, E <: Extent, R <: Range[O, E]]:
    def allocate(ext: E): Unit
    def load(off: O, ext: E, data: ByteBuffer): Unit

  def main(args: Array[String]) =
    println(2)