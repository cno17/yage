package yage.graphics.gl.primitive

import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL15C.*
import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL45C.*

import scala.collection.mutable.ArrayBuffer
import org.lwjgl.BufferUtils

object Grid:

  def main(args: Array[String]) =
    val g = Grid(0, 1, 0, 1, 3, 2)
    g.initAttributeArrays()
    println(2)

/**
 * use Buffer class, viewport trafo, zoom, translate
 */

class Grid(minU: Float, maxU: Float, minV: Float, maxV: Float, numU: Int, numV: Int):

  val incU = (maxU - minU) / (numU - 1)
  val incV = (maxV - minV) / (numV - 1)

  val posA = new Array[Float](numU * numV * 2)
  val colA = new Array[Float](numU * numV * 4)
  val indA = new Array[Int]((numU - 1) * (numV - 1) * 6)

  var vao = 0
  var vbo = 0
  var ibo = 0

  def initAttributeArrays() =
    for iu <- 0 to numU - 1 do
      for iv <- 0 to numV - 1 do
        val n = index(iu, iv)
        posA(n + 0) = iu * incU
        posA(n + 1) = iv * incV
        colA(n + 0) = 0.0f
        colA(n + 1) = 1.0f
        colA(n + 2) = 0.0f
        colA(n + 3) = 1.0f

  def initIndexArray() =
    for iu <- 0 to numU - 1 do
      for iv <- 0 to numV - 1 do
        val n = index(iu, iv)
        posA(n + 0) = iu * incU
        posA(n + 1) = iv * incV
        colA(n + 0) = 0.0f
        colA(n + 1) = 1.0f
        colA(n + 2) = 0.0f
        colA(n + 3) = 1.0f

  def createVertexData() =
    val res = BufferUtils.createByteBuffer(posA.size * 4 + colA.size * 4)

  def createIndexData() =
    val res = BufferUtils.createByteBuffer(indA.size * 4)


  def create() =
    vao = glCreateVertexArrays()
    vbo = glCreateBuffers()
    ibo = glCreateBuffers()
    glNamedBufferStorage(ibo, indA, 0)
      // glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indB)

  def destroy() =
    glDeleteBuffers(ibo)
    glDeleteBuffers(vbo)
    glDeleteVertexArrays(vao)


  def index(iu: Int, iv: Int) = iu + iv * numU
