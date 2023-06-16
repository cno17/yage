package yage.graphics.gl.shader

import org.lwjgl.opengl.GL20C.GL_FRAGMENT_SHADER
import org.lwjgl.opengl.GL20C.GL_VERTEX_SHADER
import org.lwjgl.opengl.GL32C.GL_GEOMETRY_SHADER
import org.lwjgl.opengl.GL40C.GL_TESS_CONTROL_SHADER
import org.lwjgl.opengl.GL40C.GL_TESS_EVALUATION_SHADER
import org.lwjgl.opengl.GL43C.GL_COMPUTE_SHADER

object ShaderStage:

  def apply(id: Int) = ShaderStage.values.find(_.id == id).get
  def apply(ext: String) = ShaderStage.values.find(_.ext == ext).get

enum ShaderStage(val id: Int, val ext: String):

  case Vertex extends ShaderStage(GL_VERTEX_SHADER, "vert")
  case TessControl extends ShaderStage(GL_TESS_CONTROL_SHADER, "tesc")
  case TessEvaluation extends ShaderStage(GL_TESS_EVALUATION_SHADER, "tese")
  case Geometry extends ShaderStage(GL_GEOMETRY_SHADER, "geom")
  case Fragment extends ShaderStage(GL_FRAGMENT_SHADER, "frag")
  case Compute extends ShaderStage(GL_COMPUTE_SHADER, "comp")
