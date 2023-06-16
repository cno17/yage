package yage.graphics.gl.shader.program.interface

import org.lwjgl.opengl.GL43C.GL_BUFFER_VARIABLE
import org.lwjgl.opengl.GL43C.GL_PROGRAM_INPUT
import org.lwjgl.opengl.GL43C.GL_PROGRAM_OUTPUT
import org.lwjgl.opengl.GL43C.GL_SHADER_STORAGE_BLOCK
import org.lwjgl.opengl.GL43C.GL_UNIFORM
import org.lwjgl.opengl.GL43C.GL_UNIFORM_BLOCK

enum ProgramInterfaceType(val id: Int):

  case Input extends ProgramInterfaceType(GL_PROGRAM_INPUT)
  case Output extends ProgramInterfaceType(GL_PROGRAM_OUTPUT)
  case Uniform extends ProgramInterfaceType(GL_UNIFORM)
  case UniformBlock extends ProgramInterfaceType(GL_UNIFORM_BLOCK)
  case Storage extends ProgramInterfaceType(GL_BUFFER_VARIABLE)
  case StorageBlock extends ProgramInterfaceType(GL_SHADER_STORAGE_BLOCK)
  // case Counter extends ProgramInterfaceType(GL_ATOMIC_COUNTER_BUFFER_INDEX)
