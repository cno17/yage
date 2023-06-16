package yage.image.view

import java.awt.event.MouseWheelEvent
import java.awt.event.MouseWheelListener
import java.awt.geom.Ellipse2D
import java.awt.geom.Rectangle2D
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.SwingConstants
import javax.swing.WindowConstants
import java.awt.event.MouseMotionListener
import java.awt.event.MouseEvent
import java.awt.geom.Line2D
import java.io.File
import java.awt.event.KeyEvent

// C+K,C comment a block
// C+K,U uncomment

object ShapeView:

  def main(args: Array[String]): Unit =
    val f = JFrame()
    val v = ShapeView()
    f.setSize(800, 600)
    f.setVisible(true)
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    f.add(v)
    v.setFocusable(true)
    v.requestFocusInWindow()

class ShapeView extends View:

  val e2d = Ellipse2D.Double()
  e2d.setFrame(100, 100, 200, 100)

  override def draw(g2d: Graphics2D) =
    g2d.draw(e2d)
    
  override def keyPressed(e: KeyEvent) =
    trafo.setToIdentity()
    repaint()

  override def mouseDragged(e: MouseEvent) =
    val newX = e.getX()
    val newY = e.getY()
    val difX = newX - oldX
    val difY = newY - oldY
    if Math.abs(difX) < 20 && Math.abs(difY) < 20 then trafo.translate(difX, difY) 
    oldX = newX
    oldY = newY
    repaint()

  override def mouseWheelMoved(e: MouseWheelEvent) =
    val x = e.getX()
    val y = e.getY()
    if e.getWheelRotation() > 0 then zoomAt(x, y, 0.9) else zoomAt(x, y, 1.1)
    repaint()

