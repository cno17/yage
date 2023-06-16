package yage.graphics.gl.resource.texture

import org.lwjgl.opengl.GL11C.GL_TEXTURE_1D
import org.lwjgl.opengl.GL11C.GL_TEXTURE_2D
import org.lwjgl.opengl.GL12C.GL_TEXTURE_3D
import org.lwjgl.opengl.GL13C.GL_TEXTURE_CUBE_MAP
import org.lwjgl.opengl.GL30C.GL_TEXTURE_1D_ARRAY
import org.lwjgl.opengl.GL30C.GL_TEXTURE_2D_ARRAY
import org.lwjgl.opengl.GL31C.GL_TEXTURE_BUFFER
import org.lwjgl.opengl.GL32C.GL_TEXTURE_2D_MULTISAMPLE
import org.lwjgl.opengl.GL32C.GL_TEXTURE_2D_MULTISAMPLE_ARRAY
import org.lwjgl.opengl.GL40C.GL_TEXTURE_CUBE_MAP_ARRAY

// TextureType?

enum TextureTarget(val value: Int):

  case Dim1 extends TextureTarget(GL_TEXTURE_1D)
  case Dim1Array extends TextureTarget(GL_TEXTURE_1D_ARRAY)
  case Dim2 extends TextureTarget(GL_TEXTURE_2D)
  case Dim2Array extends TextureTarget(GL_TEXTURE_2D_ARRAY)
  case Dim3 extends TextureTarget(GL_TEXTURE_3D)
  case Cube extends TextureTarget(GL_TEXTURE_CUBE_MAP)
  case CubeArray extends TextureTarget(GL_TEXTURE_CUBE_MAP_ARRAY)
  case Buffer extends TextureTarget(GL_TEXTURE_BUFFER)
