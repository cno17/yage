package yage.graphics.gl.shader.program

import java.nio.FloatBuffer
import java.nio.IntBuffer
import org.lwjgl.opengl.GL11C.glGetIntegerv
import org.lwjgl.opengl.GL20C.glGetUniformLocation
import org.lwjgl.opengl.GL41C.*
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryStack.stackMallocInt
import org.lwjgl.system.MemoryStack.stackPush
import scala.language.postfixOps

// todo: uint versions!

trait UniformSetter:

  this: Program =>

  def loc(name: String) =
    val res = glGetUniformLocation(id, name)
    if res == -1 then println(s"unknown uniform \"$name\"")
    res

  // REFERENCE BY LOCATION

  def setUniform1i(loc: Int, v1: Int) = glProgramUniform1i(id, loc, v1)
  def setUniform1i(loc: Int, vs: IntBuffer) = glProgramUniform1iv(id, loc, vs)
  def setUniform2i(loc: Int, v1: Int, v2: Int) = glProgramUniform2i(id, loc, v1, v2)
  def setUniform2i(loc: Int, vs: IntBuffer) = glProgramUniform2iv(id, loc, vs)
  def setUniform3i(loc: Int, v1: Int, v2: Int, v3: Int) = glProgramUniform3i(id, loc, v1, v2, v3)
  def setUniform3i(loc: Int, vs: IntBuffer) = glProgramUniform3iv(id, loc, vs)
  def setUniform4i(loc: Int, v1: Int, v2: Int, v3: Int, v4: Int) = glProgramUniform4i(id, loc, v1, v2, v3, v4)
  def setUniform4i(loc: Int, vs: IntBuffer) = glProgramUniform4iv(id, loc, vs)

  def setUniform1f(loc: Int, v1: Float) = glProgramUniform1f(id, loc, v1)
  def setUniform1f(loc: Int, vs: FloatBuffer) = glProgramUniform1fv(id, loc, vs)
  def setUniform2f(loc: Int, v1: Float, v2: Float) = glProgramUniform2f(id, loc, v1, v2)
  def setUniform2f(loc: Int, vs: FloatBuffer) = glProgramUniform2fv(id, loc, vs)
  def setUniform3f(loc: Int, v1: Float, v2: Float, v3: Float) = glProgramUniform3f(id, loc, v1, v2, v3)
  def setUniform3f(loc: Int, vs: FloatBuffer) = glProgramUniform3fv(id, loc, vs)
  def setUniform4f(loc: Int, v1: Float, v2: Float, v3: Float, v4: Float) = glProgramUniform4f(id, loc, v1, v2, v3, v4)
  def setUniform4f(loc: Int, vs: FloatBuffer) = glProgramUniform4fv(id, loc, vs)

  def setUniform2x2f(loc: Int, vs: FloatBuffer) = glProgramUniformMatrix2fv(id, loc, false, vs)
  def setUniform3x3f(loc: Int, vs: FloatBuffer) = glProgramUniformMatrix3fv(id, loc, false, vs)
  def setUniform4x4f(loc: Int, vs: FloatBuffer) = glProgramUniformMatrix4fv(id, loc, false, vs)

  // REFERENCE BY NAME

  // TODO
  def setUniform1u(name: String, v1: Int) = glProgramUniform1ui(id, loc(name), v1)
  
  def setUniform1i(name: String, v1: Int) = glProgramUniform1i(id, loc(name), v1)
  def setUniform1i(name: String, vs: IntBuffer) = glProgramUniform1iv(id, loc(name), vs)
  def setUniform2i(name: String, v1: Int, v2: Int) = glProgramUniform2i(id, loc(name), v1, v2)
  def setUniform2i(name: String, vs: IntBuffer) = glProgramUniform2iv(id, loc(name), vs)
  def setUniform3i(name: String, v1: Int, v2: Int, v3: Int) = glProgramUniform3i(id, loc(name), v1, v2, v3)
  def setUniform3i(name: String, vs: IntBuffer) = glProgramUniform3iv(id, loc(name), vs)
  def setUniform4i(name: String, v1: Int, v2: Int, v3: Int, v4: Int) = glProgramUniform4i(id, loc(name), v1, v2, v3, v4)
  def setUniform4i(name: String, vs: IntBuffer) = glProgramUniform4iv(id, loc(name), vs)

  def setUniform1f(name: String, v1: Float) = glProgramUniform1f(id, loc(name), v1)
  def setUniform1f(name: String, vs: FloatBuffer) = glProgramUniform1fv(id, loc(name), vs)
  def setUniform2f(name: String, v1: Float, v2: Float) = glProgramUniform2f(id, loc(name), v1, v2)
  def setUniform2f(name: String, vs: FloatBuffer) = glProgramUniform2fv(id, loc(name), vs)
  def setUniform3f(name: String, v1: Float, v2: Float, v3: Float) = glProgramUniform3f(id, loc(name), v1, v2, v3)
  def setUniform3f(name: String, vs: FloatBuffer) = glProgramUniform3fv(id, loc(name), vs)
  def setUniform4f(name: String, v1: Float, v2: Float, v3: Float, v4: Float) =
    glProgramUniform4f(id, loc(name), v1, v2, v3, v4)
  def setUniform4f(name: String, vs: FloatBuffer) = glProgramUniform4fv(id, loc(name), vs)

  def setUniform2x2f(name: String, vs: FloatBuffer) = glProgramUniformMatrix2fv(id, loc(name), false, vs)
  def setUniform3x3f(name: String, vs: FloatBuffer) = glProgramUniformMatrix3fv(id, loc(name), false, vs)
  def setUniform4x4f(name: String, vs: FloatBuffer) = glProgramUniformMatrix4fv(id, loc(name), false, vs)

// SAMPLE (JOML) INTEROP
/*
	def setMat4(name: String, matrix: Matrix4fc) =
		try {
			val stack = MemoryStack.stackPush
			try // glUniformMatrix4fv(GL20.glGetUniformLocation(id(), name), false, matrix.get(stack.mallocFloat(16)));
				glUniformMatrix4fv(loc(name), false, matrix.get(stack.mallocFloat(16)))
			finally if (stack != null) stack.close()
 */

/*

		def setUniform(loc: Int, vec: Nothing) = setUniform2f(loc, vec.x, vec.y)
		def setUniform(loc: Int, vec: Nothing) = setUniform3f(loc, vec.x, vec.y, vec.z)
		def setUniform(loc: Int, vec: Nothing) = setUniform4f(loc, vec.x, vec.y, vec.z, vec.w)
		def setUniform(loc: Int, mat: Nothing) = glProgramUniformMatrix4fv(id, loc, false, toBuffer(mat))

		def setUniform(name: String, vec: Nothing) = setUniform2f(loc(name), vec.x, vec.y)
		def setUniform(name: String, vec: Nothing) = setUniform3f(loc(name), vec.x, vec.y, vec.z)
		def setUniform(name: String, vec: Nothing) = setUniform4f(loc(name), vec.x, vec.y, vec.z, vec.w)
		def setUniform(name: String, mat: Nothing) = glProgramUniformMatrix4fv(id, loc(name), false, toBuffer(mat))
		def setUniform(name: String, trafo: Nothing) = glProgramUniformMatrix4fv(id, loc(name), false, toBuffer(trafo.mat))
 */
