package test.graphics.gl.reflection

// import yage.graphics.gl.shader.program.ProgramFactory

import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.ByteBuffer
import org.lwjgl.opengl.GL20C.*
import org.lwjgl.opengl.GL43C.*
import org.lwjgl.BufferUtils
import yage.graphics.fw.App
import yage.graphics.gl.shader.program.interface.ProgramInterfaceType
import yage.graphics.gl.shader.program.Program
import yage.graphics.gl.shader.Shader
import yage.graphics.gl.shader.ShaderStage

object UniformBlockTest1 extends App:

  override def init() =
    val d = File("src/main/scala/test/graphics/gl/reflection")
    val p = Program(File(d, "UniformBlockTest1.vert"))
    p.uniformVariables.foreach(println(_))
    p.uniformBlocks.foreach(println(_))
    close()

  def printUniformInfo(p: Program) =
    val pit = ProgramInterfaceType.Uniform
    val pPropName = BufferUtils.createIntBuffer(1)
    val pPropValue = BufferUtils.createIntBuffer(1)
    val n = glGetProgramInterfacei(p.id, pit.id, GL_ACTIVE_RESOURCES)
    for i <- 0 to n - 1 do
      val name = glGetProgramResourceName(p.id, pit.id, i)
      pPropName.put(0, GL_BLOCK_INDEX)
      glGetProgramResourceiv(p.id, pit.id, i, pPropName, null, pPropValue)
      println(s"$name: ${pPropValue.get(0)}")

  def printUniformBlockInfo(p: Program) =
    val pit = ProgramInterfaceType.UniformBlock
    val pPropName = BufferUtils.createIntBuffer(1)
    val pPropValue = BufferUtils.createIntBuffer(1)
    pPropName.put(0, GL_NUM_ACTIVE_VARIABLES)
    val n = glGetProgramInterfacei(p.id, pit.id, GL_ACTIVE_RESOURCES)
    for i <- 0 to n - 1 do
      val name = glGetProgramResourceName(p.id, pit.id, i)
      glGetProgramResourceiv(p.id, pit.id, i, pPropName, null, pPropValue)
      println(s"$name: ${pPropValue.get(0)}")
