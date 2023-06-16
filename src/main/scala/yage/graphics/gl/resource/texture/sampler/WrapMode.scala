package yage.graphics.gl.resource.texture.sampler

import org.lwjgl.opengl.GL11C.GL_REPEAT
import org.lwjgl.opengl.GL12C.GL_CLAMP_TO_EDGE
import org.lwjgl.opengl.GL13C.GL_CLAMP_TO_BORDER
import org.lwjgl.opengl.GL14C.GL_MIRRORED_REPEAT
import org.lwjgl.opengl.GL44C.GL_MIRROR_CLAMP_TO_EDGE

enum WrapMode(val id: Int):

  case ClampToEdge extends WrapMode(GL_CLAMP_TO_EDGE)
  case ClampToBorder extends WrapMode(GL_CLAMP_TO_BORDER)
  case MirroredRepeat extends WrapMode(GL_MIRRORED_REPEAT)
  case Repeat extends WrapMode(GL_REPEAT)
  case MirroredClampToEdge extends WrapMode(GL_MIRROR_CLAMP_TO_EDGE)
