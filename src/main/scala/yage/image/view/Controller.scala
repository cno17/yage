package yage.image.view

import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import java.awt.event.MouseWheelEvent
import java.awt.event.MouseWheelListener

trait Controller extends KeyListener, MouseListener, MouseMotionListener, MouseWheelListener:

  protected var oldX = 0
  protected var oldY = 0

  override def keyPressed(e: KeyEvent) = {}
  override def keyReleased(e: KeyEvent) = {}
  override def keyTyped(e: KeyEvent) = {}

  override def mouseClicked(e: MouseEvent) = {}
  override def mouseEntered(e: MouseEvent) = {}
  override def mouseExited(e: MouseEvent) = {}
  override def mousePressed(e: MouseEvent) = {}
  override def mouseReleased(e: MouseEvent) = {}

  override def mouseDragged(e: MouseEvent) = {}
  override def mouseMoved(e: MouseEvent) = {}

  override def mouseWheelMoved(e: MouseWheelEvent) = {}
