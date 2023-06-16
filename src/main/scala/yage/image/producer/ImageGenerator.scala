package yage.image.producer

import yage.image.Image
import yage.image.grid.Pixel
import yage.image.producer.ImageProducer

/**
 * An image generator produces an image from scratch. 
 * There are no input images, just some parameters.
 */

trait ImageGenerator[V](val min: Pixel, val max: Pixel) extends ImageProducer[V]
