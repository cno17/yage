package yage.graphics.gl.shader.program.resource

// the imports are not checked!!!

import org.lwjgl.opengl.GL11C.GL_DOUBLE
import org.lwjgl.opengl.GL11C.GL_FLOAT
import org.lwjgl.opengl.GL11C.GL_INT
import org.lwjgl.opengl.GL11C.GL_UNSIGNED_INT
import org.lwjgl.opengl.GL20C.GL_BOOL
import org.lwjgl.opengl.GL20C.GL_BOOL_VEC2
import org.lwjgl.opengl.GL20C.GL_BOOL_VEC3
import org.lwjgl.opengl.GL20C.GL_BOOL_VEC4
import org.lwjgl.opengl.GL20C.GL_FLOAT_MAT2
import org.lwjgl.opengl.GL20C.GL_FLOAT_MAT3
import org.lwjgl.opengl.GL20C.GL_FLOAT_MAT4
import org.lwjgl.opengl.GL20C.GL_FLOAT_VEC2
import org.lwjgl.opengl.GL20C.GL_FLOAT_VEC3
import org.lwjgl.opengl.GL20C.GL_FLOAT_VEC4
import org.lwjgl.opengl.GL20C.GL_INT_VEC2
import org.lwjgl.opengl.GL20C.GL_INT_VEC3
import org.lwjgl.opengl.GL20C.GL_INT_VEC4
import org.lwjgl.opengl.GL21C.GL_FLOAT_MAT2x3
import org.lwjgl.opengl.GL21C.GL_FLOAT_MAT2x4
import org.lwjgl.opengl.GL21C.GL_FLOAT_MAT3x2
import org.lwjgl.opengl.GL21C.GL_FLOAT_MAT3x4
import org.lwjgl.opengl.GL21C.GL_FLOAT_MAT4x2
import org.lwjgl.opengl.GL21C.GL_FLOAT_MAT4x3
import org.lwjgl.opengl.GL30C.GL_UNSIGNED_INT_VEC2
import org.lwjgl.opengl.GL30C.GL_UNSIGNED_INT_VEC3
import org.lwjgl.opengl.GL30C.GL_UNSIGNED_INT_VEC4
import org.lwjgl.opengl.GL40C.GL_DOUBLE_MAT2
import org.lwjgl.opengl.GL40C.GL_DOUBLE_MAT2x3
import org.lwjgl.opengl.GL40C.GL_DOUBLE_MAT2x4
import org.lwjgl.opengl.GL40C.GL_DOUBLE_MAT3
import org.lwjgl.opengl.GL40C.GL_DOUBLE_MAT3x2
import org.lwjgl.opengl.GL40C.GL_DOUBLE_MAT3x4
import org.lwjgl.opengl.GL40C.GL_DOUBLE_MAT4
import org.lwjgl.opengl.GL40C.GL_DOUBLE_MAT4x2
import org.lwjgl.opengl.GL40C.GL_DOUBLE_MAT4x3
import org.lwjgl.opengl.GL40C.GL_DOUBLE_VEC2
import org.lwjgl.opengl.GL40C.GL_DOUBLE_VEC3
import org.lwjgl.opengl.GL40C.GL_DOUBLE_VEC4

object UniformType:

  def apply(v: Int) = values.find(_.value == v).get

enum UniformType(val value: Int):

    case B1 extends UniformType(GL_BOOL)
    case B2 extends UniformType(GL_BOOL_VEC2)
    case B3 extends UniformType(GL_BOOL_VEC3)
    case B4 extends UniformType(GL_BOOL_VEC4)

    case I1 extends UniformType(GL_INT)
    case I2 extends UniformType(GL_INT_VEC2)
    case I3 extends UniformType(GL_INT_VEC3)
    case I4 extends UniformType(GL_INT_VEC4)

    case U1 extends UniformType(GL_UNSIGNED_INT)
    case U2 extends UniformType(GL_UNSIGNED_INT_VEC2)
    case U3 extends UniformType(GL_UNSIGNED_INT_VEC3)
    case U4 extends UniformType(GL_UNSIGNED_INT_VEC4)

    case F1 extends UniformType(GL_FLOAT)
    case F2 extends UniformType(GL_FLOAT_VEC2)
    case F3 extends UniformType(GL_FLOAT_VEC3)
    case F4 extends UniformType(GL_FLOAT_VEC4)

    case D1 extends UniformType(GL_DOUBLE)
    case D2 extends UniformType(GL_DOUBLE_VEC2)
    case D3 extends UniformType(GL_DOUBLE_VEC3)
    case D4 extends UniformType(GL_DOUBLE_VEC4)

    case F2x2 extends UniformType(GL_FLOAT_MAT2)
    case F2x3 extends UniformType(GL_FLOAT_MAT2x3)
    case F2x4 extends UniformType(GL_FLOAT_MAT2x4)
    case F3x2 extends UniformType(GL_FLOAT_MAT3x2)
    case F3x3 extends UniformType(GL_FLOAT_MAT3)
    case F3x4 extends UniformType(GL_FLOAT_MAT3x4)
    case F4x2 extends UniformType(GL_FLOAT_MAT4x2)
    case F4x3 extends UniformType(GL_FLOAT_MAT4x3)
    case F4x4 extends UniformType(GL_FLOAT_MAT4)

    case D2x2 extends UniformType(GL_DOUBLE_MAT2)
    case D2x3 extends UniformType(GL_DOUBLE_MAT2x3)
    case D2x4 extends UniformType(GL_DOUBLE_MAT2x4)
    case D3x2 extends UniformType(GL_DOUBLE_MAT3x2)
    case D3x3 extends UniformType(GL_DOUBLE_MAT3)
    case D3x4 extends UniformType(GL_DOUBLE_MAT3x4)
    case D4x2 extends UniformType(GL_DOUBLE_MAT4x2)
    case D4x3 extends UniformType(GL_DOUBLE_MAT4x3)
    case D4x4 extends UniformType(GL_DOUBLE_MAT4)

    // TODO sampler types imageTypes counters
