package test.image

import yage.image.grid.Grid
import yage.image.grid.Pixel
import yage.image.ImageF
import yage.image.producer.HillGeneratorF
import yage.image.util.AwtAdapter

import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.WindowConstants
import scala.collection.mutable.ArrayBuffer

object HillGeneratorTest3D:
  
  var icons = createIcons

  def main(args: Array[String]) =
    val f = JFrame()
    f.setTitle("ImageView Test")
    f.getContentPane.add(JLabel(icons(0)))
    f.pack
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    f.setVisible(true)

  def createIcons =    
    val min = Pixel(0, 0, 0)
    val max = Pixel(100, 100, 100)
    val img = HillGeneratorF(min, max, 500, 10, 50).exec.asInstanceOf[ImageF]
    val buf = ArrayBuffer[ImageIcon]()
    for i <- img.min.i to img.max.i do
      buf += ImageIcon(AwtAdapter.toBufferedImage(img.slice(0, i)))
    buf.toArray  
        