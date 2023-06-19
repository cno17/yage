package test.math.noise

import yage.math.noise.SimplexNoise

import java.awt.image.BufferedImage
import java.awt.Color
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.WindowConstants

object SimplexNoiseTest:

  def main(args: Array[String]): Unit =
    test2

  def test1 =
    for i <- 0 to 10 do
      for j <- 0 to 10 do
        println(SimplexNoise.noise(i, j))
  
  def test2 =
    val f = JFrame()
    f.add(JLabel(ImageIcon(createBufferedImage(600, 600))))
    f.setVisible(true)
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    f.pack()

  def createBufferedImage(si: Int, sj: Int) =
    val img = BufferedImage(si, sj, BufferedImage.TYPE_INT_RGB)
    for i <- 0 to si - 1 do
      val x = i.toDouble / si
      for j <- 0 to sj - 1 do
        val y = j.toDouble / sj
        val n = (SimplexNoise.noise(x, y).toFloat + 1) / 2
        img.setRGB(i, j, Color(n, n, n).getRGB())
    img
