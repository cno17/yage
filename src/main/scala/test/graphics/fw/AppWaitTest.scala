package test.graphics.fw

import org.lwjgl.opengl.GL11C.*
import yage.graphics.fw.App
import yage.graphics.fw.AppCreateInfo
import yage.graphics.fw.EventMode

object AppWaitTest extends App:

  override def info() =
    val res = AppCreateInfo()
    res.eventMode = EventMode.Wait
    res

  override def init() =
    println("init")
    glClearColor(0.0f, 1.0f, 0.0f, 1.0f)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
    println("draw")
