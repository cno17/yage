package test.graphics

import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryStack.stackPush

object UsingTest:

  class Resource extends AutoCloseable :
    override def close = println("closed")

  def use(f: MemoryStack => Unit) =
    val s = stackPush
    f(s)
    s.pop

  def using[A <: AutoCloseable](res: A)(fun: A => Unit): Unit =
    try
      fun(res)
    finally
      res.close

  def main(args: Array[String]) =
    using(new Resource)(res =>
      println(2 * 3)
    )
    using(stackPush)(s =>
      val is = s.ints(5)
    )
    use(s =>
      val as = s.mallocInt(2)
    )
    println("ok")
