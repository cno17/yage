package demo.graphics.nk

import java.nio._

import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.Callbacks._
import org.lwjgl.glfw.GLFW._
import org.lwjgl.nuklear._
import org.lwjgl.nuklear.Nuklear._
import org.lwjgl.opengl.{GL, GLUtil, KHRDebug}
import org.lwjgl.system.Platform
import org.lwjgl.opengl.ARBDebugOutput._
import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.GL12._
import org.lwjgl.opengl.GL13._
import org.lwjgl.opengl.GL14._
import org.lwjgl.opengl.GL15._
import org.lwjgl.opengl.GL20._
import org.lwjgl.opengl.GL30._
import org.lwjgl.opengl.GL43._
import org.lwjgl.stb.{STBTTAlignedQuad, STBTTFontinfo, STBTTPackContext, STBTTPackedchar}
import org.lwjgl.stb.STBTruetype._
import org.lwjgl.system.MemoryUtil._
import java.io.File
import yage.util.FileExt

import yage.graphics.StackUser
import yage.graphics.gl.shader.program.Program

object Main8 extends FileExt, StackUser:

  val BUFFER_INITIAL_SIZE: Int = 4 * 1024

  val MAX_VERTEX_BUFFER: Int  = 512 * 1024
  val MAX_ELEMENT_BUFFER: Int = 128 * 1024

  val allocator = NkAllocator.create()
  allocator.alloc((handle: Long, old: Long, size: Long) => nmemAlloc(size))
  allocator.mfree((handle: Long, ptr: Long) => nmemFree(ptr))

  val vertexLayout = NkDrawVertexLayoutElement.create(4)
  vertexLayout.position(0).attribute(NK_VERTEX_POSITION).format(NK_FORMAT_FLOAT).offset(0)
  vertexLayout.position(1).attribute(NK_VERTEX_TEXCOORD).format(NK_FORMAT_FLOAT).offset(8)
  vertexLayout.position(2).attribute(NK_VERTEX_COLOR).format(NK_FORMAT_R8G8B8A8).offset(16)
  vertexLayout.position(3).attribute(NK_VERTEX_ATTRIBUTE_COUNT).format(NK_FORMAT_COUNT).offset(0)
  vertexLayout.flip()

  val ctx = NkContext.create()
  val fontInfo = STBTTFontinfo.create()
  val cdata = STBTTPackedchar.create(95)
  val font = NkUserFont.create()
  val texture = NkDrawNullTexture.create()
  val cmds = NkBuffer.create()

  val BITMAP_W = 1024
  val BITMAP_H = 1024
  val FONT_HEIGHT = 18.0f
  var scale = 0.0 // font scale
  var descent = 0.0

  var win : Long = 0
  var vbo = 0
  var vao = 0
  var ebo = 0
  var prog = 0
  var uniform_tex = 0
  var uniform_proj = 0
  val demo = DemoA("demo")
  

  def main(args: Array[String]) = 
    GLFWErrorCallback.createPrint().set()
    glfwInit()
    glfwDefaultWindowHints()
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3)
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3)
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
    glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE)

    win = glfwCreateWindow(800, 800, "GLFW Nuklear Demo", NULL, NULL)
    glfwMakeContextCurrent(win)
    val caps = GL.createCapabilities()

    glDebugMessageControl(GL_DEBUG_SOURCE_API, GL_DEBUG_TYPE_OTHER, GL_DEBUG_SEVERITY_NOTIFICATION, null: IntBuffer, false)

    val debugProc = GLUtil.setupDebugMessageCallback()

    setupWindow(win)

    val fontTexID = glGenTextures
    
    val fontFile = File("src/main/resources/font/FiraSans-Regular.ttf")
    val ttf = fontFile.bytes

    useStack(stack =>
      stbtt_InitFont(fontInfo, ttf)
      scale = stbtt_ScaleForPixelHeight(fontInfo, FONT_HEIGHT)
      val d = stack.mallocInt(1)
      stbtt_GetFontVMetrics(fontInfo, null, d, null)
      descent = d.get(0) * scale
      val bitmap = memAlloc(BITMAP_W * BITMAP_H)
      val pc = STBTTPackContext.malloc(stack)
      stbtt_PackBegin(pc, bitmap, BITMAP_W, BITMAP_H, 0, 1, NULL)
      stbtt_PackSetOversampling(pc, 4, 4)
      stbtt_PackFontRange(pc, ttf, 0, FONT_HEIGHT, 32, cdata)
      stbtt_PackEnd(pc)

      // Convert R8 to RGBA8
      val texture = memAlloc(BITMAP_W * BITMAP_H * 4)
      (0 until bitmap.capacity()).foreach { i =>
        texture.putInt((bitmap.get(i) << 24) | 0x00FFFFFF)
      }
      texture.flip()

      glBindTexture(GL_TEXTURE_2D, fontTexID)
      glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, BITMAP_W, BITMAP_H, 0, GL_RGBA, GL_UNSIGNED_INT_8_8_8_8_REV, texture)
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR)

      memFree(texture)
      memFree(bitmap)
    )
    font.width(onTextWidth)
    font.height(FONT_HEIGHT)
    font.query(onQueryFontGlyph)
    font.texture().id(fontTexID)

    nk_style_set_font(ctx, font)

    glfwShowWindow(win)

    while !glfwWindowShouldClose(win) do
      // Input
      newFrame()
      demo.layout(ctx, 50, 50)
      useStack(stack =>
        val bg = stack.mallocFloat(4)
        nk_color_fv(bg, demo.background)
        val width = stack.mallocInt(1)
        val height = stack.mallocInt(1)
        glfwGetWindowSize(win, width, height)
        glViewport(0, 0, width.get(0), height.get(0))
        glClearColor(bg.get(0), bg.get(1), bg.get(2), bg.get(3))
      )
      glClear(GL_COLOR_BUFFER_BIT)
      /*
       * IMPORTANT: `nk_glfw_render` modifies some global OpenGL state
       * with blending, scissor, face culling, depth test and viewport and
       * defaults everything back into a default state.
       * Make sure to either a.) save and restore or b.) reset your own state after
       * rendering the UI.
       */
      render(MAX_VERTEX_BUFFER, MAX_ELEMENT_BUFFER)
      glfwSwapBuffers(win)
    
    shutdown()

    glfwFreeCallbacks(win)
    if debugProc != null then debugProc.free()
    glfwTerminate()
    glfwSetErrorCallback(null).free()
    println ("Done")
  
  def destroy() : Unit = {
    glDeleteProgram(prog)
    glDeleteTextures(font.texture().id())
    glDeleteTextures(texture.texture().id())
    glDeleteBuffers(vbo)
    glDeleteBuffers(ebo)
    nk_buffer_free(cmds)
  }

  def shutdown() : Unit = {
    ctx.clip().copy().free()
    ctx.clip().paste().free()
    nk_free(ctx)
    destroy()
    font.query().free()
    font.width().free()
    allocator.alloc().free()
    allocator.mfree().free()
  }

  def newFrame() =
    nk_input_begin(ctx)
    glfwPollEvents()
    val mouse = ctx.input().mouse()
    if mouse.grab() then
      glfwSetInputMode(win, GLFW_CURSOR, GLFW_CURSOR_HIDDEN)
    else if mouse.grabbed() then
      val prevX = mouse.prev().x()
      val prevY = mouse.prev().y()
      glfwSetCursorPos(win, prevX, prevY)
      mouse.pos().x(prevX)
      mouse.pos().y(prevY)
    else if mouse.ungrab() then
      glfwSetInputMode(win, GLFW_CURSOR, GLFW_CURSOR_NORMAL)
    nk_input_end(ctx)
  
  def render(max_vertex_buffer: Int, max_element_buffer: Int) =
    // move to init!
    glEnable(GL_BLEND)
    glBlendEquation(GL_FUNC_ADD)
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
    glActiveTexture(GL_TEXTURE0)

    // allocate vertex and element buffer - aber doch nicht hier !!!
    glBindVertexArray(vao)
    glBindBuffer(GL_ARRAY_BUFFER, vbo)
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo)

    glBufferData(GL_ARRAY_BUFFER, max_vertex_buffer, GL_STREAM_DRAW)
    glBufferData(GL_ELEMENT_ARRAY_BUFFER, max_element_buffer, GL_STREAM_DRAW)

    // load draw vertices & elements directly into vertex + element buffer
    val vertices = glMapBuffer(GL_ARRAY_BUFFER, GL_WRITE_ONLY, max_vertex_buffer, null)
    val elements = glMapBuffer(GL_ELEMENT_ARRAY_BUFFER, GL_WRITE_ONLY, max_element_buffer, null)

    useStack(stack =>
      val pSx = stack.mallocInt(1)
      val pSy = stack.mallocInt(1)
      glfwGetFramebufferSize(win, pSx, pSy)
      val sx = pSx.get(0)
      val sy = pSy.get(0)

      glUseProgram(prog)
      glUniform1i(uniform_tex, 0)
      glUniformMatrix4fv(uniform_proj, false, stack.floats(
        2.0f / sx, 0.0f, 0.0f, 0.0f,
        0.0f, -2.0f / sy, 0.0f, 0.0f,
        0.0f, 0.0f, -1.0f, 0.0f,
        -1.0f, 1.0f, 0.0f, 1.0f
      ))
      glViewport(0, 0, sx, sy)
      // convert from command queue into draw list and draw to screen
      // fill convert configuration
      val config = NkConvertConfig.calloc(stack)
        .vertex_layout(vertexLayout)
        .vertex_size(20)
        .vertex_alignment(4)
        .null_texture(texture)
        .circle_segment_count(22)
        .curve_segment_count(22)
        .arc_segment_count(22)
        .global_alpha(1.0f)
        .shape_AA(NK_ANTI_ALIASING_ON)
        .line_AA(NK_ANTI_ALIASING_ON)

      // setup buffers to load vertices and elements
      val vbuf = NkBuffer.malloc(stack)
      val ebuf = NkBuffer.malloc(stack)

      nk_buffer_init_fixed(vbuf, vertices)
      nk_buffer_init_fixed(ebuf, elements)
      nk_convert(ctx, cmds, vbuf, ebuf, config)
    )
    glUnmapBuffer(GL_ELEMENT_ARRAY_BUFFER)
    glUnmapBuffer(GL_ARRAY_BUFFER)

    // iterate over and execute each draw command
    var offset = 0L
    var cmd = nk__draw_begin(ctx, cmds)
    while cmd != null do
      if cmd.elem_count() != 0 then
        glBindTexture(GL_TEXTURE_2D, cmd.texture().id())
        glDrawElements(GL_TRIANGLES, cmd.elem_count(), GL_UNSIGNED_SHORT, offset)
        offset += cmd.elem_count() * 2
      cmd = nk__draw_next(cmd, cmds, ctx)
    nk_clear(ctx)
    

  private def setupContext() = 
    nk_buffer_init(cmds, allocator, BUFFER_INITIAL_SIZE)
    val dir = "src/main/scala/demo/graphics/nk"
    prog = Program(File(dir, "Draw.vert"), File(dir, "Draw.frag")).id
    uniform_tex = glGetUniformLocation(prog, "Texture")
    uniform_proj = glGetUniformLocation(prog, "ProjMtx")

    // buffer setup
    vbo = glGenBuffers
    ebo = glGenBuffers
    vao = glGenVertexArrays
    glBindVertexArray(vao)
    glBindBuffer(GL_ARRAY_BUFFER, vbo)
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo)
    glEnableVertexAttribArray(0) // pos
    glEnableVertexAttribArray(1) // tec
    glEnableVertexAttribArray(2) // col
    glVertexAttribPointer(0, 2, GL_FLOAT, false, 20, 0)
    glVertexAttribPointer(1, 2, GL_FLOAT, false, 20, 8)
    glVertexAttribPointer(2, 4, GL_UNSIGNED_BYTE, true, 20, 16)

    // null texture setup
    val nullTexID = glGenTextures
    texture.texture.id(nullTexID)
    texture.uv.set(0.5f, 0.5f)
    glBindTexture(GL_TEXTURE_2D, nullTexID)

    useStack(stack =>
      val pixels = stack.ints(0xFFFFFFFF)
      glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, 1, 1, 0, GL_RGBA, GL_UNSIGNED_INT_8_8_8_8_REV, pixels)
    )

    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST)
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST)

    glBindTexture(GL_TEXTURE_2D, 0)
    glBindBuffer(GL_ARRAY_BUFFER, 0)
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0)
    glBindVertexArray(0)
  

  private def setupWindow(win: Long) =
    glfwSetKeyCallback(win, onKey)
    glfwSetCharCallback(win, onChar)
    glfwSetCursorPosCallback(win, onMouseMove)
    glfwSetMouseButtonCallback(win, onMouseButton)
    glfwSetScrollCallback(win, onMouseWheel)

    nk_init(ctx, allocator, null)
    
    ctx.clip.copy(onClipboardCopy)
    ctx.clip.paste(onClipboardPaste)
    
    setupContext()

  // EVENT HANDLER

  def onMouseMove(window: Long, x: Double, y: Double) =
    nk_input_motion(ctx, x.toInt, y.toInt)

  def onMouseButton(window: Long, button: Int, action: Int, mods: Int) =
    useStack(stack =>
        val cx = stack.mallocDouble(1)
        val cy = stack.mallocDouble(1)
        glfwGetCursorPos(window, cx, cy)
        val x = cx.get(0).toInt
        val y = cy.get(0).toInt
        val nkButton = button match 
          case GLFW_MOUSE_BUTTON_RIGHT => NK_BUTTON_RIGHT
          case GLFW_MOUSE_BUTTON_MIDDLE => NK_BUTTON_MIDDLE
          case GLFW_MOUSE_BUTTON_LEFT => NK_BUTTON_LEFT
        nk_input_button(ctx, nkButton, x, y, action == GLFW_PRESS)
    )

  def onMouseWheel(window: Long, ox: Double, oy: Double) =
    useStack(s =>
      val v = NkVec2.malloc(s)
      v.set(ox.toFloat, oy.toFloat)
      nk_input_scroll(ctx, v)
    )

  def onChar(window: Long, codepoint: Int) =
    nk_input_unicode(ctx, codepoint)

  def onKey(window: Long, key: Int, code: Int, action: Int, modes: Int) =
    val press = action == GLFW_PRESS
    key match
      case GLFW_KEY_ESCAPE => glfwSetWindowShouldClose(window, true)
      case GLFW_KEY_DELETE => nk_input_key(ctx, NK_KEY_DEL, press)
      case GLFW_KEY_ENTER => nk_input_key(ctx, NK_KEY_ENTER, press)
      case GLFW_KEY_TAB => nk_input_key(ctx, NK_KEY_TAB, press)
      case GLFW_KEY_BACKSPACE => nk_input_key(ctx, NK_KEY_BACKSPACE, press)
      case GLFW_KEY_UP => nk_input_key(ctx, NK_KEY_UP, press)
      case GLFW_KEY_DOWN => nk_input_key(ctx, NK_KEY_DOWN, press)
      case GLFW_KEY_HOME =>
        nk_input_key(ctx, NK_KEY_TEXT_START, press)
        nk_input_key(ctx, NK_KEY_SCROLL_START, press)
      case GLFW_KEY_END =>
        nk_input_key(ctx, NK_KEY_TEXT_END, press)
        nk_input_key(ctx, NK_KEY_SCROLL_END, press)
      case GLFW_KEY_PAGE_DOWN => nk_input_key(ctx, NK_KEY_SCROLL_DOWN, press)
      case GLFW_KEY_PAGE_UP => nk_input_key(ctx, NK_KEY_SCROLL_UP, press)
      case GLFW_KEY_LEFT_SHIFT | GLFW_KEY_RIGHT_SHIFT => nk_input_key(ctx, NK_KEY_SHIFT, press)
      case GLFW_KEY_LEFT_CONTROL | GLFW_KEY_RIGHT_CONTROL =>
        if press then
          nk_input_key(ctx, NK_KEY_COPY, glfwGetKey(window, GLFW_KEY_C) == GLFW_PRESS)
          nk_input_key(ctx, NK_KEY_PASTE, glfwGetKey(window, GLFW_KEY_P) == GLFW_PRESS)
          nk_input_key(ctx, NK_KEY_CUT, glfwGetKey(window, GLFW_KEY_X) == GLFW_PRESS)
          nk_input_key(ctx, NK_KEY_TEXT_UNDO, glfwGetKey(window, GLFW_KEY_Z) == GLFW_PRESS)
          nk_input_key(ctx, NK_KEY_TEXT_REDO, glfwGetKey(window, GLFW_KEY_R) == GLFW_PRESS)
          nk_input_key(ctx, NK_KEY_TEXT_WORD_LEFT, glfwGetKey(window, GLFW_KEY_LEFT) == GLFW_PRESS)
          nk_input_key(ctx, NK_KEY_TEXT_WORD_RIGHT, glfwGetKey(window, GLFW_KEY_RIGHT) == GLFW_PRESS)
          nk_input_key(ctx, NK_KEY_TEXT_LINE_START, glfwGetKey(window, GLFW_KEY_B) == GLFW_PRESS)
          nk_input_key(ctx, NK_KEY_TEXT_LINE_END, glfwGetKey(window, GLFW_KEY_E) == GLFW_PRESS)
        else
          nk_input_key(ctx, NK_KEY_LEFT, glfwGetKey(window, GLFW_KEY_LEFT) == GLFW_PRESS)
          nk_input_key(ctx, NK_KEY_RIGHT, glfwGetKey(window, GLFW_KEY_RIGHT) == GLFW_PRESS)
          nk_input_key(ctx, NK_KEY_COPY, false)
          nk_input_key(ctx, NK_KEY_PASTE, false)
          nk_input_key(ctx, NK_KEY_CUT, false)
          nk_input_key(ctx, NK_KEY_SHIFT, false)
        
  def onClipboardCopy(handle: Long, text: Long, length: Int) =
    // if (len == 0) ()
    useStack(stack =>
      val str = stack.malloc(length + 1)
      memCopy(text, memAddress(str), length)
      str.put(length, 0)
      glfwSetClipboardString(win, str)
    )

  // todo: use safe funktions!  
  def onClipboardPaste(handle: Long, edit: Long) =
    val text = nglfwGetClipboardString(win)
    if text != NULL then nnk_textedit_paste(edit, text, nnk_strlen(text))

  // copression value not visible!  
  def onTextWidth(handle: Long, h: Float, text: Long, len: Int) =
    var text_width = 0.0f
    useStack(stack =>
      val unicode = stack.mallocInt(1)
      var glyph_len = nnk_utf_decode(text, memAddress(unicode), len)
      var text_len = glyph_len
      if glyph_len == 0 then 
        0
      else
        val advance = stack.mallocInt(1)
        while text_len <= len && glyph_len != 0 && unicode.get(0) != NK_UTF_INVALID do
          // query currently drawn glyph information
          stbtt_GetCodepointHMetrics(fontInfo, unicode.get(0), advance, null)
          text_width += advance.get(0) * scale.toFloat
          // offset next glyph
          glyph_len = nnk_utf_decode(text + text_len, memAddress(unicode), len - text_len)
          text_len += glyph_len
        text_width
    )

  def onQueryFontGlyph(handle: Long, fontHeight: Float, glyph: Long, codepoint: Int, nextCodepoint: Int) =
    useStack(stack =>
      val x = stack.floats(0.0f)
      val y = stack.floats(0.0f)
      val q = STBTTAlignedQuad.malloc(stack)
      val advance = stack.mallocInt(1)
      stbtt_GetPackedQuad(cdata, BITMAP_W, BITMAP_H, codepoint - 32, x, y, q, false)
      stbtt_GetCodepointHMetrics(fontInfo, codepoint, advance, null)
      val ufg = NkUserFontGlyph.create(glyph)
      ufg.width(q.x1() - q.x0())
      ufg.height(q.y1() - q.y0())
      ufg.offset().set(q.x0(), q.y0() + (FONT_HEIGHT + descent).toFloat)
      ufg.xadvance(advance.get(0) * scale.toFloat)
      ufg.uv(0).set(q.s0(), q.t0())
      ufg.uv(1).set(q.s1(), q.t1())
    )
