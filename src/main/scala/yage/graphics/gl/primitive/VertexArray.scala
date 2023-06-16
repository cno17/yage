package yage.graphics.gl.primitive

import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL45C.*
import yage.graphics.gl.resource.buffer.Buffer
import yage.graphics.gl.Object

/**
 * attribute index = attribute location?
 */

class VertexArray extends Object:

  override def create() =
    glCreateVertexArrays()

  def bind() =
    glBindVertexArray(id)

  def bindVertexBuffer(buffer: Buffer, binding: Int, offset: Long, stride: Int) =
    glVertexArrayVertexBuffer(id, binding, buffer.id, offset, stride)

  def bindIndexBuffer(buffer: Buffer) =
    glVertexArrayElementBuffer(id, buffer.id)

  def enableAttribute(index: Int) =
    glEnableVertexArrayAttrib(id, index)

  def disableAttribute(index: Int) =
    glDisableVertexArrayAttrib(id, index)

  def setAttributeFormat(index: Int, size: Int, typ: Int, normalized: Boolean, offset: Int) =
    glVertexArrayAttribFormat(id, index, size, typ, normalized, offset)

  def setAttributeBinding(index: Int, binding: Int) =
    glVertexArrayAttribBinding(id, index, binding)

  def setBindingDivisor(binding: Int, divisor: Int) =
    glVertexArrayBindingDivisor(id, binding, divisor)

  override def destroy() =
    glDeleteVertexArrays(id)
