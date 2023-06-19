package test.graphics

object EitherTest:

  def main(args: Array[String]) =
    val l: Either[String, Int] = Left("one")
    val r: Either[String, Int] = Right(2)
    for x <- l do println(x)
    for y <- r do println(y)