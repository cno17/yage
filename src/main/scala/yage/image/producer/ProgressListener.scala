package yage.image.producer

trait ProgressListener:

  def reached(percent: Double) = println(s"reached $percent[%]")
