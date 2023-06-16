package yage.graphics.stb.font

/**
 * min = left bottom
 * max = right top 
 */

case class Box(min: Point, max: Point)

object Box:

  def apply(minX: Int, minY: Int, maxX: Int, maxY: Int) =
    new Box(Point(minX, minY), Point(maxX, maxY))

