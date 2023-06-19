package test.image

import yage.image.*
import yage.image.grid.Pixel
import yage.image.producer.RandomGeneratorF

object ImageTest:

  def main(args: Array[String]) = test3

  def test1 =
    val img = ImageI(Pixel(10, 10, 10), Pixel(20, 20, 20))
    img(Pixel(12, 10, 13)) = 2
    img(Pixel(12, 20, 13)) = 3
    val t1 = System.currentTimeMillis()
    println(img.isToroidal)
    val t2 = System.currentTimeMillis()
    println(t2 - t1)

  def test2 =
    val img = ImageI(Pixel(2, 2), Pixel(4, 3))
    img.foreachI(i => img(i) += i)
    println(img)

  def test3 =
    val img1 = ImageF(Pixel(Array(10, 20, 30)), Pixel(Array(20, 50, 80)))
    val img2 = img1.slice(0, 12)
    println(2)