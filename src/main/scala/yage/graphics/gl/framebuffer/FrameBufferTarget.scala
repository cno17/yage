package yage.graphics.gl.framebuffer

import org.lwjgl.opengl.GL30C.GL_FRAMEBUFFER
import org.lwjgl.opengl.GL30C.GL_READ_FRAMEBUFFER
import org.lwjgl.opengl.GL30C.GL_DRAW_FRAMEBUFFER

enum FrameBufferTarget(val id: Int):
  case Read extends FrameBufferTarget(GL_READ_FRAMEBUFFER)
  case Draw extends FrameBufferTarget(GL_DRAW_FRAMEBUFFER)
  case ReadDraw extends FrameBufferTarget(GL_FRAMEBUFFER)
