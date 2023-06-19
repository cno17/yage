package test.graphics.vg.swing

import javax.swing.JFrame
import javax.swing.WindowConstants
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JSlider
import javax.swing.Box
import javax.swing.JComboBox

object Demo:

  def main(args: Array[String]): Unit =
    val f = JFrame()
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    f.setBounds(100, 100, 800, 600)
    f.setVisible(true)
    f.setTitle("Component Demo")
    val pane = f.getContentPane()
    pane.setLayout(BoxLayout(pane, BoxLayout.Y_AXIS))
    pane.add(JButton("Start"))
    pane.add(JButton("Repeat the Song"))
    pane.add(Box.createVerticalStrut(10))
    pane.add(JSlider(0, 100, 20))
    pane.add(Box.createVerticalStrut(10))
    val combo = JComboBox[String]()
    combo.addItem("a")
    combo.addItem("b")
    combo.addItem("c")
    pane.add(combo)
    pane.add(JButton("Keep"))


/*
    val items = java.util.Vector[String]()
    items.add("a")
    items.add("b")
    items.add("c")
    items.add("d")
    items.add("e")
*/
