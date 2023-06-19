package test.util

import java.io.File
import yage.util.FileExt
import scala.collection.mutable.ArrayBuffer

object FileExtTest extends FileExt:

  def main(args: Array[String]) =
    val d = File("src/main/resources/shader/cookbook/ch06")
    val f = File("diffuse.frag.glsl")
    for cs <- getChildren(d); c <- cs do
      println(c.getName())
    val a = getChildren(d).get
    a.drop(1)
    println(a.length)


  def getDescendants(f: File, res: ArrayBuffer[File]): Unit =
    res ++= f.listFiles()
    for ff <- f.listFiles(_.isDirectory()) do
      getDescendants(ff, res)

  def getChildren(f: File) = 
    if f.isFile then None else Some(f.listFiles())
    