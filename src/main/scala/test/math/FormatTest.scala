package test.math

object FormatTest:

  def main(args: Array[String]) =
    val a = 250.13456
    val b = -1024.7
    println(f"$a%+8.2f")
    println(f"$b%+8.2f")
