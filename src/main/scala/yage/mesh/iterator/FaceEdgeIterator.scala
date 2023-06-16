package yage.mesh.iterator

import yage.mesh.Edge
import yage.mesh.Face
import yage.mesh.Vertex

/**
 * Iterate through the adjacent vertices of a given vertex.
 */

class FaceEdgeIterator[V <: Vertex[V, E, F], E <: Edge[V, E, F], F <: Face[V, E, F]](var face: F) extends ItemIterator[E]:

  protected var edge = face.edge.succ

  override def hasNext = edge != face.edge 
  
  override def next = {edge = edge.succ; edge}

