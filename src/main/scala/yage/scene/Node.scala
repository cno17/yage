package yage.scene

import scala.collection.mutable.ArrayBuffer
import yage.scene.geometry.Geometry
import yage.math.Mat4

object Node:

  def apply(parent: Node) =
    val res = new Node()
    res.parent = Some(parent)
    res

  def apply(parent: Node, children: Array[Node]) =
    val res = new Node()
    res.parent = Some(parent)
    res.children = Some(children)
    res

  def apply(parent: Node, geometry: Geometry) =
    val res = new Node()
    res.parent = Some(parent)
    res.geometry = Some(geometry)
    res

  def apply(parent: Node, children: Array[Node], geometry: Geometry) =
    val res = new Node()
    res.parent = Some(parent)
    res.children = Some(children)
    res.geometry = Some(geometry)
    res

class Node:

  var parent: Option[Node] = None
  var children: Option[Array[Node]] = None
  var geometry: Option[Geometry] = None
  var matPN = Mat4()
  var matWN = Mat4()

  def isRoot = parent == None
  def isLeaf = children == None

  // def matWP = if parent == None then

  def forallAncestors(f: Node => Unit): Unit =
    for p <- parent do
      f(p)
      p.forallAncestors(f)

  def forallSiblings(f: Node => Unit): Unit =
    for p <- parent; cs <- p.children; c <- cs do
      if c != this then f(c)

  def forallDescentants(f: Node => Unit): Unit =
    for cs <- children; c <- cs do
      f(c)
      c.forallDescentants(f)

  def updateTrafos(): Unit =
    for p <- parent do 
      matWN =* (p.matWN, matPN)
    forallDescentants(n => n.updateTrafos())

  def drawGeometries(): Unit =
    for g <- geometry do
      g.draw()
    forallDescentants(n => n.drawGeometries())

  def numAncestors = 0
  def numDescendents = 0

  def level = 0 // height of subtree?