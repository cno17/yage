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

object FontTest extends JPanel:

  val font2 = Font(Font.MONOSPACED, Font.PLAIN, 100)
  val img = createImage()

  override def paint(g: Graphics) =
    super.paint(g)
    val g2d = g.asInstanceOf[Graphics2D]
    g2d.drawImage(img, 0, 0, null)
    // g2d.drawLine(0, 0, img.getWidth(), img.getHeight())
    // g2d.drawString("here", 20,20);

  def main(args: Array[String]) =
    val f = new JFrame
    f.getContentPane.add(FontTest)
    f.setTitle("Font Test")
    f.setBounds(0, 0, 800, 600)
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    f.setVisible(true)

  def createImage() =
    val s = "AgixpQwi"
    val f = Font(Font.MONOSPACED, Font.PLAIN, 100)
    val frc = FontRenderContext(AffineTransform(), false, false)
    val r2d = f.getStringBounds(s, frc)
    println(r2d)
    val img = BufferedImage(r2d.getWidth().toInt, r2d.getHeight().toInt, BufferedImage.TYPE_INT_ARGB)
    val g2d = img.createGraphics().asInstanceOf[Graphics2D]
    g2d.setStroke(BasicStroke(2))
    g2d.setPaint(Color.BLACK)
    g2d.fillRect(0, 0, img.getWidth(), img.getHeight())
    g2d.setPaint(Color.GREEN)
    g2d.setFont(f)
    g2d.translate(-r2d.getMinX(), -r2d.getMinY())
    g2d.drawString(s, 0, 0)
    g2d.draw(r2d)
    img