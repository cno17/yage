package yage.graphics.gl.primitive

// GL_INT?

enum IndexType(val clazz: Class[_]):
  case Byte extends IndexType(scala.Byte.getClass)
  case Short extends IndexType(scala.Short.getClass)
  case Int extends IndexType(scala.Int.getClass)
