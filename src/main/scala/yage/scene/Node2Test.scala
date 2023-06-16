package yage.scene

object Node2Test:

/*
a0
  b0
    c0
    c1
  b1
    c2
    c3
      d0
      d1
      d2
  b2
    c4
*/

  def main(args: Array[String]): Unit =
    val a0 = Node2("a0")
    val b0 = Node2("b0", a0)
    val b1 = Node2("b1", a0)
    val b2 = Node2("b2", a0)
    val c0 = Node2("c0", b0)
    val c1 = Node2("c1", b0)
    val c2 = Node2("c2", b1)
    val c3 = Node2("c3", b1)
    val c4 = Node2("c4", b2)
    val d0 = Node2("d0", c3)
    val d1 = Node2("d1", c3)
    val d2 = Node2("d2", c3)
    a0.leftChild = b0
    b0.rightSibling = b1
    b0.leftChild = c0
    b1.rightSibling = b2
    b1.leftChild = c2
    b2.leftChild = c4
    c0.rightSibling = c1
    c2.rightSibling = c3
    c3.leftChild = d0
    d0.rightSibling = d1
    d1.rightSibling = d2
    a0.foreachDescendantBF(d => println(d.name))