package yage.graphics.gl

import scala.collection.mutable.ArrayBuffer

object ClassUtil:
 
  def main(args: Array[String]) =
    val fn = "GL_PROGRAM_POINT_SIZE"
    println(findDeclaringClassOf(fn))

  def findDeclaringClassOf(memberName: String): String =
    for c <- classes do
      for f <- c.getDeclaredFields() do
        if memberName.equals(f.getName()) then 
          return c.getName()
      for m <- c.getDeclaredMethods() do
        if memberName.equals(m.getName()) then 
          return c.getName()
    return "Not Found"

  def classes =
    val res = ArrayBuffer[Class[_]]()
    res += Class.forName("org.lwjgl.opengl.GL11C")
    res += Class.forName("org.lwjgl.opengl.GL12C")
    res += Class.forName("org.lwjgl.opengl.GL13C")
    res += Class.forName("org.lwjgl.opengl.GL14C")
    res += Class.forName("org.lwjgl.opengl.GL15C")
    res += Class.forName("org.lwjgl.opengl.GL20C")
    res += Class.forName("org.lwjgl.opengl.GL21C")
    res += Class.forName("org.lwjgl.opengl.GL30C")
    res += Class.forName("org.lwjgl.opengl.GL31C")
    res += Class.forName("org.lwjgl.opengl.GL32C")
    res += Class.forName("org.lwjgl.opengl.GL33C")
    res += Class.forName("org.lwjgl.opengl.GL40C")
    res += Class.forName("org.lwjgl.opengl.GL41C")
    res += Class.forName("org.lwjgl.opengl.GL42C")
    res += Class.forName("org.lwjgl.opengl.GL43C")
    res += Class.forName("org.lwjgl.opengl.GL44C")
    res += Class.forName("org.lwjgl.opengl.GL45C")
    res += Class.forName("org.lwjgl.opengl.GL46C")
    res
