package test.graphics.fw

import scala.collection.mutable.ListBuffer

object Test:

  def main(args: Array[String]) =

    val movedListeners = ListBuffer[(Double, Double) => Unit]()
    movedListeners.foreach(f => f(0, 0))
    movedListeners.foreach(_(0, 0))
