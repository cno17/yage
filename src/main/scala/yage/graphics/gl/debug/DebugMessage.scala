package yage.graphics.gl.debug

/**
 * A debug message in the debug message stream is uniquely identified by the (source, typ, id) triple.
 */

class DebugMessage:

  var source = DebugMessageSource.Api
  var typ = DebugMessageType.Error
  var id = 0
  var severity = DebugMessageSeverity.High
  var text = ""

  override def toString =
    s"Source = $source, Type = $typ, Id = $id, Severity = $severity\n$text\n"
