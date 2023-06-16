package yage.graphics.gl.primitive

import org.lwjgl.opengl.GL11C.*

// matrices allowed?

enum AttributeFormat(val componentType: Int, val componentCount: Int, val byteCount: Int):

  case U08_1 extends AttributeFormat(GL_UNSIGNED_BYTE, 1, 1)
  case U08_2 extends AttributeFormat(GL_UNSIGNED_BYTE, 2, 2)
  case U08_3 extends AttributeFormat(GL_UNSIGNED_BYTE, 3, 3)
  case U08_4 extends AttributeFormat(GL_UNSIGNED_BYTE, 4, 4)
  case U16_1 extends AttributeFormat(GL_UNSIGNED_SHORT, 1, 2)
  case U16_2 extends AttributeFormat(GL_UNSIGNED_SHORT, 2, 4)
  case U16_3 extends AttributeFormat(GL_UNSIGNED_SHORT, 3, 6)
  case U16_4 extends AttributeFormat(GL_UNSIGNED_SHORT, 4, 8)
  case U32_1 extends AttributeFormat(GL_UNSIGNED_INT, 1, 4)
  case U32_2 extends AttributeFormat(GL_UNSIGNED_INT, 2, 8)
  case U32_3 extends AttributeFormat(GL_UNSIGNED_INT, 3, 12)
  case U32_4 extends AttributeFormat(GL_UNSIGNED_INT, 4, 16)
  case I08_1 extends AttributeFormat(GL_BYTE, 1, 1)
  case I08_2 extends AttributeFormat(GL_BYTE, 2, 2)
  case I08_3 extends AttributeFormat(GL_BYTE, 3, 3)
  case I08_4 extends AttributeFormat(GL_BYTE, 4, 4)
  case I16_1 extends AttributeFormat(GL_SHORT, 1, 2)
  case I16_2 extends AttributeFormat(GL_SHORT, 2, 4)
  case I16_3 extends AttributeFormat(GL_SHORT, 3, 6)
  case I16_4 extends AttributeFormat(GL_SHORT, 4, 8)
  case I32_1 extends AttributeFormat(GL_INT, 1, 4)
  case I32_2 extends AttributeFormat(GL_INT, 2, 8)
  case I32_3 extends AttributeFormat(GL_INT, 3, 12)
  case I32_4 extends AttributeFormat(GL_INT, 4, 16)
  case F32_1 extends AttributeFormat(GL_FLOAT, 1, 4)
  case F32_2 extends AttributeFormat(GL_FLOAT, 2, 8)
  case F32_3 extends AttributeFormat(GL_FLOAT, 3, 12)
  case F32_4 extends AttributeFormat(GL_FLOAT, 4, 16)
  case F64_1 extends AttributeFormat(GL_DOUBLE, 1, 8)
  case F64_2 extends AttributeFormat(GL_DOUBLE, 2, 16)
  case F64_3 extends AttributeFormat(GL_DOUBLE, 3, 24)
  case F64_4 extends AttributeFormat(GL_DOUBLE, 4, 32)
  
