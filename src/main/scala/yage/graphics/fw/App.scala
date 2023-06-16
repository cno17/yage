package yage.graphics.fw

import org.lwjgl.opengl.GL11C.*
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import yage.graphics.gl.debug.DebugMessenger
import org.lwjgl.opengl.GL.createCapabilities

import java.io.File
import yage.graphics.vg.Context
import org.lwjgl.nanovg.NanoVGGL3

/**
 * how to close this programmatically?
 *
 * getResourceAsStream(), getResourceDir(): // in an sbt app
 */

trait App:

  type EventProcessor = () => Unit

  var window: Window = null
  var keyboard: Keyboard = null
  var mouse: Mouse = null
  var timer: Timer = null
  var debugMessenger: DebugMessenger = null
  var eventProcessor: EventProcessor = null
  var frameCounter: FrameCounter = null

  var ctx: Context = null // ctx2d?

  def info() = AppCreateInfo()
  def init() = {}
  def draw() = {}
  def step(dt: Int) = {}
  def exit() = {}

  def main(args: Array[String]) = run()

  def srcDir =
    val s1 = File("src/main/scala").getAbsolutePath().toLowerCase()
    val s2 = getClass().getPackage().getName().replace('.', '\\')
    File(s1 + "\\" + s2)

  def resDir = 
    File("src/main/resources")

  final def run() =
    create()
    init()
    var t1 = System.nanoTime()
    var t2 = t1
    var dt = 0
    while !window.shouldClose() do
      t2 = System.nanoTime()
      dt = ((t2 - t1) / 1000000).toInt
      t1 = t2
      draw()
      step(dt)
      frameCounter.step(dt)
      window.swapBuffers()
      eventProcessor()
    exit()
    destroy()

  final def close() = glfwSetWindowShouldClose(window.handle, true)

  protected def onError(code: Int, pDescription: Long) =
    val error = Error(code)
    val description = GLFWErrorCallback.getDescription(pDescription)
    println(s"$error, $description")

  protected def onCloseKeyPressed(key: Key) =
    if key == Key.Escape || key == Key.Q then close()

  // good idea?  
  protected def onWindowResized(w: Int, h: Int) = 
    glViewport(0, 0, w, h)

  private def create() =
    glfwSetErrorCallback(onError)
    glfwInit()
    val i = info()
    i.hints.apply()
    val h = glfwCreateWindow(i.sizeX, i.sizeY, i.title, 0, 0)
    glfwMakeContextCurrent(h)
    createCapabilities()
    glfwSwapInterval(1)
    //
    window = Window(h)
    window.resizedListeners += onWindowResized
    keyboard = Keyboard(h)
    keyboard.keyPressedListeners += onCloseKeyPressed
    mouse = Mouse(h)
    timer = Timer()
    debugMessenger = DebugMessenger(i.hints.debugContext)
    eventProcessor = i.eventMode.eventProcessor
    frameCounter = FrameCounter()
    ctx = Context(NanoVGGL3.nvgCreate(0))
    Monitor.primaryMonitor.center(window)

  private def destroy() =
    window.destroy()
    glfwTerminate

/*
def srcDir =
  val s = getClass.getPackage.getName.replace(".", File.separator)
  new File("src" + File.separator + s)

def resDir =
  val s = getClass.getPackage.getName.replace(".", File.separator)
  new File("res" + File.separator + s)
 */
