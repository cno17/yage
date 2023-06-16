package yage.graphics.stb.font

/**
 * // A cubic bezier curve segment describing a part of the outline of a glyph.
 */

case class Vertex(p: Point, cp1: Point, cp2: Point, typ: VertexType)
