package yage.graphics.gl.resource.texture

import java.nio.ByteBuffer
import org.lwjgl.opengl.GL45C.glCreateTextures
import org.lwjgl.opengl.GL45C.glTextureStorage1D
import org.lwjgl.opengl.GL45C.glTextureSubImage1D
import yage.graphics.gl.resource.Format

class Texture1 extends Texture:

  override def create() =
    glCreateTextures(TextureTarget.Dim1.value)

  def allocate(nl: Int, f: Format, si: Int) =
    glTextureStorage1D(id, nl, f.id, si)

  def load(level: Int, format: Format, data: ByteBuffer) =
    glTextureSubImage1D(id, level, 0, sizeI, format.layout, format.typ, data)

  def load(level: Int, offI: Int, lenI: Int, format: Format, data: ByteBuffer) =
    glTextureSubImage1D(id, level, offI, lenI, format.layout, format.typ, data)
