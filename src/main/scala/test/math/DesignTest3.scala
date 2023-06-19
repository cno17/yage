package test.math

object DesignTest3:

  trait MatBase[M <: MatBase[M]]:
    
    type Store = Array[Array[Float]]

    extension (s: Store) def apply(i: Int, j: Int) = s(i)(j)
    extension (sto: Store) def update(i: Int, j: Int, s: Float) = 0 // cmp(i)(j) = s

    extension (s: Store) def toZero() = s.foreach((i, j) => s(i, j) = 0)
    // extension (s: Store) def toOne() = s.foreach((i, j) => s(i, j) = if i == j then 1 else 0)

    extension (s: Store) def toRotX(a: Float) = 2
    
    extension (s: Store) def foreach(f: (Int, Int) => Unit) =
      for i <- 0 to n - 1 do
        for j <- 0 to n - 1 do
          f(i, j)
      asInstanceOf[M]

    extension (s: Store) def show() =
      val sb = new StringBuffer
      for i <- 0 to n - 1 do
        for j <- 0 to n - 1 do
          sb.append("f${s(i, j)}%10.2f")
        sb.append("\n")
      sb.toString

    def n: Int


  class Mat(val n: Int) extends MatBase[Mat]:
    var cmp: Store = Array.ofDim[Float](n, n) 

    def show(): String = cmp.show()

  def main(args: Array[String]) =
    val m = Mat(3)
    println(m.show())