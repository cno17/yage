package yage.graphics.fw

import org.lwjgl.glfw.GLFW.glfwPollEvents
import org.lwjgl.glfw.GLFW.glfwWaitEvents

/**
 * glfwPollEvents() delegates the events received since the last call to the 
 * registered listeners and returns immediatedly.
 * glfwWaitEvents() ...
 */

enum EventMode(val eventProcessor: () => Unit):
  case Poll extends EventMode(glfwPollEvents)
  case Wait extends EventMode(glfwWaitEvents)
