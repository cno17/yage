package yage.graphics.gl.resource.texture

import org.lwjgl.opengl.GL45C.glCreateTextures
import org.lwjgl.opengl.GL45C.glTextureStorage2D
import org.lwjgl.opengl.GL45C.glTextureSubImage2D
import yage.graphics.gl.resource.Format

import java.awt.image.BufferedImage
import java.io.File
import java.nio.ByteBuffer

/**
 * ToDo write texture from pixel unpack buffer
 * BufferedImage loader from lwjgl/examples/spaceinvaders/TextureLoader.java Thanks!
 */

object Texture2 extends TextureLoader:

  def apply(format: Format, sizeI: Int, sizeJ: Int) =
    val tex = new Texture2()
    tex.allocate(1, format, sizeI, sizeJ)
    tex

  def apply(f: File) =
    loadTexture(f)

  def apply(i: BufferedImage) =
    loadTexture(i)

class Texture2 extends Texture:

  override def create() =
    glCreateTextures(TextureTarget.Dim2.value)

  def allocate(levels: Int, format: Format, sizeI: Int, sizeJ: Int) =
    glTextureStorage2D(id, levels, format.id, sizeI, sizeJ)

  def load(level: Int, format: Format, data: ByteBuffer) =
    glTextureSubImage2D(id, level, 0, 0, sizeI, sizeJ, format.layout, format.typ, data)

  // def load(level: Int, offI: Int, offJ: Int, extI: Int, extJ: Int, f: Format, data: ByteBuffer) =
  //   glTextureSubImage2D(id, level, offI, offJ, extI, extJ, f.externalFormat, f.componentType, data)
