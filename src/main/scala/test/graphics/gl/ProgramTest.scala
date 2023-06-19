package test.graphics.gl

// import yage.graphics.gl.shader.program.ProgramFactory

import java.io.File
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import yage.graphics.fw.App
import yage.graphics.gl.shader.program.Program
import yage.graphics.gl.shader.ShaderStage
import yage.graphics.gl.shader.Shader

object ProgramLoaderTest extends App:

  override def init() =
    val d = File("src/main/scala/demo/graphics/gl/gs")
    val vf = File(d, "Silhouette.vert")
    val gf = File(d, "Silhouette.geom")
    val ff = File(d, "Silhouette.frag")
    val p = Program(vf, gf, ff)
    println(p)
    close()
