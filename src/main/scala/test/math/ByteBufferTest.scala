package test.math

import yage.math.*
import org.lwjgl.BufferUtils


object ByteBufferTest:

  def main(args: Array[String]): Unit =
    val a = Vec2(2, 3)
    val b = Vec3(3, 5, 7)
    val buf = BufferUtils.createByteBuffer(20)
    a >> buf
    b >> buf
    buf.rewind()
    b << (buf, 8)
    a << (buf, 0)
    println(a)
    println(b)
