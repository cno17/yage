package yage.graphics.gl.primitive

import org.lwjgl.opengl.GL11C.GL_BYTE
import org.lwjgl.opengl.GL11C.GL_DOUBLE
import org.lwjgl.opengl.GL11C.GL_FLOAT
import org.lwjgl.opengl.GL11C.GL_INT
import org.lwjgl.opengl.GL11C.GL_SHORT
import org.lwjgl.opengl.GL11C.GL_UNSIGNED_BYTE
import org.lwjgl.opengl.GL11C.GL_UNSIGNED_INT
import org.lwjgl.opengl.GL11C.GL_UNSIGNED_SHORT

enum ComponentType(val value: Int, val numBytes: Int):
  case U08 extends ComponentType(GL_UNSIGNED_BYTE, 1)
  case U16 extends ComponentType(GL_UNSIGNED_SHORT, 2)
  case U32 extends ComponentType(GL_UNSIGNED_INT, 4)
  case I08 extends ComponentType(GL_BYTE, 1)
  case I16 extends ComponentType(GL_SHORT, 2)
  case I32 extends ComponentType(GL_INT, 4)
  // case F16 extends ComponentType(GL_HALF, 2)
  case F32 extends ComponentType(GL_FLOAT, 4)
  case F64 extends ComponentType(GL_DOUBLE, 8)
