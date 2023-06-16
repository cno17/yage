package yage.graphics.gl.resource.texture.sampler

import org.lwjgl.opengl.GL11C.GL_TEXTURE_MAG_FILTER
import org.lwjgl.opengl.GL11C.GL_TEXTURE_MIN_FILTER
import org.lwjgl.opengl.GL11C.GL_TEXTURE_WRAP_S
import org.lwjgl.opengl.GL11C.GL_TEXTURE_WRAP_T
import org.lwjgl.opengl.GL12C.GL_TEXTURE_WRAP_R
import org.lwjgl.opengl.GL14C.GL_TEXTURE_COMPARE_FUNC
import org.lwjgl.opengl.GL14C.GL_TEXTURE_COMPARE_MODE
import org.lwjgl.opengl.GL33C.glDeleteSamplers
import org.lwjgl.opengl.GL33C.glGetSamplerParameteri
import org.lwjgl.opengl.GL33C.glSamplerParameteri
import org.lwjgl.opengl.GL45C.glCreateSamplers
import yage.graphics.gl.Object

class Sampler extends Object:

  override def create() = glCreateSamplers()

// should work!
  def minFilter = glGetSamplerParameteri(id, GL_TEXTURE_MIN_FILTER)
  def minFilter_=(f: MinFilter) = glSamplerParameteri(id, GL_TEXTURE_MIN_FILTER, f.id)
//  
  
  def setMinFilter(f: MinFilter) = glSamplerParameteri(id, GL_TEXTURE_MIN_FILTER, f.id)
  def setMagFilter(f: MagFilter) = glSamplerParameteri(id, GL_TEXTURE_MAG_FILTER, f.id)

  def setWrapModeU(m: WrapMode) = glSamplerParameteri(id, GL_TEXTURE_WRAP_S, m.id)
  def setWrapModeV(m: WrapMode) = glSamplerParameteri(id, GL_TEXTURE_WRAP_T, m.id)
  def setWrapModeW(m: WrapMode) = glSamplerParameteri(id, GL_TEXTURE_WRAP_R, m.id)

  def setCompareMode(m: CompareMode) = glSamplerParameteri(id, GL_TEXTURE_COMPARE_MODE, m.id)
  def setCompareFunc(f: CompareFunc) = glSamplerParameteri(id, GL_TEXTURE_COMPARE_FUNC, f.id)

  // TODO
  def setBorderColor(r: Double, g: Double, b: Double, a: Double) = 0
  def setMinLod(l: Double) = 0
  def setMaxLod(l: Double) = 0

  override def destroy() = glDeleteSamplers(id)
