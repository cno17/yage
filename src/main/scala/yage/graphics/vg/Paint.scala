package yage.graphics.vg

import org.lwjgl.nanovg.NVGPaint
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVG.nvgLinearGradient
import org.lwjgl.nanovg.NanoVG.nvgRadialGradient
import yage.graphics.vg.{Paint, Gradient, LinearGradient, RadialGradient}

abstract class Paint(val ctx: Context):

  val handle = NVGPaint.create()

  // transfer subclass specific attributes to nanovg

  def apply(): Unit

//   

abstract class Gradient(ctx: Context) extends Paint(ctx)

//

class LinearGradient(ctx: Context, val minColor: Color, val maxColor: Color) extends Gradient(ctx):
    
  val min = Point()
  val max = Point()

  def this(ctx: Context) = this(ctx, Color(), Color())

  override def apply() = nvgLinearGradient(ctx.id, min.x, min.y, max.x, max.y, minColor.handle, maxColor.handle, handle)

//

class RadialGradient(ctx: Context, val innerColor: Color, val outerColor: Color) extends Gradient(ctx):

  val org = Point()
  var innerRadius = 0.0f
  var outerRadius = 0.0f

  def this(ctx: Context) = this(ctx, Color(), Color())

  override def apply() = nvgRadialGradient(ctx.id, org.x, org.y, innerRadius, outerRadius, innerColor.handle, outerColor.handle, handle)


// class BoxGradient extends Gradient

class ImagePattern(img: Image) extends Paint(img.ctx):

  var px = 0.0f
  var py = 0.0f
  var sx = 0.0f
  var sy = 0.0f
  var angle = 0.0f
  var alpha = 1.0f

  override def apply() = nvgImagePattern(ctx.id, px, py, sx, sy, angle, img.id, alpha, handle)
