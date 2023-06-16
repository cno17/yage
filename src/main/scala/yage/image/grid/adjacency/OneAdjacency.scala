package yage.image.grid.adjacency

import scala.collection.mutable.ArrayBuffer
import yage.image.grid.Grid
import yage.image.grid.Pixel

object OneAdjacency:

  def apply(g: Grid, c: Pixel) =
    val adj = new OneAdjacency(g)
    adj.setCenter(c)
    adj

class OneAdjacency(g: Grid) extends Adjacency(g):

  override def createNeighbours = Array.fill(2 * dim)(Pixel(dim))

  override def updateNeighbours =
    for d <- 0 to dim - 1 do
      val neg = neighbours(2 * d + 0)
      val pos = neighbours(2 * d + 1)
      // grid.dec(d, neg.set(center))
      // grid.inc(d, pos.set(center))
