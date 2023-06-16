package yage.image.util

import scala.util.Random as Rnd

// TODO move to math

trait Random:
  
  def rndI(min: Int, max: Int) = min + Rnd.nextInt(max - min + 1) // TODO better in [min, max)?
  def rndF(min: Float, max: Float) = min + Rnd.nextFloat * (max - min)
