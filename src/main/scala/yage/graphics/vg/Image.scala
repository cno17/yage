package yage.graphics.vg

import org.lwjgl.nanovg.NanoVG.*

import yage.graphics.Caller
import java.nio.ByteBuffer
import java.io.File

// join with ImagePattern

object Image:

  def apply(ctx: Context, file: File): Image = apply(ctx, file, ImageFlags())
  
  def apply(ctx: Context, file: File, flags: ImageFlags) =
    val id = nvgCreateImage(ctx.id, file.getAbsolutePath(), flags.toInt())
    if id == 0 then throw new RuntimeException("Could not create image.")
    new Image(ctx, id)
    
  def apply(ctx: Context, data: ByteBuffer): Image = apply(ctx, data, ImageFlags())
  
  def apply(ctx: Context, data: ByteBuffer, flags: ImageFlags) =
    val id = nvgCreateImageMem(ctx.id, flags.toInt(), data)
    if id == 0 then throw new RuntimeException("Could not create image.")
    new Image(ctx, id)

class Image(val ctx: Context, val id: Int) extends Caller:

  // move to CallerVg

  def sizeI =
    var res = 0
    useStack(s =>
      val bufI = s.mallocInt(1)
      val bufJ = s.mallocInt(1)
      nvgImageSize(ctx.id, id, bufI, bufJ)
      res = bufI.get(0)
    )
    res

  def sizeJ =
    var res = 0
    useStack(s =>
      val bufI = s.mallocInt(1)
      val bufJ = s.mallocInt(1)
      nvgImageSize(ctx.id, id, bufI, bufJ)
      res = bufJ.get(0)
    )
    res

  def update(data: ByteBuffer) = nvgUpdateImage(ctx.id, id, data)

  def destroy() = nvgDeleteImage(ctx.id, id)

