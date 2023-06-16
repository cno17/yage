package yage.graphics.gl.framebuffer

import org.lwjgl.opengl.GL30C.*

// FrameBufferSlot, FrameBufferAttachmentPoint

enum FrameBufferAttachment(val id: Int):

  case Color0 extends FrameBufferAttachment(GL_COLOR_ATTACHMENT0)
  case Color1 extends FrameBufferAttachment(GL_COLOR_ATTACHMENT1)
  case Color2 extends FrameBufferAttachment(GL_COLOR_ATTACHMENT2)
  case Color3 extends FrameBufferAttachment(GL_COLOR_ATTACHMENT3)
  case Color4 extends FrameBufferAttachment(GL_COLOR_ATTACHMENT4)
  case Color5 extends FrameBufferAttachment(GL_COLOR_ATTACHMENT5)
  case Color6 extends FrameBufferAttachment(GL_COLOR_ATTACHMENT6)
  case Color7 extends FrameBufferAttachment(GL_COLOR_ATTACHMENT7)
  case Depth extends FrameBufferAttachment(GL_DEPTH_ATTACHMENT)
  case Stencil extends FrameBufferAttachment(GL_STENCIL_ATTACHMENT)
  case DepthStencil extends FrameBufferAttachment(GL_DEPTH_STENCIL_ATTACHMENT)
