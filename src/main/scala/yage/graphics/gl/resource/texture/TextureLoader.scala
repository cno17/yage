package yage.graphics.gl.resource.texture

import org.lwjgl.BufferUtils
import org.lwjgl.stb.STBImage.stbi_image_free
import org.lwjgl.stb.STBImage.stbi_load
import org.lwjgl.stb.STBImage.stbi_set_flip_vertically_on_load
import org.lwjgl.system.MemoryStack
import yage.graphics.gl.resource.Format
import yage.graphics.gl.resource.texture.Texture2

import java.awt.Graphics2D
import java.awt.Transparency
import java.awt.color.ColorSpace
import java.awt.image.BufferedImage
import java.awt.image.ColorModel
import java.awt.image.ComponentColorModel
import java.awt.image.DataBuffer
import java.awt.image.DataBufferByte
import java.awt.image.Raster
import java.io.File
import java.nio.ByteBuffer

// from lwjgl/examples/spaceinvaders/TextureLoader.java

trait TextureLoader:

  // loadTexture(ImageIO.load(f))
  def loadTexture(f: File) =
    stbi_set_flip_vertically_on_load(true)
    val stack = MemoryStack.stackPush()
    val si = stack.ints(0)
    val sj = stack.ints(0)
    val nc = stack.ints(0)
    val bb = stbi_load(f.getAbsolutePath(), si, sj, nc, 4)
    val tex = new Texture2()
    tex.allocate(1, Format.FP32_4, si.get(0), sj.get(0))
    tex.load(0, Format.UI08_4, bb)
    MemoryStack.stackPop()
    tex

  def loadTexture(img: BufferedImage) =
    val si = img.getWidth()
    val sj = img.getHeight()
    val cs = ColorSpace.getInstance(ColorSpace.CS_sRGB)
    val ap = img.isAlphaPremultiplied()
    val cm = ComponentColorModel(cs, Array(8, 8, 8, 8), true, ap, Transparency.TRANSLUCENT, DataBuffer.TYPE_BYTE)
    val wr = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, si, sj, 4, null)
    val bi = BufferedImage(cm, wr, ap, null)
    val gc = bi.getGraphics().asInstanceOf[Graphics2D]
    gc.scale(1, -1)
    gc.translate(0, -sj)
    gc.drawImage(img, 0, 0, null)
    val ba = bi.getRaster().getDataBuffer().asInstanceOf[DataBufferByte].getData()
    val bb = BufferUtils.createByteBuffer(ba.length)
    bb.put(ba)
    bb.flip()
    val tex = new Texture2()
    tex.allocate(1, Format.FP32_4, si, sj)
    tex.load(0, Format.UI08_4, bb)
    tex






/*
  def loadTexture(arg: BufferedImage) =
    val si = arg.getWidth()
    val sj = arg.getHeight()
    val space = ColorSpace.getInstance(ColorSpace.CS_sRGB)
    var image: BufferedImage = null
    var formatInt: Format = null
    var formatExt: Format = null
    if arg.getColorModel().hasAlpha() then
      val bits = Array(8, 8, 8, 8)
      val model = ComponentColorModel(space, bits, true, false, Transparency.TRANSLUCENT, DataBuffer.TYPE_BYTE) // premul alpha?
      val raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, si, sj, 4, null)
      image = BufferedImage(model, raster, false, null)
      formatInt = Format.FP32_4
      formatExt = Format.UI08_4
    val g = image.getGraphics()
    // g.setColor(Color(0f, 0f, 0f, 0f))
    // g.fillRect(0, 0, si, sj)
    g.drawImage(arg, 0, 0, null)

    val tx = AffineTransform.getScaleInstance(1, -1)
    tx.translate(0, -sj)
    val op = AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR)
    image = op.filter(image, null)

    val data = image.getRaster().getDataBuffer().asInstanceOf[DataBufferByte].getData()
    val buf = BufferUtils.createByteBuffer(data.length)
    // buf.order(ByteOrder.nativeOrder())
    buf.put(data, 0, data.length)
    buf.flip()
    val tex = new Texture2()
    tex.allocate(1, formatInt, si, sj)
    tex.load(0, formatExt, buf)
    tex




  def loadTexture(image: BufferedImage) =
    val w = image.getWidth()
    val h = image.getHeight()
    val pixels = Array[Int](w * h)
    image.getRGB(0, 0, w, h, pixels, 0, w);
    val data = BufferUtils.createByteBuffer(w * h * 4)
    for i <- 0 to w - 1 do
      for j <- 0 to h - 1 do
        val pixel = pixels(j * w + i)
        data.put(((pixel >> 16) & 0xFF).toByte)
        data.put(((pixel >> 8) & 0xFF).toByte)
        data.put(((pixel >> 0) & 0xFF).toByte)
        data.put(((pixel >> 16) & 0xFF).toByte)
    data.flip()
    val res = new Texture2()
    res.allocate(1, Format.FP32_4, w, h)
    res.load(0, Format.UI08_4, data)
    res


    buffer.flip();

    int id = glGenTextures();
    glBindTexture(GL_TEXTURE_2D, id);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(),
                 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
    glGenerateMipmap(GL_TEXTURE_2D);

    return id;

  extension (img: BufferedImage)
    def foreach(f: (Int, Int) => Unit) =
      for i <- 0 to img.getWidth() - 1 do
        for j <- 0 to img.getHeight - 1 do
          f(i, j)

 */
