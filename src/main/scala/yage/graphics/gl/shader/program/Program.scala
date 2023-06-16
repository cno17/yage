package yage.graphics.gl.shader.program

import org.lwjgl.opengl.GL11C.GL_TRUE
import org.lwjgl.opengl.GL20C.GL_LINK_STATUS
import org.lwjgl.opengl.GL20C.glAttachShader
import org.lwjgl.opengl.GL20C.glCreateProgram
import org.lwjgl.opengl.GL20C.glDeleteProgram
import org.lwjgl.opengl.GL20C.glGetProgramInfoLog
import org.lwjgl.opengl.GL20C.glGetProgrami
import org.lwjgl.opengl.GL20C.glLinkProgram
import org.lwjgl.opengl.GL20C.glUseProgram
import org.lwjgl.system.MemoryStack.stackPush
import yage.graphics.gl.Object
import yage.graphics.gl.shader.Shader
import yage.graphics.gl.shader.ShaderStage
import java.io.File
import yage.util.FileExt

object Program extends FileExt:

  def apply(files: File*) = 
    val res = new Program()
    for f <- files do
      res.attach(loadShader(f))
    res.link()
    if !res.isLinked then throw Exception(res.infoLog)
    res

  def loadShader(f: File) =
    val res = Shader(ShaderStage(f.ext))
    res.load(f.chars)
    res.compile()
    if !res.isCompiled then throw Exception(f.getName() + res.infoLog)
    res 

class Program extends Object, ProgramInfo, UniformGetter, UniformSetter:
  
  override def create() = glCreateProgram()

  def attach(s: Shader) = glAttachShader(id, s.id)

  def link() = glLinkProgram(id)

  def isLinked = glGetProgrami(id, GL_LINK_STATUS) == GL_TRUE

  def infoLog = glGetProgramInfoLog(id)

  // move to ctx: bind
  def use() = glUseProgram(id)

  override def destroy() = glDeleteProgram(id)
