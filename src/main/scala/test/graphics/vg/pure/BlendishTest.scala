package test.graphics.vg.pure
import java.io.File
import java.nio.ByteBuffer
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.BufferUtils
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVGGL3
import org.lwjgl.nanovg.Blendish.*

import yage.graphics.fw.App
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NVGColor
import java.nio.file.Paths
import java.nio.file.Files
import scala.annotation.varargs
import java.io.FileInputStream

object BlendishTest extends App:

  var ctxp: Long = 0
  var color: NVGColor = null

  override def init() =
    glClearColor(0, 0, 0, 1)
    ctxp = NanoVGGL3.nvgCreate(0)
    color = NVGColor.create()
    nvgRGBf(0.2f, 0.8f, 0.2f, color)
    // val f = File(resDir, "font/Roboto-Regular.ttf")
    // val h = nvgCreateFont(ctx, "roboto", f.getAbsolutePath())
    // bndSetFont(h)
    // bndSetIconImage()

  override def draw() =
    val sx = window.innerSizeX.toFloat
    val sy = window.innerSizeY.toFloat
    glClear(GL_COLOR_BUFFER_BIT)
    nvgBeginFrame(ctxp, sx, sy, 1)
    nvgBeginPath(ctxp)
    bndArrow(ctxp, 100, 100, 50, color)
    // bndBackground(ctx, 100, 100, 400, 300)
    // bndBevel(ctx, 100, 100, 400, 300)
    // bndCheck(ctx, 100, 100, color)
    // bndChoiceButton(ctx, 100, 100, 100, 20, BND_CORNER_TOP, BND_ACTIVE, 0, "Label")
    // bndColorButton(ctx, 100, 100, 100, 50, BND_CORNER_LEFT, color)
    // bndColoredNodeWire(ctx, 100, 100, 200, 200, color, color)
    // bndIcon(ctx, 100, 100, BND_ICON_ARMATURE_DATA) // TODO
    nvgClosePath(ctxp)
    nvgEndFrame(ctxp)


  def bytesOf(f: File) =
    val is = FileInputStream(f)
    val ba = is.readAllBytes()
    is.close
    BufferUtils.createByteBuffer(ba.length).put(ba).rewind()

