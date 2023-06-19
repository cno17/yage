package demo.graphics.gl.texture.load

import org.lwjgl.system.MemoryStack
import org.lwjgl.stb.STBImage.stbi_load
import java.io.File

object Test:

  def main(args: Array[String]) =
    val f1 = File("src/main/resources/image/noir/Faya.jpg")
    val f2 = File("src/main/resources/image/misc/grass1.jpg")
    val stack = MemoryStack.stackPush()
    val psi = stack.ints(0)
    val psj = stack.ints(0)
    val pnc = stack.ints(0)
    val data = stbi_load(f1.getAbsolutePath(), psi, psj, pnc, 4)
    val si = psi.get(0)
    val sj = psj.get(0)
    val nc = pnc.get(0)
    MemoryStack.stackPop()
    for i <- 0 to 100 do println(data.get(i))
    println(si)
    println(sj)
    println(si * sj * 4)
    println(data.capacity())