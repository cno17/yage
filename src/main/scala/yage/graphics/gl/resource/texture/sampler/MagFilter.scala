package yage.graphics.gl.resource.texture.sampler;

import org.lwjgl.opengl.GL11C.GL_LINEAR
import org.lwjgl.opengl.GL11C.GL_NEAREST

enum MagFilter(val id: Int):

  case Nearest extends MagFilter(GL_NEAREST)
  case Linear extends MagFilter(GL_LINEAR)
