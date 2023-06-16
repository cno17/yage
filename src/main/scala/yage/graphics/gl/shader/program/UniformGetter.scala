package yage.graphics.gl.shader.program

import org.lwjgl.opengl.GL20C.glGetUniformf
import org.lwjgl.opengl.GL20C.glGetUniformi
import org.lwjgl.opengl.GL20C.glGetUniformLocation

trait UniformGetter:

  this: Program =>

  def getUniformf(name: String) = glGetUniformf(id, glGetUniformLocation(id, name))
  def getUniformi(name: String) = glGetUniformi(id, glGetUniformLocation(id, name))
