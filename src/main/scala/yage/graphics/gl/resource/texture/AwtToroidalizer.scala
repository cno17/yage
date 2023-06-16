package yage.graphics.gl.resource.texture

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JPanel
import java.awt.Graphics
import javax.swing.JFrame
import javax.swing.SwingConstants
import javax.swing.WindowConstants
import java.awt.Graphics2D
import java.awt.geom.AffineTransform
import java.awt.Color

// todo: repetition, image op

object AwtToroidalizer:

  def main(args: Array[String]): Unit =
    val s = "src/main/resources/image/bird/Blackbird1.jpg"
    val i1 = ImageIO.read(File(s))
    val i2 = grayscalize(toroidalize(i1))
    ImageIO.write(i2, "jpg", File(tName(s)))
    

  def tName(s: String) =
    val i = s.lastIndexOf(".")
    val s1 = s.substring(0, i) + "T"
    val s2 = s.substring(i)
    s1 + s2

  def toroidalize(arg: BufferedImage) =
    val w = arg.getWidth()
    val h = arg.getHeight()
    val res1 = BufferedImage(w, h, arg.getType())
    for i <- 0 to w - 1 do
      for j <- 0 to h - 1 do
        val c1 = Color(arg.getRGB(i, j))
        val c2 = Color(arg.getRGB(i, h - 1 - j))
        val cr = (c1.getRed() + c2.getRed()) / 2
        val cg = (c1.getGreen() + c2.getGreen()) / 2
        val cb = (c1.getBlue() + c2.getBlue()) / 2
        res1.setRGB(i, j, Color(cr, cg, cb).getRGB())
    val res2 = BufferedImage(w, h, arg.getType())
    for i <- 0 to w - 1 do
      for j <- 0 to h - 1 do
        val c1 = Color(res1.getRGB(i, j))
        val c2 = Color(res1.getRGB(w - 1 - i, j))
        val cr = (c1.getRed() + c2.getRed()) / 2
        val cg = (c1.getGreen() + c2.getGreen()) / 2
        val cb = (c1.getBlue() + c2.getBlue()) / 2
        res2.setRGB(i, j, Color(cr, cg, cb).getRGB())
    res2

  def grayscalize(arg: BufferedImage) =
    val w = arg.getWidth()
    val h = arg.getHeight()
    val res = BufferedImage(w, h, arg.getType())
    for i <- 0 to w - 1 do
      for j <- 0 to h - 1 do
        val c = Color(arg.getRGB(i, j))
        val g = (c.getRed() + c.getGreen() + c.getBlue()) / 3
        res.setRGB(i, j, Color(g, g, g).getRGB())
    res
