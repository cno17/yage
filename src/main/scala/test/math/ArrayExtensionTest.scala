package test.math

object ArrayExtensionTest:

  type Storage = Array[Array[Float]]

  extension (s: Storage) def apply(i: Int, j: Int) = s(i)(j)
  extension (s: Storage) def toRotX(a: Float) = 2

  def main(args: Array[String]) =
    var cmp: Storage = Array.ofDim[Float](3, 3)
    cmp.toRotX(2.0f)
    println(cmp(0, 0))
