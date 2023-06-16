package yage.graphics.gl.framebuffer

import org.lwjgl.opengl.GL30C.glBindFramebuffer
import org.lwjgl.opengl.GL30C.glDeleteFramebuffers
import org.lwjgl.opengl.GL30C.GL_FRAMEBUFFER_COMPLETE
import org.lwjgl.opengl.GL45C.glCheckNamedFramebufferStatus
import org.lwjgl.opengl.GL45C.glCreateFramebuffers
import org.lwjgl.opengl.GL45C.glNamedFramebufferTexture
import org.lwjgl.opengl.GL45C.glNamedFramebufferTextureLayer
import yage.graphics.gl.resource.texture.Texture
import yage.graphics.gl.Object

class FrameBuffer extends Object:

  override def create() = glCreateFramebuffers()

  def bindTo(target: FrameBufferTarget) =
    glBindFramebuffer(target.id, id)

  def attach(texture: Texture, level: Int, attachment: FrameBufferAttachment) =
    glNamedFramebufferTexture(id, attachment.id, texture.id, level)

  def attach(texture: Texture, level: Int, layer: Int, attachment: FrameBufferAttachment) =
    glNamedFramebufferTextureLayer(id, attachment.id, texture.id, level, layer)

  def isCompleteFor(target: FrameBufferTarget) =
    glCheckNamedFramebufferStatus(id, target.id) == GL_FRAMEBUFFER_COMPLETE

  override def destroy() = glDeleteFramebuffers(id)
