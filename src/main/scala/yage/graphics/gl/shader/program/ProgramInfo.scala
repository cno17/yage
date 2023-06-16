package yage.graphics.gl.shader.program

import org.lwjgl.opengl.GL11C.glGetIntegerv
import org.lwjgl.opengl.GL41C.GL_NUM_PROGRAM_BINARY_FORMATS
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryStack.stackMallocInt
import org.lwjgl.system.MemoryStack.stackMallocInt
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.opengl.GL43C.*
import yage.graphics.gl.shader.program.resource.UniformVariable
import yage.graphics.gl.shader.program.resource.UniformBlock
import yage.graphics.gl.shader.program.resource.UniformType

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer



import java.nio.IntBuffer
import scala.language.postfixOps

trait ProgramInfo:

  this: Program =>

  def uniformVariables =
    val res = ArrayBuffer[UniformVariable]()
    val num = glGetProgramInterfacei(id, GL_UNIFORM, GL_ACTIVE_RESOURCES)
    for i <- 0 to num - 1 do
      val u = UniformVariable()
      u.index = i
      u.name = glGetProgramResourceName(id, GL_UNIFORM, i)
      u.blockIndex = propertyValue(GL_UNIFORM, i, GL_BLOCK_INDEX)
      u.typ = UniformType(propertyValue(GL_UNIFORM, i, GL_TYPE))
      u.location = propertyValue(GL_UNIFORM, i, GL_LOCATION)
      u.offset = propertyValue(GL_UNIFORM, i, GL_OFFSET)
      u.arraySize = propertyValue(GL_UNIFORM, i, GL_ARRAY_SIZE)
      u.arrayStride = propertyValue(GL_UNIFORM, i, GL_ARRAY_STRIDE)
      u.matrixStride = propertyValue(GL_UNIFORM, i, GL_MATRIX_STRIDE)
      res += u
    res.toArray

  def uniformBlocks =
    val res = ArrayBuffer[UniformBlock]()
    val num = glGetProgramInterfacei(id, GL_UNIFORM_BLOCK, GL_ACTIVE_RESOURCES)
    for i <- 0 to num - 1 do
      val b = UniformBlock()
      b.index = i
      b.name = glGetProgramResourceName(id, GL_UNIFORM_BLOCK, i)
      b.bufferBinding = propertyValue(GL_UNIFORM_BLOCK, i, GL_BUFFER_BINDING)
      b.bufferSize = propertyValue(GL_UNIFORM_BLOCK, i, GL_BUFFER_DATA_SIZE)
      b.members = uniformVariables.filter(_.blockIndex == b.index)
      res += b
    res.toArray

  private def propertyValue(programInterface: Int, resourceIndex: Int, propertyName: Int) =
    var value = 0
    useStack(s =>
      val pName = s.ints(propertyName)
      val pValue = s.ints(0)
      glGetProgramResourceiv(id, programInterface, resourceIndex, pName, null, pValue)
      value = pValue.get(0)
    )
    value
