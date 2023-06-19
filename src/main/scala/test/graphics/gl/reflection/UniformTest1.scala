package test.graphics.gl

// import yage.graphics.gl.shader.program.ProgramFactory

import java.io.File
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import yage.graphics.fw.App
import yage.graphics.gl.shader.program.Program
import yage.graphics.gl.shader.program.interface.ProgramInterfaceType
import yage.graphics.gl.shader.ShaderStage
import yage.graphics.gl.shader.Shader
import org.lwjgl.opengl.GL20C.*
import org.lwjgl.opengl.GL43C.*
import org.lwjgl.BufferUtils

object UniformTest1 extends App:

  override def init() =
    val d = File("src/main/scala/test/graphics/gl/reflection")
    val p = Program(File(d, "UniformTest1.vert"))
    printInfoNewStyle(p)
    close()

  def printInfoOldStyle(p: Program) =
    val pSize = BufferUtils.createIntBuffer(1)
    val pType = BufferUtils.createIntBuffer(1)
    val name0 = glGetActiveUniform(p.id, 0, pSize, pType)
    println(s"$name0: ${pSize.get(0)}, ${pType.get(0)}")
    val name1 = glGetActiveUniform(p.id, 1, pSize, pType)
    println(s"$name1: ${pSize.get(0)}, ${pType.get(0)}")
    val name2 = glGetActiveUniform(p.id, 2, pSize, pType)
    println(s"$name2: ${pSize.get(0)}, ${pType.get(0)}")

  def printInfoNewStyle(p: Program) =
    val pPropName = BufferUtils.createIntBuffer(1)
    val pPropValue = BufferUtils.createIntBuffer(1)
    pPropName.put(0, GL_LOCATION)
    // pPropName.rewind()
    val n = glGetProgramInterfacei(p.id, ProgramInterfaceType.Uniform.id, GL_ACTIVE_RESOURCES)
    for i <- 0 to n - 1 do
      glGetProgramResourceiv(p.id, ProgramInterfaceType.Uniform.id, i, pPropName, null, pPropValue)
      println(pPropValue.get(0))
