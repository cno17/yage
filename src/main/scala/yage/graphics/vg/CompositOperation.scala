package yage.graphics.vg

import org.lwjgl.nanovg.NanoVG.NVG_ATOP
import org.lwjgl.nanovg.NanoVG.NVG_COPY
import org.lwjgl.nanovg.NanoVG.NVG_DESTINATION_ATOP
import org.lwjgl.nanovg.NanoVG.NVG_DESTINATION_IN
import org.lwjgl.nanovg.NanoVG.NVG_DESTINATION_OUT
import org.lwjgl.nanovg.NanoVG.NVG_DESTINATION_OVER
import org.lwjgl.nanovg.NanoVG.NVG_LIGHTER
import org.lwjgl.nanovg.NanoVG.NVG_SOURCE_IN
import org.lwjgl.nanovg.NanoVG.NVG_SOURCE_OUT
import org.lwjgl.nanovg.NanoVG.NVG_SOURCE_OVER
import org.lwjgl.nanovg.NanoVG.NVG_XOR

// BlendOp, BlendMode

enum CompositOperation(val id: Int):

  case SrcOver extends CompositOperation(NVG_SOURCE_OVER)
  case SrcIn extends CompositOperation(NVG_SOURCE_IN)
  case SrcOut extends CompositOperation(NVG_SOURCE_OUT)
  case Atop extends CompositOperation(NVG_ATOP) // SrcAtop?
  case DstOver extends CompositOperation(NVG_DESTINATION_OVER)
  case DstIn extends CompositOperation(NVG_DESTINATION_IN)
  case DstOut extends CompositOperation(NVG_DESTINATION_OUT)
  case DstAtop extends CompositOperation(NVG_DESTINATION_ATOP)
  case Lighter extends CompositOperation(NVG_LIGHTER)
  case Copy extends CompositOperation(NVG_COPY)
  case Xor extends CompositOperation(NVG_XOR)
