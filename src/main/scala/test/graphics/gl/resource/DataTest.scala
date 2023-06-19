package test.graphics.gl.resource

import org.lwjgl.BufferUtils
import java.nio.ByteBuffer

object DataTest:

  def main(args: Array[String]) =
    val da = Array(0.5, 2.7, 3.1)
    val db = toByteBufferOld(da)
    println("ok")
/*
  def toByteBuffer(a: Array[AnyVal]) =
    val b = BufferUtils.createByteBuffer(8 * data.size)
    for d <- data do 
      res.putDouble(d)
    res.rewind()
*/
  def toByteBufferOld(data: Array[Double]) =
    val res = BufferUtils.createByteBuffer(8 * data.size)
    for d <- data do 
      res.putDouble(d)
    res.rewind()

  def numBytes(a: AnyVal) =
      if a.isInstanceOf[Byte] then 1
      else if a.isInstanceOf[Short] then 2
      else if a.isInstanceOf[Int] then 4
      else if a.isInstanceOf[Long] then 8
      else if a.isInstanceOf[Float] then 4
      else if a.isInstanceOf[Double] then 8
      else throw RuntimeException()


  extension(b: ByteBuffer)
    def put(a: AnyVal) =
      if a.isInstanceOf[Byte] then b.put(a.asInstanceOf[Byte])
      else if a.isInstanceOf[Short] then b.putShort(a.asInstanceOf[Short])
      else if a.isInstanceOf[Int] then b.putInt(a.asInstanceOf[Int])
      else if a.isInstanceOf[Long] then b.putLong(a.asInstanceOf[Long])
      else if a.isInstanceOf[Float] then b.putDouble(a.asInstanceOf[Float])
      else if a.isInstanceOf[Double] then b.putDouble(a.asInstanceOf[Double])
      else throw RuntimeException()