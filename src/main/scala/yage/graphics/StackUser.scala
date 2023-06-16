package yage.graphics

import org.lwjgl.system.MemoryStack

trait StackUser:

  def useStack[A](f: MemoryStack => A) =
    val s = MemoryStack.stackPush()
    val r = f(s)
    MemoryStack.stackPop()
    r
