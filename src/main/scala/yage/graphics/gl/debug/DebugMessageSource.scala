package yage.graphics.gl.debug

import org.lwjgl.opengl.GL11C.GL_DONT_CARE
import org.lwjgl.opengl.GL43C.GL_DEBUG_SOURCE_API
import org.lwjgl.opengl.GL43C.GL_DEBUG_SOURCE_APPLICATION
import org.lwjgl.opengl.GL43C.GL_DEBUG_SOURCE_OTHER
import org.lwjgl.opengl.GL43C.GL_DEBUG_SOURCE_SHADER_COMPILER
import org.lwjgl.opengl.GL43C.GL_DEBUG_SOURCE_THIRD_PARTY
import org.lwjgl.opengl.GL43C.GL_DEBUG_SOURCE_WINDOW_SYSTEM

object DebugMessageSource:

  def apply(v: Int) = values.find(_.id == v).get

enum DebugMessageSource(val id: Int):
  
  case Api extends DebugMessageSource(GL_DEBUG_SOURCE_API)
  case WindowSystem extends DebugMessageSource(GL_DEBUG_SOURCE_WINDOW_SYSTEM)
  case ShaderCompiler extends DebugMessageSource(GL_DEBUG_SOURCE_SHADER_COMPILER)
  case ThirdParty extends DebugMessageSource(GL_DEBUG_SOURCE_THIRD_PARTY)
  case Application extends DebugMessageSource(GL_DEBUG_SOURCE_APPLICATION)
  case Other extends DebugMessageSource(GL_DEBUG_SOURCE_OTHER)
  //
  case DontCare extends DebugMessageSource(GL_DONT_CARE)

