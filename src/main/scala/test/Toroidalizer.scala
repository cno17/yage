package test

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

object Toroidalizer:

  class View extends JPanel:

    val image1 = ImageIO.read(File("src/main/resources/image/bird/Blackbird1.jpg"))
    // val image1 = ImageIO.read(File("src/main/resources/image/noir/Leyla.jpg"))
    val image2 = toroidalize(image1)
    // val image2 = grayscalize(toroidalize(image1))

    extension (img: BufferedImage)
      def foreach(f: (Int, Int) => Unit) =
        for i <- 0 to img.getWidth() - 1 do
          for j <- 0 to img.getHeight - 1 do
            f(i, j)   

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

    override def paint(g: Graphics) =
      super.paint(g)
      val pw = getWidth().toFloat
      val ph = getHeight().toFloat
      val iw = image2.getWidth() * 1.0f
      val ih = image2.getHeight() * 1.0f
      val sx = pw / iw
      val sy = ph / ih
      // val sca = Math.max(sx, sy)
      val g2d = g.asInstanceOf[Graphics2D]
      val trafo = AffineTransform.getScaleInstance(sx, sy)
      g2d.drawImage(image2, trafo, null)

  def main(args: Array[String]) =
    val f = JFrame()
    f.getContentPane().add(View())
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    f.setBounds(0, 0, 800, 600)
    f.setVisible(true)