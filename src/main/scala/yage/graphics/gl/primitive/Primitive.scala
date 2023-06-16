package yage.graphics.gl.primitive

import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL15C.glBindBuffer
import org.lwjgl.opengl.GL15C.glDeleteBuffers
import org.lwjgl.opengl.GL15C.GL_ARRAY_BUFFER
import org.lwjgl.opengl.GL15C.GL_ELEMENT_ARRAY_BUFFER
import org.lwjgl.opengl.GL15C.GL_STATIC_DRAW
import org.lwjgl.opengl.GL20C.glEnableVertexAttribArray
import org.lwjgl.opengl.GL20C.glVertexAttribPointer
import org.lwjgl.opengl.GL30C.glBindVertexArray
import org.lwjgl.opengl.GL30C.glDeleteVertexArrays
import org.lwjgl.opengl.GL44C.GL_DYNAMIC_STORAGE_BIT
import org.lwjgl.opengl.GL45C.*

import yage.graphics.gl.Object
import scala.collection.mutable.ArrayBuffer

type ComponentArray = Array[AnyVal]

// step 1: all attributes are stored in a single buffer in interleaved layout

class Primitive(val vertexDescriptor: VertexDescriptor) extends Object:

  val attributeArrays = new Array[ArrayBuffer[AnyVal]](vertexDescriptor.attributeCount)
  val indexArray = ArrayBuffer[Int]

  override def create() = glCreateVertexArrays()
  
  override def destroy() = glDeleteVertexArrays(id)
