package yage.scene.geometry

import yage.math.Vec2
import yage.math.Vec3
import scala.collection.mutable.ArrayBuffer
import yage.math.Vec4

val PI = Math.PI.toFloat


/**
 * a parametrized surface: (u, v) => (x, y, z)
 * todo: create indices, do not use array buffers - compute array size
 */

abstract class Surface(minU: Float, maxU: Float, minV: Float, maxV: Float, numU: Int, numV: Int) extends Geometry:

  private val du = 0.0001f // (maxU - minU) * 0.001f
  private val dv = 0.0001f

  private val incU = (maxU - minU) / (numU - 1)
  private val incV = (maxV - minV) / (numV - 1)

  def vertexCount = numU * numV

  def indexCount = (numU - 1) * (numV - 1) * 6

  def toIndex(iu: Int, iv: Int) = iu + iv * numU

  def pos(u: Float, v: Float): Vec4

  def nor(u: Float, v: Float) =
    val p0 = pos(u, v)
    val p1 = pos(u + du, v)
    val p2 = pos(u, v + dv)
    val ux = p1.x - p0.x
    val uy = p1.y - p0.y
    val uz = p1.z - p0.z
    val vx = p2.x - p0.x
    val vy = p2.y - p0.y
    val vz = p2.z - p0.z
    val nx = uy * vz - vy * uz
    val ny = vx * uz - ux * vz
    val nz = ux * vy - vx * uy
    Vec4(nx, ny, nz, 0) // TODO

  def tan(u: Float, v: Float) = 
    val p0 = pos(u, v)
    val p1 = pos(u + du, v)
    val tx = p1.x - p0.x
    val ty = p1.y - p0.y
    val tz = p1.z - p0.z
    Vec4(tx, ty, tz, 0).normalize()    

  def col(u: Float, v: Float) =
    Vec4(0, 1, 0, 1)
  
  def tec(u: Float, v: Float) =
    Vec2(u / numU, v / numV)

  override def init() =
    posA = new Array[Vec4](vertexCount)
    norA = new Array[Vec4](vertexCount)
    tanA = new Array[Vec4](vertexCount)
    colA = new Array[Vec4](vertexCount)
    tecA = new Array[Vec2](vertexCount)
    for iu <- 0 to numU - 1 do
      val u = minU + incU * iu
      for iv <- 0 to numV - 1 do
        val v = minV + incV * iv
        val i = toIndex(iu, iv)
        posA(i) = pos(u, v)
        norA(i) = nor(u, v)
        tanA(i) = tan(u, v)
        colA(i) = col(u, v)
        tecA(i) = tec(u, v)
    val indB = new ArrayBuffer[Int]()
    for iu <- 0 to numU - 2 do
      for iv <- 0 to numV - 2 do
        indB += toIndex(iu + 0, iv + 0) 
        indB += toIndex(iu + 1, iv + 0) 
        indB += toIndex(iu + 1, iv + 1) 
        indB += toIndex(iu + 1, iv + 1) 
        indB += toIndex(iu + 0, iv + 1) 
        indB += toIndex(iu + 0, iv + 0) 
    indA = indB.toArray

  // override 
  def initOld() =
    posA = new Array[Vec4](vertexCount)
    norA = new Array[Vec4](vertexCount)
    tanA = new Array[Vec4](vertexCount)
    colA = new Array[Vec4](vertexCount)
    tecA = new Array[Vec2](vertexCount)
    for iu <- 0 to numU - 1 do
      val u = minU + incU * iu
      for iv <- 0 to numV - 1 do
        val v = minV + incV * iv
        val i = toIndex(iu, iv)
        posA(i) = pos(u, v)
        norA(i) = nor(u, v)
        tanA(i) = tan(u, v)
        colA(i) = col(u, v)
        tecA(i) = tec(u, v)
    val indB = new ArrayBuffer[Int]()
    for iu <- 0 to numU - 2 do
      for iv <- 0 to numV - 2 do
        if (iu + iv) % 2 == 0 then
          indB += toIndex(iu + 0, iv + 0) 
          indB += toIndex(iu + 1, iv + 0) 
          indB += toIndex(iu + 1, iv + 1) 
          indB += toIndex(iu + 1, iv + 1) 
          indB += toIndex(iu + 0, iv + 1) 
          indB += toIndex(iu + 0, iv + 0) 
        else
          indB += toIndex(iu + 1, iv + 0) 
          indB += toIndex(iu + 1, iv + 1) 
          indB += toIndex(iu + 0, iv + 1) 
          indB += toIndex(iu + 0, iv + 1) 
          indB += toIndex(iu + 0, iv + 0) 
          indB += toIndex(iu + 1, iv + 0)
    indA = indB.toArray
