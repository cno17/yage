package yage.image.ui

object Cell:

  def apply() = new Cell(0, 0, 0, 0)

class Cell(var x1: Double, var y1: Double, var x2: Double, var y2: Double):

  def :=(x1: Double, y1: Double, x2: Double, y2: Double) =
    this.x1 = x1
    this.y1 = y1
    this.x2 = x2
    this.y2 = y2
    this
