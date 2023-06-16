package yage.graphics.vg

import org.lwjgl.nanovg.NanoVG.NVG_ALIGN_BASELINE
import org.lwjgl.nanovg.NanoVG.NVG_ALIGN_BOTTOM
import org.lwjgl.nanovg.NanoVG.NVG_ALIGN_CENTER
import org.lwjgl.nanovg.NanoVG.NVG_ALIGN_LEFT
import org.lwjgl.nanovg.NanoVG.NVG_ALIGN_RIGHT
import org.lwjgl.nanovg.NanoVG.NVG_ALIGN_TOP

enum TextAlign(val id: Int):

  // H
  case Left extends TextAlign(NVG_ALIGN_LEFT)
  case Center extends TextAlign(NVG_ALIGN_CENTER)
  case Right extends TextAlign(NVG_ALIGN_RIGHT)
  
  // V
  case Top extends TextAlign(NVG_ALIGN_TOP)
  case Baseline extends TextAlign(NVG_ALIGN_BASELINE)
  // case Middle extends TextAlign(NVG_ALIGN_)
  case Bottom extends TextAlign(NVG_ALIGN_BOTTOM)
