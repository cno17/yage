package yage.graphics.gl.debug

import org.lwjgl.opengl.GL11C.GL_DONT_CARE
import org.lwjgl.opengl.GL43C.GL_DEBUG_SEVERITY_HIGH
import org.lwjgl.opengl.GL43C.GL_DEBUG_SEVERITY_LOW
import org.lwjgl.opengl.GL43C.GL_DEBUG_SEVERITY_MEDIUM
import org.lwjgl.opengl.GL43C.GL_DEBUG_SEVERITY_NOTIFICATION

// move: DebugMessage.Severity

object DebugMessageSeverity:
  
  def apply(v: Int) = values.find(_.id == v).get

enum DebugMessageSeverity(val id: Int, val level: Int) extends Ordered[DebugMessageSeverity]:
  
  case Notification extends DebugMessageSeverity(GL_DEBUG_SEVERITY_NOTIFICATION, 0)
  case Low extends DebugMessageSeverity(GL_DEBUG_SEVERITY_LOW, 1)
  case Medium extends DebugMessageSeverity(GL_DEBUG_SEVERITY_MEDIUM, 2)
  case High extends DebugMessageSeverity(GL_DEBUG_SEVERITY_HIGH, 3)
  // 
  case DontCare extends DebugMessageSeverity(GL_DONT_CARE, 17)

  override def compare(s: DebugMessageSeverity) = level - s.level

