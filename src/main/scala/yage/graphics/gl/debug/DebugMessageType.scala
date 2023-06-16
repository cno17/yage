package yage.graphics.gl.debug

import org.lwjgl.opengl.GL11C.GL_DONT_CARE
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_ERROR
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_MARKER
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_OTHER
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_PERFORMANCE
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_POP_GROUP
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_PORTABILITY
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_PUSH_GROUP
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR

object DebugMessageType:

  def apply(v: Int) = values.find(_.id == v).get

enum DebugMessageType(val id: Int):
  
  case Error extends DebugMessageType(GL_DEBUG_TYPE_ERROR)
  case DeprecatedBehavior extends DebugMessageType(GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR)
  case UndefinedBehavior extends DebugMessageType(GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR)
  case Portability extends DebugMessageType(GL_DEBUG_TYPE_PORTABILITY)
  case Performance extends DebugMessageType(GL_DEBUG_TYPE_PERFORMANCE)
  case Marker extends DebugMessageType(GL_DEBUG_TYPE_MARKER)
  case PushGroup extends DebugMessageType(GL_DEBUG_TYPE_PUSH_GROUP)
  case PopGroup extends DebugMessageType(GL_DEBUG_TYPE_POP_GROUP)
  case Other extends DebugMessageType(GL_DEBUG_TYPE_OTHER)
  //
  case DontCare extends DebugMessageType(GL_DONT_CARE)

