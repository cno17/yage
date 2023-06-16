package yage.graphics.fw

/**
 * The time increment dt should be given in milli seconds.
 */

class FrameCounter:

  var fps = 0

  protected var frameCount = 0
  protected var milliCount = 0

  def step(dt: Int) =
    frameCount += 1
    milliCount += dt
    if milliCount > 1000 then
      fps = (1000 * frameCount) / milliCount
      frameCount = 0
      milliCount = 0
