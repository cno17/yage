package yage.graphics.vg

import org.lwjgl.nanovg.NanoVG.NVG_CCW
import org.lwjgl.nanovg.NanoVG.NVG_CW

enum PathWinding(val id: Int):
  case CW extends PathWinding(NVG_CW)
  case CCW extends PathWinding(NVG_CCW)
