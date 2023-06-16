package yage.graphics.gl.resource.texture

import org.lwjgl.opengl.GL11C.GL_TEXTURE_HEIGHT
import org.lwjgl.opengl.GL11C.GL_TEXTURE_WIDTH
import org.lwjgl.opengl.GL12C.GL_TEXTURE_DEPTH
import org.lwjgl.opengl.GL45C.glGetTextureLevelParameteri

trait TextureInfo:

  this: Texture =>

  def sizeI: Int = sizeI(0)
  def sizeJ: Int = sizeJ(0)
  def sizeK: Int = sizeK(0)

  def sizeI(level: Int) = glGetTextureLevelParameteri(id, level, GL_TEXTURE_WIDTH)
  def sizeJ(level: Int) = glGetTextureLevelParameteri(id, level, GL_TEXTURE_HEIGHT)
  def sizeK(level: Int) = glGetTextureLevelParameteri(id, level, GL_TEXTURE_DEPTH)
