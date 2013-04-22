package org.leviathan.message

import org.msgpack.`type`.Value

class ProcessActionMessage(data: Map[Value,Value]) extends Message(data) {
  val controller = ""
  val action = ""
  val params = ""
  val format = ""
  val method = ""
  val path = ""
  val viewRuntime = ""

  override def getFields : Map[String,String] = {
    Map[String,String](
        "controller" -> controller,
        "action" -> action,
        "params" -> params,
        "format" -> format,
        "method" -> method,
        "path" -> path,
        "view_runtime" -> viewRuntime
    ) ++ this.asInstanceOf[Map[String,String]]
  }
}
