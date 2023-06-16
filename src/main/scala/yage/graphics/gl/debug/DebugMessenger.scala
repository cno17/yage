package yage.graphics.gl.debug

import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL43C.GL_DEBUG_OUTPUT
import org.lwjgl.opengl.GL43C.GL_DEBUG_OUTPUT_SYNCHRONOUS
import org.lwjgl.opengl.GL43C.glDebugMessageCallback
import org.lwjgl.opengl.GL43C.glDebugMessageControl
import org.lwjgl.opengl.GLDebugMessageCallback

import java.nio.IntBuffer
import scala.collection.mutable.ListBuffer

// todo: needs work!

class DebugMessenger(enabled: Boolean):

  if enabled then enable() else disable()
  glDebugMessageCallback(messageReceiver, 0)

  var messageHandler = handle

  def enable() =
    glEnable(GL_DEBUG_OUTPUT)
    glEnable(GL_DEBUG_OUTPUT_SYNCHRONOUS)

  def disable() =
    glDisable(GL_DEBUG_OUTPUT)
    glDisable(GL_DEBUG_OUTPUT_SYNCHRONOUS)

  def enableMessages(src: DebugMessageSource, typ: DebugMessageType, sev: DebugMessageSeverity) =
    glDebugMessageControl(src.id, typ.id, sev.id, null.asInstanceOf[IntBuffer], true)

  def disableMessages(src: DebugMessageSource, typ: DebugMessageType, sev: DebugMessageSeverity) =
    glDebugMessageControl(src.id, typ.id, sev.id, null.asInstanceOf[IntBuffer], false)

  protected def handle(msg: DebugMessage) =
    if msg.severity != DebugMessageSeverity.Notification then println(msg)

  protected def messageReceiver(src: Int, typ: Int, id: Int, sev: Int, length: Int, message: Long, userParam: Long) =
    val msg = DebugMessage()
    msg.source = DebugMessageSource(src)
    msg.typ = DebugMessageType(typ)
    msg.id = id
    msg.severity = DebugMessageSeverity(sev)
    msg.text = GLDebugMessageCallback.getMessage(length, message)
    messageHandler(msg)
