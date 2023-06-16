package yage.image

import yage.image.grid.Pixel

class ImageB(min: Pixel, max: Pixel) extends Image[Boolean](min, max):

  values = Array.fill(size)(false)
