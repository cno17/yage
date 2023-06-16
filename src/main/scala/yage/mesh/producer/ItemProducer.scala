package yage.mesh.producer

import yage.mesh.Edge
import yage.mesh.Face
import yage.mesh.Vertex

trait ItemProducer[V <: Vertex[V, E, F], E <: Edge[V, E, F], F <: Face[V, E, F]]:

  def newVertex(): V
  def newEdge(): E
  def newFace(): F
