package yage.graphics.gl.primitive

import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL32C.*
import org.lwjgl.opengl.GL40C.*

enum PrimitiveMode(val value: Int):
  case Points extends PrimitiveMode(GL_POINTS)
  case Lines extends PrimitiveMode(GL_LINES)
  case LinesAdjacency extends PrimitiveMode(GL_LINES_ADJACENCY)
  case LineStrip extends PrimitiveMode(GL_LINE_STRIP)
  case LineStripAdjacency extends PrimitiveMode(GL_LINE_STRIP_ADJACENCY)
  case LineLoop extends PrimitiveMode(GL_LINE_LOOP)
  case Triangles extends PrimitiveMode(GL_TRIANGLES)
  case TrianglesAdjacency extends PrimitiveMode(GL_TRIANGLES_ADJACENCY)
  case TriangleStrip extends PrimitiveMode(GL_TRIANGLE_STRIP)
  case TriangleStripAdjacency extends PrimitiveMode(GL_TRIANGLE_STRIP_ADJACENCY)
  case TriangleFan extends PrimitiveMode(GL_TRIANGLE_FAN)
  case Patches extends PrimitiveMode(GL_PATCHES)
