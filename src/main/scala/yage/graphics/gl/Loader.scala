package yage.graphics.gl

import java.io.File

trait Loader[T]: // [O <: Object]:
  
  def load(f: File): T // Try[T]


