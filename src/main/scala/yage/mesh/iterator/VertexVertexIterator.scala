package yage.mesh.iterator

import yage.mesh.Edge
import yage.mesh.Face
import yage.mesh.Vertex

/**
 * Iterate through the adjacent vertices of a given vertex.
 */

class VertexVertexIterator[V <: Vertex[V, E, F], E <: Edge[V, E, F], F <: Face[V, E, F]](var vertex: V) extends ItemIterator[V]:

  protected var edge = vertex.edge.twin.succ

  override def hasNext = edge != vertex.edge 
  
  override def next = {edge = edge.twin.succ; edge.end}

