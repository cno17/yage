package yage.image.ui

import java.awt.geom.Rectangle2D
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel
import scala.collection.mutable.ListBuffer
import yage.image.grid.Pixel

/**
 * preconditions: all images must ... be 2-dimensional have the same size
 */

class ImageView extends JPanel:

  val items = new ListBuffer[ViewItem[_]]
  val r2d = new Rectangle2D.Double

  def sizeX = getWidth
  def sizeY = getHeight

  override def paint(g: Graphics) =
    if items.isEmpty then return
    val numX = items(0).image.size(0)
    val numY = items(0).image.size(1)
    val extX = sizeX / numX // TODO offsets, gaps
    val extY = sizeY / numY
    val g2d = g.asInstanceOf[Graphics2D]
    val pixel = Pixel(0, 0)
    val cell = Cell()
    for i <- 0 to numX - 1 do
      cell.x1 = (i + 0) * extX
      cell.x2 = (i + 1) * extX
      for j <- 0 to numY - 1 do
        pixel := (i, j)
        cell.y1 = (j + 0) * extX
        cell.y2 = (j + 1) * extX
        // for (item <- items) do
        // item.renderer.draw(cell, item.image(p), g2d)

  def validateItems: Boolean =
    if items.isEmpty then return true
    val s0 = items(0).image.size(0)
    val s1 = items(0).image.size(1)
    for item <- items do
      val img = item.image
      if img.dim != 2 then return false
      if img.size(0) != s0 then return false
      if img.size(1) != s1 then return false
    true
