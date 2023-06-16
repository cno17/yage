package yage.graphics.gl.resource

import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL31C.*

import yage.graphics.gl.resource.Format

// Unification of attribute format and pixel/texel format.
// UN = unsigned normalized fixed point (only used as internal texel format)
// Todo: depth/stencil formats

// GL_RGBA for texture loading?

/**
 * UI = unsigned int
 * SI = signed int
 * UN = unsigned normalized
 * SN = signed normalized
 * FP = floating point
 */

enum Format(val id: Int, val layout: Int, val typ: Int):

  case UI08_1 extends Format(GL_R8UI, GL_RED, GL_UNSIGNED_BYTE)
  case UI08_2 extends Format(GL_RG8UI, GL_RG, GL_UNSIGNED_BYTE)
  case UI08_3 extends Format(GL_RGB8UI, GL_RGB, GL_UNSIGNED_BYTE)
  case UI08_4 extends Format(GL_RGBA8UI, GL_RGBA, GL_UNSIGNED_BYTE)
  case UI16_1 extends Format(GL_R16UI, GL_RED, GL_UNSIGNED_SHORT)
  case UI16_2 extends Format(GL_RG16UI, GL_RG, GL_UNSIGNED_SHORT)
  case UI16_3 extends Format(GL_RGB16UI, GL_RGB, GL_UNSIGNED_SHORT)
  case UI16_4 extends Format(GL_RGBA16UI, GL_RGBA, GL_UNSIGNED_SHORT)
  case UI32_1 extends Format(GL_R32UI, GL_RED, GL_UNSIGNED_INT)
  case UI32_2 extends Format(GL_RG32UI, GL_RG, GL_UNSIGNED_INT)
  case UI32_3 extends Format(GL_RGB32UI, GL_RGB, GL_UNSIGNED_INT)
  case UI32_4 extends Format(GL_RGBA32UI, GL_RGBA, GL_UNSIGNED_INT)

  case SI08_1 extends Format(GL_R8I, GL_RED, GL_BYTE)
  case SI08_2 extends Format(GL_RG8I, GL_RG, GL_BYTE)
  case SI08_3 extends Format(GL_RGB8I, GL_RGB, GL_BYTE)
  case SI08_4 extends Format(GL_RGBA8I, GL_RGBA, GL_BYTE)
  case SI16_1 extends Format(GL_R16I, GL_RED, GL_SHORT)
  case SI16_2 extends Format(GL_RG16I, GL_RG, GL_SHORT)
  case SI16_3 extends Format(GL_RGB16I, GL_RGB, GL_SHORT)
  case SI16_4 extends Format(GL_RGBA16I, GL_RGBA, GL_SHORT)
  case SI32_1 extends Format(GL_R32I, GL_RED, GL_INT)
  case SI32_2 extends Format(GL_RG32I, GL_RG, GL_INT)
  case SI32_3 extends Format(GL_RGB32I, GL_RGB, GL_INT)
  case SI32_4 extends Format(GL_RGBA32I, GL_RGBA, GL_INT)

  case UN08_1 extends Format(GL_R8, GL_RED, 0)
  case UN08_2 extends Format(GL_RG8, GL_RG, 0)
  case UN08_3 extends Format(GL_RGB8, GL_RGB, 0)
  case UN08_4 extends Format(GL_RGBA8, GL_RGBA, 0)
  case UN16_1 extends Format(GL_R16, GL_RED, 0)
  case UN16_2 extends Format(GL_RG16, GL_RG, 0)
  case UN16_3 extends Format(GL_RGB16, GL_RGB, 0)
  case UN16_4 extends Format(GL_RGBA16, GL_RGBA, 0)

  case SN08_1 extends Format(GL_R8_SNORM, GL_RED, 0)
  case SN08_2 extends Format(GL_RG8_SNORM, GL_RG, 0)
  case SN08_3 extends Format(GL_RGB8_SNORM, GL_RGB, 0)
  case SN08_4 extends Format(GL_RGBA8_SNORM, GL_RGBA, 0)
  case SN16_1 extends Format(GL_R16_SNORM, GL_RED, 0)
  case SN16_2 extends Format(GL_RG16_SNORM, GL_RG, 0)
  case SN16_3 extends Format(GL_RGB16_SNORM, GL_RGB, 0)
  case SN16_4 extends Format(GL_RGBA16_SNORM, GL_RGBA, 0)

  case FP16_1 extends Format(GL_R16F, GL_RED, GL_HALF_FLOAT)
  case FP16_2 extends Format(GL_RG16F, GL_RG, GL_HALF_FLOAT)
  case FP16_3 extends Format(GL_RGB16F, GL_RGB, GL_HALF_FLOAT)
  case FP16_4 extends Format(GL_RGBA16F, GL_RGBA, GL_HALF_FLOAT)
  case FP32_1 extends Format(GL_R32F, GL_RED, GL_FLOAT)
  case FP32_2 extends Format(GL_RG32F, GL_RG, GL_FLOAT)
  case FP32_3 extends Format(GL_RGB32F, GL_RGB, GL_FLOAT)
  case FP32_4 extends Format(GL_RGBA32F, GL_RGBA, GL_FLOAT)


  /*


enum Format(
    val internalFormat: Int,
    val componentLayout: Int,
    val componentType: Int,
    val componentCount: Int,
    val byteCount: Int
):

  case UI08_1 extends Format(GL_R8UI, GL_RED, GL_UNSIGNED_BYTE, 1, 1)
  case UI08_2 extends Format(GL_RG8UI, GL_RG, GL_UNSIGNED_BYTE, 2, 2)
  case UI08_3 extends Format(GL_RGB8UI, GL_RGB, GL_UNSIGNED_BYTE, 3, 3)
  case UI08_4 extends Format(GL_RGBA8UI, GL_RGBA, GL_UNSIGNED_BYTE, 4, 4)
  case UI16_1 extends Format(GL_R16UI, GL_RED, GL_UNSIGNED_SHORT, 1, 2)
  case UI16_2 extends Format(GL_RG16UI, GL_RG, GL_UNSIGNED_SHORT, 2, 4)
  case UI16_3 extends Format(GL_RGB16UI, GL_RGB, GL_UNSIGNED_SHORT, 3, 6)
  case UI16_4 extends Format(GL_RGBA16UI, GL_RGBA, GL_UNSIGNED_SHORT, 4, 8)
  case UI32_1 extends Format(GL_R32UI, GL_RED, GL_UNSIGNED_INT, 1, 4)
  case UI32_2 extends Format(GL_RG32UI, GL_RG, GL_UNSIGNED_INT, 2, 8)
  case UI32_3 extends Format(GL_RGB32UI, GL_RGB, GL_UNSIGNED_INT, 3, 12)
  case UI32_4 extends Format(GL_RGBA32UI, GL_RGBA, GL_UNSIGNED_INT, 4, 16)

  case SI08_1 extends Format(GL_R8I, GL_RED, GL_BYTE, 1, 1)
  case SI08_2 extends Format(GL_RG8I, GL_RG, GL_BYTE, 2, 2)
  case SI08_3 extends Format(GL_RGB8I, GL_RGB, GL_BYTE, 3, 3)
  case SI08_4 extends Format(GL_RGBA8I, GL_RGBA, GL_BYTE, 4, 4)
  case SI16_1 extends Format(GL_R16I, GL_RED, GL_SHORT, 1, 2)
  case SI16_2 extends Format(GL_RG16I, GL_RG, GL_SHORT, 2, 4)
  case SI16_3 extends Format(GL_RGB16I, GL_RGB, GL_SHORT, 3, 6)
  case SI16_4 extends Format(GL_RGBA16I, GL_RGBA, GL_SHORT, 4, 8)
  case SI32_1 extends Format(GL_R32I, GL_RED, GL_INT, 1, 4)
  case SI32_2 extends Format(GL_RG32I, GL_RG, GL_INT, 2, 8)
  case SI32_3 extends Format(GL_RGB32I, GL_RGB, GL_INT, 3, 12)
  case SI32_4 extends Format(GL_RGBA32I, GL_RGBA, GL_INT, 4, 16)

  // not needed?
  case UN08_1 extends Format(GL_R8, 0, 0, 0, 0)
  case UN08_2 extends Format(GL_RG8, 0, 0, 0, 0)
  case UN08_3 extends Format(GL_RGB8, 0, 0, 0, 0)
  case UN08_4 extends Format(GL_RGBA8, 0, 0, 0, 0)
  case UN16_1 extends Format(GL_R16, 0, 0, 0, 0)
  case UN16_2 extends Format(GL_RG16, 0, 0, 0, 0)
  case UN16_3 extends Format(GL_RGB16, 0, 0, 0, 0)
  case UN16_4 extends Format(GL_RGBA16, 0, 0, 0, 0)

  case SN08_1 extends Format(GL_R8_SNORM, 0, 0, 0, 0)
  case SN08_2 extends Format(GL_RG8_SNORM, 0, 0, 0, 0)
  case SN08_3 extends Format(GL_RGB8_SNORM, 0, 0, 0, 0)
  case SN08_4 extends Format(GL_RGBA8_SNORM, 0, 0, 0, 0)
  case SN16_1 extends Format(GL_R16_SNORM, 0, 0, 0, 0)
  case SN16_2 extends Format(GL_RG16_SNORM, 0, 0, 0, 0)
  case SN16_3 extends Format(GL_RGB16_SNORM, 0, 0, 0, 0)
  case SN16_4 extends Format(GL_RGBA16_SNORM, 0, 0, 0, 0)

  case FP16_1 extends Format(GL_R16F, GL_RED, GL_HALF_FLOAT, 1, 2)
  case FP16_2 extends Format(GL_RG16F, GL_RG, GL_HALF_FLOAT, 2, 4)
  case FP16_3 extends Format(GL_RGB16F, GL_RGB, GL_HALF_FLOAT, 3, 6)
  case FP16_4 extends Format(GL_RGBA16F, GL_RGBA, GL_HALF_FLOAT, 4, 8)
  case FP32_1 extends Format(GL_R32F, GL_RED, GL_FLOAT, 1, 4)
  case FP32_2 extends Format(GL_RG32F, GL_RG, GL_FLOAT, 2, 8)
  case FP32_3 extends Format(GL_RGB32F, GL_RGB, GL_FLOAT, 3, 12)
  case FP32_4 extends Format(GL_RGBA32F, GL_RGBA, GL_FLOAT, 4, 16)


  */