package yage.image.ui

import java.awt.Graphics2D

class IntCellRenderer extends CellRenderer[Int]:

  override def draw(c: Cell, v: Int, g: Graphics2D) =
    println()
