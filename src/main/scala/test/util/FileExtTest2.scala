package test.util

import java.io.File
import yage.util.FileExt
import yage.graphics.gl.shader.ShaderStage
import scala.collection.mutable.ArrayBuffer

object FileExtTest2 extends FileExt:

  def main(args: Array[String]) =
    val f = File("diffuse.fra")
    println(stageOf(f))
    println(17)

  def stageOf(f: File) = ShaderStage.values.find(s => s.ext == f.ext).get
