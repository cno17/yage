package yage.graphics.fw

import System.nanoTime

class Timer:

  def milliTime = dt / 1000000
  def microTime = dt / 1000

  def reset() = t0 = nanoTime()

  private var t0 = nanoTime()
  private def dt = nanoTime() - t0