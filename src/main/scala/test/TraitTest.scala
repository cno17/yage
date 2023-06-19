package test.core

object TraitTest:

  trait DefaultConstructible[T]:    
    def apply(): T

  object Point: //  extends DefaultConstructible[Point]: 
    def apply() = new Point(0, 0)

  class Point(var x: Int, var y: Int) // extends DefaultConstructible[Point]
    // override def apply() = this


  def main(args: Array[String]) =
    println(2)
