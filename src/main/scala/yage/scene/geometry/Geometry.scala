package yage.scene.geometry

import org.lwjgl.opengl.GL11C.glDrawArrays
import org.lwjgl.opengl.GL11C.glDrawElements
import org.lwjgl.opengl.GL11C.GL_FLOAT
import org.lwjgl.opengl.GL11C.GL_TRIANGLES
import org.lwjgl.opengl.GL11C.GL_UNSIGNED_INT
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
import org.lwjgl.opengl.GL45C.glCreateBuffers
import org.lwjgl.opengl.GL45C.glCreateVertexArrays
import org.lwjgl.opengl.GL45C.glNamedBufferStorage
import org.lwjgl.BufferUtils
import yage.graphics.gl.primitive.PrimitiveMode
import yage.math.MathUtil
import yage.math.Vec
import yage.math.Vec2
import yage.math.Vec3
import yage.math.Vec4
import yage.shape.ABox3

import java.nio.ByteBuffer

import scala.collection.mutable.ArrayBuffer

/**
 * An indexed triangle mesh for now. 
 * num: number of vertices (indices?) indices, positions and normals are mandatory
 *
 * Note: changed order of tangents and texCoords ToDo: add colors, interleaved attributes
 *
 * pos, nor, tan, col: Vec4 tec: Vec2
 * 
 * todo: ByteBufferable
 * 
 * todo: ABox, yage.Buffer
 */

// (val mode: PrimitiveMode)

abstract class Geometry extends MathUtil:

  var mode = PrimitiveMode.Triangles

  var posA: Array[Vec4] = null
  var norA: Array[Vec4] = null
  var tanA: Array[Vec4] = null
  var colA: Array[Vec4] = null
  var tecA: Array[Vec2] = null
  var indA: Array[Int] = null

  var box: ABox3 = null // todo

  protected var vao = 0
  protected var ibo = 0
  protected var vboPos = 0
  protected var vboNor = 0
  protected var vboTan = 0
  protected var vboCol = 0
  protected var vboTec = 0

  // TODO why must this be called in subclasses?
  init()
  create()

  def init(): Unit

  def create() =

    vao = glCreateVertexArrays
    glBindVertexArray(vao)

    ibo = glCreateBuffers()
    glNamedBufferStorage(ibo, bytesOf(indA), 0)
    glVertexArrayElementBuffer(vao, ibo)

    if posA != null then
      vboPos = glCreateBuffers()
      glNamedBufferStorage(vboPos, bytesOf(posA), 0)
      glVertexArrayVertexBuffer(vao, 0, vboPos, 0, 16)
      glEnableVertexAttribArray(0)
      glVertexArrayAttribFormat(vao, 0, 4, GL_FLOAT, false, 0)
      glVertexArrayAttribBinding(vao, 0, 0)

    if norA != null then
      vboNor = glCreateBuffers()
      glNamedBufferStorage(vboNor, bytesOf(norA), 0)
      glVertexArrayAttribFormat(vao, 1, 4, GL_FLOAT, false, 0)
      glVertexArrayAttribBinding(vao, 1, 1)
      glEnableVertexAttribArray(1)
      glVertexArrayVertexBuffer(vao, 1, vboNor, 0, 16)

    if tanA != null then
      vboTan = glCreateBuffers()
      glNamedBufferStorage(vboTan, bytesOf(tanA), 0)
      glVertexArrayAttribFormat(vao, 2, 4, GL_FLOAT, false, 0)
      glVertexArrayAttribBinding(vao, 2, 2)
      glEnableVertexAttribArray(2)
      glVertexArrayVertexBuffer(vao, 2, vboTan, 0, 16)

    if colA != null then
      vboCol = glCreateBuffers()
      glNamedBufferStorage(vboCol, bytesOf(colA), 0)
      glVertexArrayAttribFormat(vao, 3, 4, GL_FLOAT, false, 0)
      glVertexArrayAttribBinding(vao, 3, 3)
      glEnableVertexAttribArray(3)
      glVertexArrayVertexBuffer(vao, 3, vboCol, 0, 16)

    if tecA != null then
      vboTec = glCreateBuffers()
      glNamedBufferStorage(vboTec, bytesOf(tecA), 0)
      glVertexArrayAttribFormat(vao, 4, 2, GL_FLOAT, false, 0)
      glVertexArrayAttribBinding(vao, 4, 4)
      glEnableVertexAttribArray(4)
      glVertexArrayVertexBuffer(vao, 4, vboTec, 0, 8)

  def draw() =
    glBindVertexArray(vao)
    glDrawElements(mode.value, indA.size, GL_UNSIGNED_INT, 0)

  def destroy() =
    glDeleteVertexArrays(vao)
    glDeleteBuffers(ibo)
    if vboPos != 0 then glDeleteBuffers(vboPos)
    if vboNor != 0 then glDeleteBuffers(vboNor)
    if vboTan != 0 then glDeleteBuffers(vboTan)
    if vboCol != 0 then glDeleteBuffers(vboCol)
    if vboTec != 0 then glDeleteBuffers(vboTec)

  protected def bytesOf[V <: Vec[V]](vs: Array[V]) =
    val dim = if vs.isEmpty then 0 else vs(0).n
    val buf = BufferUtils.createByteBuffer(vs.size * dim * 4)
    for v <- vs; i <- 0 to dim - 1 do
      buf.putFloat(v(i))
    buf.rewind()

  protected def bytesOf(is: Array[Int]) =
    val buf = BufferUtils.createByteBuffer(is.size * 4)
    for i <- is do
      buf.putInt(i)
    buf.rewind()
