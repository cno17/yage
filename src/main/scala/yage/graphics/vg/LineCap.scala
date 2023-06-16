package yage.graphics.vg

import org.lwjgl.nanovg.NanoVG.NVG_BEVEL
import org.lwjgl.nanovg.NanoVG.NVG_BUTT
import org.lwjgl.nanovg.NanoVG.NVG_MITER
import org.lwjgl.nanovg.NanoVG.NVG_ROUND
import org.lwjgl.nanovg.NanoVG.NVG_SQUARE

enum LineCap(val id: Int):

  case Butt extends LineCap(NVG_BUTT)
  case Round extends LineCap(NVG_ROUND)
  case Square extends LineCap(NVG_SQUARE)
  case Bevel extends LineCap(NVG_BEVEL)
  case Miter extends LineCap(NVG_MITER)
