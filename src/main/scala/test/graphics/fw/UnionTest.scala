package test.graphics.fw

import org.lwjgl.glfw.GLFW.*
import scala.collection.mutable.ArrayBuffer

object UnionTest:
  
  type MouseListener2d = (Double, Double) => Unit
  type MouseListener4d = (Double, Double, Double, Double) => Unit
  type MouseListener = MouseListener2d | MouseListener4d

  def mouseMoved2d(x: Double, y: Double) = {}  
  def mouseMoved4d(x: Double, y: Double, dx: Double, dy: Double) = {}  

  def main(args: Array[String]) =
    println(mouseMoved2d.isInstanceOf[MouseListener2d])
    // println(mouseMoved2d.isInstanceOf[MouseListener4d]) // Bloop Bug
