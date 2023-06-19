package test.graphics.gl.resource

import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL15C.*
import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL31C.*
import org.lwjgl.opengl.GL44C.*
import org.lwjgl.opengl.GL45C.*
import yage.graphics.fw.App
import yage.graphics.gl.ClassUtil
import org.lwjgl.BufferUtils
import yage.graphics.gl.resource.buffer.BufferStorageFlags
import yage.graphics.gl.resource.buffer.Buffer
import yage.graphics.gl.resource.Access
import yage.graphics.gl.resource.buffer.BufferMapFlags
import yage.graphics.fw.AppCreateInfo
import java.io.File
import yage.graphics.gl.resource.texture.Texture2

object TextureTest extends App:

  override def init() =
    val f = File("src/main/resources/image/noir/Faya.jpg")
    val tex = Texture2(f)
    println(tex.sizeI)
    close()

