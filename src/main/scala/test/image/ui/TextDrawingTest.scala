package test.image.ui

import java.awt.geom.Rectangle2D
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants
import yage.image.grid.Grid
import yage.image.grid.Pixel
import yage.image.ui.ImageView
import yage.image.ui.IntImageView
import yage.image.ImageI

object TextDrawingTest:

  class TextView extends JPanel:

    val text = "123"
    val cell = new Rectangle2D.Double()

    setFont(new Font("Times New Roman", Font.PLAIN, 20))

    override def paint(g: Graphics) =
      super.paint(g)
      val g2d = g.asInstanceOf[Graphics2D]
      cell.setRect(50, 50, 200, 100)
      g2d.draw(cell)
      g2d.drawString(text, 50, 50)

  def main(args: Array[String]) =
    val f = new JFrame
    f.getContentPane.add(new TextView)
    f.setTitle("Text Drawing Test")
    f.setBounds(0, 0, 800, 600)
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    f.setVisible(true)
