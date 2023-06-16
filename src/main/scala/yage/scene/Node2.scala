package yage.scene

object Node2:

  def apply(name: String): Node2 = apply(name, null, null, null)

  def apply(name: String, parentNode: Node2): Node2 = apply(name, parentNode, null, null)

  def apply(name: String, parentNode: Node2, rightSibling: Node2, leftChild: Node2): Node2 =
    val res = new Node2(name)
    res.parentNode = parentNode
    res.rightSibling = rightSibling
    res.leftChild = leftChild
    res

class Node2(var name: String = "node"):

  var parentNode: Node2 = null
  var rightSibling: Node2 = null
  var leftChild: Node2 = null
  // var upperNode: Node = null
  // var lowerNode: Node = null

  def firstSibling: Node2 = if parentNode == null then null else parentNode.leftChild
  def lastSibling: Node2 = if rightSibling == null then this else rightSibling.lastSibling

  def firstChild: Node2 = leftChild
  def lastChild: Node2 = if leftChild == null then null else leftChild.lastSibling

  def foreachSibling(f: Node2 => Unit): Unit =
    var n = firstSibling
    while n != null do
      if n != this then f(n)
      n = n.rightSibling

  def foreachChild(f: Node2 => Unit): Unit =
    var n = firstChild
    while n != null do
      f(n)
      n = n.rightSibling

  def foreachDescendantDF(f: Node2 => Unit): Unit =
    foreachChild(n =>
      f(n)
      n.foreachDescendantDF(f)
      )
      
  // needs work    
  def foreachDescendantBF(f: Node2 => Unit): Unit =
    foreachChild(n => f(n))
    foreachChild(n => n.foreachDescendantBF(f))
      
  def foreachAncestor(f: Node2 => Unit): Unit =
    var a = parentNode
    while a != null do
      f(a)
      a = a.parentNode

  def printTree(indent: Int): Unit =
    println(" " * indent + name)
    foreachChild(c => c.printTree(indent + 4))    
  
  override def toString() =
    val pn = if parentNode == null then "null" else parentNode.name
    val rs = if rightSibling == null then "null" else rightSibling.name
    val lc = if leftChild == null then "null" else leftChild.name
    s"($name: pn = $pn, rs = $rs, lc = $lc)"
