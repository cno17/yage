package yage.image.producer

import yage.image.grid.Grid
import yage.image.producer.ImageProducer
import yage.image.Image

import scala.collection.mutable.ArrayBuffer

/**
 * An image operator takes one or more input images and produces an output image. 
 * The input images are not modified.
 */

trait ImageOperator[V] extends ImageProducer[V]:

  val args = ArrayBuffer[Image[?]]()

  def acceptsAsArg(img: Image[?]) = false

  def isReady = false
