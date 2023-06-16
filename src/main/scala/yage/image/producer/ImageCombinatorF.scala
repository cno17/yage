package yage.image.producer

import yage.image.Image
import yage.image.ImageF
import yage.image.grid.Pixel
import yage.image.grid.iterator.ForwardIterator
import yage.image.producer.ImageOperator

// add small image to large img

class ImageCombinatorF extends ImageOperator[Float]:

  override def acceptsAsArg(img: Image[?]) = args.size < 2 && img.isInstanceOf[ImageF]

  override def isReady = args.size == 2

  override def exec =
    val imgL = args(0).asInstanceOf[ImageF]
    val imgS = args(1).asInstanceOf[ImageF]
    val res = ImageF(Pixel(imgL.min), Pixel(imgL.max))
    for p <- ForwardIterator(res) do
      val v = imgS(p)
      imgL.multiApplyAt(add(v))(p)
    res


  def add(v: Float)(p: Pixel) = 0