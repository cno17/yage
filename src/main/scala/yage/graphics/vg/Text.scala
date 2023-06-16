package yage.graphics.vg

import org.lwjgl.nanovg.NanoVG.*
import java.io.File

class Text(val ctx: Long):

  // name could default to f.simpleName
  def loadFont(f: File, name: String) = nvgCreateFont(ctx, name, f.getAbsolutePath())

  def findFont(name: String) = nvgFindFont(ctx, name)

  def drawText(x: Float, y: Float, s: String) = nvgText(ctx, x, y, s)
  def drawTextBox(x: Float, y: Float, w: Float, s: String) = nvgTextBox(ctx, x, y, w, s)

  // return box and (!) horizontal advance
  def getTextBounds = 0  
  def getTextBoxBounds = 0  

  def getTextGlyphPositions(x: Float, y: Float, text: String) = 0

  // based on current state
  def getTextMetric = 0 // asc, desc, lineHight

  def breakText(text: String, width: Float) = 0 // nvgTextBreakLines
