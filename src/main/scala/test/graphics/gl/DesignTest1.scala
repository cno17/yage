package test.graphics.gl

import yage.graphics.Flags
import test.graphics.gl.DesignTest1.Buffer.MapFlags

object DesignTest1:

  object Buffer:

    enum Target:
      case Vertex
      case Index

    class MapFlags extends Flags:
      var read = false
      override def toInt() = 0


  def main(args: Array[String]) =
    println(Buffer.Target.Vertex)
    val mf = MapFlags() // why not Buffer.MapFlags?
    mf.read = true