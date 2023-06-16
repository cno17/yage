package yage.graphics.gl.resource

import org.lwjgl.opengl.GL15C.GL_READ_ONLY
import org.lwjgl.opengl.GL15C.GL_READ_WRITE
import org.lwjgl.opengl.GL15C.GL_WRITE_ONLY

// AccessMode?

enum Access(val value: Int):
  
  case ReadOnly extends Access(GL_READ_ONLY)
  case WriteOnly extends Access(GL_WRITE_ONLY)
  case ReadWrite extends Access(GL_READ_WRITE)
