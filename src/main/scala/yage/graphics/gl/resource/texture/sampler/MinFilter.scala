package yage.graphics.gl.resource.texture.sampler

import org.lwjgl.opengl.GL11C.GL_NEAREST
import org.lwjgl.opengl.GL11C.GL_NEAREST_MIPMAP_NEAREST
import org.lwjgl.opengl.GL11C.GL_NEAREST_MIPMAP_LINEAR
import org.lwjgl.opengl.GL11C.GL_LINEAR
import org.lwjgl.opengl.GL11C.GL_LINEAR_MIPMAP_NEAREST
import org.lwjgl.opengl.GL11C.GL_LINEAR_MIPMAP_LINEAR

enum MinFilter(val id: Int):

  case Nearest extends MinFilter(GL_NEAREST)
  case NearestMipmapNearest extends MinFilter(GL_NEAREST_MIPMAP_NEAREST)
  case NearestMipmapLinear extends MinFilter(GL_NEAREST_MIPMAP_LINEAR)
  case Linear extends MinFilter(GL_LINEAR)
  case LinearMipmapNearest extends MinFilter(GL_LINEAR_MIPMAP_NEAREST)
  case LinearMipmapLinear extends MinFilter(GL_LINEAR_MIPMAP_LINEAR)
