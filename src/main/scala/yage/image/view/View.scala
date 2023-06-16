package yage.image.view

import javax.swing.JPanel
import java.awt.geom.AffineTransform
import java.awt.Graphics
import java.awt.Graphics2D

// C+K,C comment a block
// C+K,U uncomment

class View extends JPanel, Controller:

  val trafo = AffineTransform()
  
  addKeyListener(this)
  addMouseListener(this)
  addMouseMotionListener(this)
  addMouseWheelListener(this)

  // todo: needs work
  def zoomAt(x: Double, y: Double, z: Double) =
    val sx1 = trafo.getScaleX()
    val sy1 = trafo.getScaleY()
    val tx1 = trafo.getTranslateX()
    val ty1 = trafo.getTranslateY()
    val sx2 = sx1 * z
    val sy2 = sy1 * z
    val tx2 = x - sx2 * (x - tx1) / sx1
    val ty2 = y - sy2 * (y - ty1) / sy1
    trafo.setTransform(sx2, 0, 0, sy2, tx2, ty2)

  def draw(g2d: Graphics2D) = {}

  override def paint(g: Graphics) =
    super.paint(g)
    val g2d = g.asInstanceOf[Graphics2D]
    g2d.setTransform(trafo)
    draw(g2d)
  

