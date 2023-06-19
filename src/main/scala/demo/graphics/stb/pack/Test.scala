package demo.graphics.stb.pack

import org.lwjgl.stb.STBRPContext
import org.lwjgl.stb.STBRPNode
import org.lwjgl.stb.STBRectPack.*
import org.lwjgl.stb.STBRPRect
import scala.util.Random

// todo draw with nvg!

object Test:

  val rnd = Random()

  def main(args: Array[String]) =
    val n = 10
    val ctx = STBRPContext.create()
    val nb = STBRPNode.malloc(n)
    val rb = STBRPRect.malloc(n)
    for i <- 0 to n - 1 do
      val w = 20 + rnd.nextInt(50)
      val h = 20 + rnd.nextInt(50)
      rb.get(i).x(0).y(0).w(w).h(h)
    stbrp_init_target(ctx, 200, 200, nb)
    val res = stbrp_pack_rects(ctx, rb)
    println(res)
    for i <- 0 to n - 1 do
      rb.get(i).print()

  extension (r: STBRPRect)
    def print() = println(s"x = ${r.x}, y = ${r.y}, w = ${r.w}, h = ${r.h}")

