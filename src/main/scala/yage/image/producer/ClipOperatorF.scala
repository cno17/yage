package yage.image.producer

import yage.image.grid.Grid
import yage.image.producer.ImageProducer
import yage.image.Image
import yage.image.ImageF
import yage.image.producer.ImageOperator

import scala.collection.mutable.ArrayBuffer

/**
 * 0 <= min <= max <= 100 raise/inc floor, lower/dec ceil
 */

class ClipOperatorF extends ImageOperator[Float]:

  var minV = 20.0f
  var maxV = 80.0f

  override def acceptsAsArg(img: Image[?]) = img.isInstanceOf[ImageF]

  override def isReady = args.size == 1

  override def exec =
    val arg = args(0).asInstanceOf[ImageF]
    val res = ImageF(arg.min, arg.max)
    val a1 = arg.values.min
    val b1 = arg.values.max
    val a2 = a1 + (b1 - a1) * minV / 100
    val b2 = a1 + (b1 - a1) * maxV / 100
    // TODO oneliner!
    res.foreachI(i =>
      var v = arg(i)
      v = Math.max(v, a2)
      v = Math.min(v, b2)
      res(i) = v
    )
    res
