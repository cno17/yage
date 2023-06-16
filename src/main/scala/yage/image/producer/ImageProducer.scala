package yage.image.producer

import yage.image.util.Random
import yage.image.Image

// drop type param?

/**
 * The common base class of ImageGenerator and ImageOperator.
 * TODO: progress listeners
 */

trait ImageProducer[V] extends Random:

  def name = ""
  def info = ""
  // def view: Unit
  // def edit: Unit
  def exec: Image[V]
