package test.graphics.gl.font

import javax.swing.JPanel
import java.awt.geom.Rectangle2D
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JFrame
import javax.swing.WindowConstants
import java.awt.image.BufferedImage
import java.awt.Color
import java.awt.Stroke
import java.awt.BasicStroke
import java.awt.font.FontRenderContext
import java.awt.geom.AffineTransform

object MeasureTest:

  def main(args: Array[String]) =
    val f = Font(Font.MONOSPACED, Font.PLAIN, 100)
    val frc = FontRenderContext(AffineTransform(), false, false)
    for i <- 0 to 255 do
      val c = i.toChar
      val s = c.toString
      val r = f.getStringBounds(s, frc)
      if Character.isDigit(c) || Character.isLetter(c) then
        println(s"$i: $s")
