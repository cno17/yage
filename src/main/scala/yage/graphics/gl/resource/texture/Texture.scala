package yage.graphics.gl.resource.texture

import org.lwjgl.opengl.GL11C.glDeleteTextures
import org.lwjgl.opengl.GL13C.GL_TEXTURE0
import org.lwjgl.opengl.GL42C.glBindImageTexture
import org.lwjgl.opengl.GL45C.glBindTextureUnit
import org.lwjgl.opengl.GL45C.glGenerateTextureMipmap
import yage.graphics.gl.resource.Access
import yage.graphics.gl.resource.Format
import yage.graphics.gl.resource.Resource

import org.lwjgl.opengl.GL11C.GL_TEXTURE_MAG_FILTER
import org.lwjgl.opengl.GL11C.GL_TEXTURE_MIN_FILTER
import org.lwjgl.opengl.GL11C.GL_TEXTURE_WRAP_S
import org.lwjgl.opengl.GL11C.GL_TEXTURE_WRAP_T
import org.lwjgl.opengl.GL12C.GL_TEXTURE_WRAP_R
import org.lwjgl.opengl.GL45C.glTextureParameteri
import yage.graphics.gl.resource.texture.sampler.MagFilter
import yage.graphics.gl.resource.texture.sampler.MinFilter
import yage.graphics.gl.resource.texture.sampler.WrapMode


abstract class Texture extends Resource, TextureInfo:

  def bindToTextureUnit(unit: Int) =
    glBindTextureUnit(unit, id)

  def bindToImageUnit(unit: Int, a: Access, f: Format) =
    glBindImageTexture(unit, id, 0, true, 0, a.value, f.id)

  // bind level
  def bindToImageUnit(unit: Int, level: Int, a: Access, f: Format) =
    glBindImageTexture(unit, id, level, true, 0, a.value, f.id)

  // bind layer of level
  def bindToImageUnit(unit: Int, level: Int, layer: Int, a: Access, f: Format) =
    glBindImageTexture(unit, id, level, false, layer, a.value, f.id)
  
  def createMipMap() =
    glGenerateTextureMipmap(id)

  override def destroy() = glDeleteTextures(id)

  // embedded sampler stuff

  def setFilter(min: MinFilter, mag: MagFilter) = 
    glTextureParameteri(id, GL_TEXTURE_MIN_FILTER, min.id)
    glTextureParameteri(id, GL_TEXTURE_MAG_FILTER, mag.id)

  def setMinFilter(f: MinFilter) = 
    glTextureParameteri(id, GL_TEXTURE_MIN_FILTER, f.id)
  
  def setMagFilter(f: MagFilter) = 
    glTextureParameteri(id, GL_TEXTURE_MAG_FILTER, f.id)

  def setWrapModeU(m: WrapMode) = glTextureParameteri(id, GL_TEXTURE_WRAP_S, m.id)
  def setWrapModeV(m: WrapMode) = glTextureParameteri(id, GL_TEXTURE_WRAP_T, m.id)
  def setWrapModeW(m: WrapMode) = glTextureParameteri(id, GL_TEXTURE_WRAP_R, m.id)

  // more, look at sampler
