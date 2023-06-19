package test.math

import yage.math.*

import java.nio.ByteBuffer
import org.joml.*
import scala.util.Random

object MathTest:

  def main(args: Array[String]) = testVec

  def testVec =
    val a = Vec2(2, 3)
    val b = Vec2(a)
    println(a += b)
    println(a)
    println(b)
    
  def testMat1 =
    val a = Mat4().toRandom(0, 10)
    val b = a.inverted()
    println(a)
    println(b)
    val c = Mat4().toProductOf(b, a)
    println(c)

  def testMat2 =
    val a = Mat4()
    val b = Mat4().toRotation(1, 0, 0, 0.1f)
    println(a)
    println(b)
    println("==================")
    a =* (a, b)
    println(a)
    a =* (a, b)
    println(a)

  def testMat3 =
    val mat = Mat2()
    //  mat.foreach((i, j) => mat(i, j) = mat.n * i + j)
    mat.foreach((i, j) => mat(i, j) = 2.0f * i + j) // ???
    val buf1 = ByteBuffer.allocate(mat.n * mat.n * 4)
    mat >> (buf1, 0)
    println(mat)
    val buf2 = buf1.asFloatBuffer
    for i <- 0 to buf2.capacity - 1 do println(buf2.get(i))

  def testTrafo1 =
    val a = Mat4()
    println(a)
    a.rotateL(1, 0, 0, 0.1f)
    println(a)
    a.rotateL(1, 0, 0, 0.1f)
    println(a)
    
  def testTrafo2 =
    val a = Mat3()
    val b = Mat3().toRandom(0, 5) // toRotation(0.5f)
    println(a)
    a.toProductOf(a, b)
    println(a)
    a.toProductOf(a, b)
    println(a)
    
  def testTrafo3 =
    val x = -1 + 2 * Random.nextFloat()
    val y = -1 + 2 * Random.nextFloat()
    val z = -1 + 2 * Random.nextFloat()
    val a = -4 + 8 * Random.nextFloat()
    val mat1 = Mat4()
    val mat2 = Matrix4f()
    mat1.toRotation(Vec3(x, y, z).normalize(), a)
    mat2.rotate(a, Vector3f(x, y, z).normalize(), mat2)
    println(mat1)
    println(mat2)
  
  def testTrafo4 =
    val (l, r, b, t, n, f) = (-1.5f, 0.6f, -0.5f, 0.8f, 5.0f, 100.0f)
    val mat1 = Mat4().toFrustum(l, r, b, t, n, f)
    println(mat1)
    val mat2 = Matrix4f().identity().frustum(l,r, b, t, n, f)
    println(mat2)
    val mat3 = Mat4().toOrtho(l, r, b, t, n, f)
    println(mat3)
    val mat4 = Matrix4f().identity().ortho(l,r, b, t, n, f)
    println(mat4)
    
