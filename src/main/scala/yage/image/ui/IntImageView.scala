package yage.image.ui

import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionAdapter
import java.awt.event.MouseWheelEvent
import java.awt.event.MouseWheelListener
import java.awt.geom.AffineTransform
import java.awt.geom.Rectangle2D
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel
import scala.collection.mutable.ListBuffer
import yage.image.grid.Pixel
import yage.image.ImageI

/**
 * preconditions: all images must ... be 2-dimensional have the same size
 */

class IntImageView(img: ImageI) extends JPanel:

  val pixel = Pixel(0, 0)
  val cell = Cell()

  var traX = 0.0
  var traY = 0.0
  var scaX = 1.0
  var scaY = 1.0
  var trafo = new AffineTransform

  def sizeX = getWidth
  def sizeY = getHeight

  addComponentListener(new ComponentListenerImpl)

  override def paint(g: Graphics) =
    val g2d = g.asInstanceOf[Graphics2D]
    val r2d = new Rectangle2D.Double
    val numX = img.size(0)
    val numY = img.size(1)
    val extX = sizeX / numX // TODO offsets, gaps
    val extY = sizeY / numY
    for i <- 0 to numX - 1 do
      cell.x1 = (i + 0) * extX
      cell.x2 = (i + 1) * extX
      for j <- 0 to numY - 1 do
        pixel := (i, j)
        cell.y1 = (j + 0) * extX
        cell.y2 = (j + 1) * extX
        r2d.setFrameFromDiagonal(cell.x1, cell.y1, cell.x2, cell.y2)
        g2d.draw(r2d)

  class ComponentListenerImpl extends ComponentAdapter:

    override def componentResized(e: ComponentEvent) =
      println("resized")

  class MouseMotionListenerImpl extends MouseMotionAdapter:

    override def mouseMoved(e: MouseEvent) =
      println()

    override def mouseDragged(e: MouseEvent) =
      println()

  class MouseWheelListenerImpl extends MouseWheelListener:

    override def mouseWheelMoved(e: MouseWheelEvent) =
      println("wheel")
