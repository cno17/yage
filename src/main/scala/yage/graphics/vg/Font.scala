package yage.graphics.vg

import org.lwjgl.nanovg.NanoVG.*
import java.io.File

object Font:

  // name could default to file name
  // name needed?

  def apply(ctx: Context, f: File) = 
    val id = nvgCreateFont(ctx.id, "", f.getAbsolutePath())
    if id == -1 then throw RuntimeException(s"Could not load font from file $f")
    new Font(ctx, id)

class Font(val ctx: Context, val id: Int)




