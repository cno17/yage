package yage.image.grid.adjacency

import scala.collection.mutable.ArrayBuffer
import yage.image.grid.Grid
import yage.image.grid.Pixel

/**
 * What is the neighbourhood of a border pixel?
 */

abstract class Adjacency(val grid: Grid) extends Iterable[Pixel]:

  val dim = grid.dim
  val center = Pixel(dim)
  val neighbours = createNeighbours

  def setCenter(c: Pixel) =
    center := c
    updateNeighbours

  def createNeighbours: Array[Pixel]
  
  def updateNeighbours: Unit

  override def iterator = neighbours.iterator
