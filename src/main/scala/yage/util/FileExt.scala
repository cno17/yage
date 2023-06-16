package yage.util

import java.io.File
import java.io.FileInputStream
import java.nio.ByteBuffer
import org.lwjgl.BufferUtils.createByteBuffer
import scala.io.Source

// todo: getDescendants, foreachDescendant
// return options

trait FileExt:

  extension (f: File)

    def shortName =
      val s = f.getName
      val i = s.indexOf('.')
      if f.isDirectory || i == -1 then s else s.substring(0, i)

    def ext =
      val s = f.getName
      val i = s.indexOf('.')
      if f.isDirectory || i == -1 then "" else s.substring(i + 1)
    
    def extensions = 
      val s = f.getName
      val i = s.indexOf('.')
      if f.isDirectory() || i == -1 then null else s.split('.').drop(1)

    // todo: return Options

    def chars =
      val src = Source.fromFile(f)
      val str = src.getLines.mkString("\n")
      src.close()
      str

    def bytes =
      val is = FileInputStream(f)
      val ba = is.readAllBytes()
      is.close()
      createByteBuffer(ba.length).put(ba).rewind()
