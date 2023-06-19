package test.image.ui

import javax.swing.JFrame
import javax.swing.WindowConstants
import yage.image.grid.Grid
import yage.image.grid.Pixel
import yage.image.ui.ImageView
import yage.image.ui.IntImageView
import yage.image.ImageI

object IntImageViewTest:

  def main(args: Array[String]) =
    val img = ImageI(Pixel(0, 0), Pixel(2, 3))
    img(Pixel(0, 1)) = 1
    img(Pixel(2, 1)) = 1
    println(img)
    println(img.isToroidal)
    val f = new JFrame
    f.getContentPane.add(new IntImageView(img))
    f.setTitle("ImageView Test")
    f.setBounds(0, 0, 800, 600)
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    f.setVisible(true)
