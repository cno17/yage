package yage.graphics.gl

import yage.graphics.StackUser

trait Object extends StackUser:

  val id = create()

  def create(): Int
  def destroy(): Unit
