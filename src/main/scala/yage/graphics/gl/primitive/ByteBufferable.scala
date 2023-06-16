package yage.graphics.gl.primitive

import java.nio.ByteBuffer

trait ByteBufferable[T]:

  extension (buf: ByteBuffer)
     def put(t: T): Unit
