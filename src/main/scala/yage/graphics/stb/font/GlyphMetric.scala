package yage.graphics.stb.font

/**
 * The horizontal measurements of a single glyph.
 *  
 * adv = horizontal advance width 
 * lsb = left side bearing
 */

// GlyphMetricH, bearing

case class GlyphMetric(advance: Int, leftSideBearing: Int)
