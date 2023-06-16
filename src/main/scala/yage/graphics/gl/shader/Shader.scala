package yage.graphics.gl.shader

import org.lwjgl.opengl.GL11C.GL_TRUE
import org.lwjgl.opengl.GL20C.glCompileShader
import org.lwjgl.opengl.GL20C.glCreateShader
import org.lwjgl.opengl.GL20C.glDeleteShader
import org.lwjgl.opengl.GL20C.glGetShaderInfoLog
import org.lwjgl.opengl.GL20C.glGetShaderi
import org.lwjgl.opengl.GL20C.glShaderSource
import org.lwjgl.opengl.GL20C.GL_COMPILE_STATUS
import yage.graphics.gl.Object

class Shader(s: ShaderStage) extends Object:

  override def create() = glCreateShader(s.id)

  def load(code: String) = glShaderSource(id, code)

  def compile() = glCompileShader(id)

  def isCompiled = glGetShaderi(id, GL_COMPILE_STATUS) == GL_TRUE
  
  def infoLog = glGetShaderInfoLog(id)

  override def destroy() = glDeleteShader(id)





  
/*

object Shader:

  def apply(stage: ShaderStage, source: String) =
    val res = new Shader(stage)
    res.load(source)
    res.compile()
    if !res.isCompiled then throw Exception(res.infoLog)
    res

  def apply(stage: ShaderStage, code: ByteBuffer) =
    val res = new Shader(stage)
    res.load(code)
    res.specialize()
    if !res.isSpecialized then throw Exception(res.infoLog)
    res
  def load(code: ByteBuffer) =
    useStack(s =>
      val pId = s.ints(id);
      glShaderBinary(pId, GL_SHADER_BINARY_FORMAT_SPIR_V, code)
    )

  def specialize() =
    val index: IntBuffer = null
    val value: IntBuffer = null
    glSpecializeShader(id, "main", index, value)

  def isSpecialized = glGetShaderi(id, GL_COMPILE_STATUS) == GL_TRUE

 */
