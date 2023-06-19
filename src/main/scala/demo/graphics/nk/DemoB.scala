package demo.graphics.nk

import org.lwjgl.nuklear.*
import org.lwjgl.system.*

import java.nio.*
import java.text.*

import org.lwjgl.nuklear.Nuklear.*
import org.lwjgl.system.MemoryStack.*
import org.lwjgl.system.MemoryUtil.*
import yage.graphics.StackUser

// TODO: needs work

class DemoB extends StackUser:

    val NUMS = "789456123"
    val OPS  = "+-*/"

    var prev = 'x'
    var op = 'x'

    var set = false

    var a = Array(1.0)
    var b = Array(1.0)
    var current = a

    var format = DecimalFormat()    
    format.setGroupingUsed(false)
    format.setDecimalSeparatorAlwaysShown(true)
    format.setMinimumFractionDigits(2)
    format.setMaximumFractionDigits(2)
    // format.getDecimalFormatSymbols().setDecimalSeparator('.') ???
    val dfs = format.getDecimalFormatSymbols()
    dfs.setDecimalSeparator('.')
    format.setDecimalFormatSymbols(dfs)
    
    var numberFilter = NkPluginFilter.create(Nuklear.nnk_filter_float)

    def layout(ctx: NkContext, x: Int, y: Int) =
      useStack(stack =>
        val rect = NkRect.malloc(stack)
        val flags = NK_WINDOW_BORDER | NK_WINDOW_NO_SCROLLBAR | NK_WINDOW_MOVABLE
        if nk_begin(ctx, "Calculator", nk_rect(x.toFloat, y.toFloat, 180, 250, rect), flags) then
          nk_layout_row_dynamic(ctx, 35, 1)
          val buffer = stack.calloc(256)
          val length = 0 // TODO memASCII(format.format(current(0), false, buffer)
          val len = stack.ints(length)
          nk_edit_string(ctx, NK_EDIT_SIMPLE, buffer, len, 255, numberFilter)
          try
            current(0) = format.parse(memASCII(buffer, len.get(0))).doubleValue()
          catch
            case e: ParseException => e.printStackTrace()
          nk_layout_row_dynamic(ctx, 35, 4)

          var solve = false
          for i <- 0 to 15 do
            if i >= 12 && i < 15 then
            // TODO if i > 12 then continue
            if nk_button_label(ctx, "C") then
              // a(0) = b(0) = op = 0.0
              current = a
              set = false
            if nk_button_label(ctx, "0") then
              current(0) *= 10.0f
              set = false
            if nk_button_label(ctx, "=") then
              solve = true
              prev = op
              op = 0
            else if ((i + 1) % 4) != 0 then
              if nk_button_text(ctx, Character.toString(NUMS.charAt((i / 4) * 3 + i % 4))) then
                current(0) = current(0) * 10.0f + (NUMS.charAt((i / 4) * 3 + i % 4) - '0')
                set = false
            else if nk_button_text(ctx, Character.toString(OPS.charAt(i / 4))) then
              if !set then
                if current != b then current = b
              else 
                prev = op
                solve = true
          
            op = OPS.charAt(i / 4)
            set = true
            if solve then
              if prev == '+' then a(0) += b(0)    
              if prev == '-' then a(0) -= b(0)
              if prev == '*' then a(0) *= b(0)
              if prev == '/' then a(0) /= b(0)
              current = a
              if set then
                current = b
                b(0) = 0
                set = false
        nk_end(ctx)
      )