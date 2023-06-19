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

object UniformTest3 extends App:

  override def init() =
    val p = Program(File(File("src/main/scala/test/graphics/gl/reflection"), "UniformTest3.vert"))
    val t = ProgramInterfaceType.Uniform
    printInfo(p, t)
    close()

  def printInfo(p: Program, t: ProgramInterfaceType) =
    val pPropName = BufferUtils.createIntBuffer(1)
    val pPropValue = BufferUtils.createIntBuffer(1)
    val n = glGetProgramInterfacei(p.id, t.id, GL_ACTIVE_RESOURCES)
    for i <- 0 to n - 1 do
      val name = glGetProgramResourceName(p.id, t.id, i)
      pPropName.put(0, GL_LOCATION)
      glGetProgramResourceiv(p.id, t.id, i, pPropName, null, pPropValue)
      println(s"$name: ${pPropValue.get(0)}")
