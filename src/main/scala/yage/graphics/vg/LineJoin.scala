package yage.graphics.vg

import org.lwjgl.nanovg.NanoVG.NVG_BEVEL
import org.lwjgl.nanovg.NanoVG.NVG_MITER
import org.lwjgl.nanovg.NanoVG.NVG_ROUND

enum LineJoin(val id: Int):

  case Bevel extends LineJoin(NVG_BEVEL)
  case Miter extends LineJoin(NVG_MITER)
  case Round extends LineJoin(NVG_ROUND)
