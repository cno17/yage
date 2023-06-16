package yage.image.ui

import java.awt.Graphics2D

trait CellRenderer[V]:

  def draw(c: Cell, v: V, g: Graphics2D): Unit
