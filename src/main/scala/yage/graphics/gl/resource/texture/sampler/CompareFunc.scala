package yage.graphics.gl.resource.texture.sampler

import org.lwjgl.opengl.GL11C.GL_ALWAYS
import org.lwjgl.opengl.GL11C.GL_EQUAL
import org.lwjgl.opengl.GL11C.GL_GEQUAL
import org.lwjgl.opengl.GL11C.GL_GREATER
import org.lwjgl.opengl.GL11C.GL_LEQUAL
import org.lwjgl.opengl.GL11C.GL_LESS
import org.lwjgl.opengl.GL11C.GL_NEVER
import org.lwjgl.opengl.GL11C.GL_NOTEQUAL

enum CompareFunc(val id: Int):

  case Never extends CompareFunc(GL_NEVER)
  case Always extends CompareFunc(GL_ALWAYS)
  case LT extends CompareFunc(GL_LESS)
  case LE extends CompareFunc(GL_LEQUAL)
  case EQ extends CompareFunc(GL_EQUAL)
  case NE extends CompareFunc(GL_NOTEQUAL)
  case GT extends CompareFunc(GL_GREATER)
  case GE extends CompareFunc(GL_GEQUAL)
