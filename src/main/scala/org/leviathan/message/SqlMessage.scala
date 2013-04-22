package org.leviathan.message

import org.msgpack.`type`.Value

class SqlMessage(data: Map[Value,Value]) extends Message(data) {
  val sql = ""
  val name = ""
  val objectId = ""

  override def getFields : Map[String,String] = {
    this.asInstanceOf[Map[String,String]] ++ Map[String,String]("sql" -> sql, "name" -> name, "object_id" -> objectId)
  }
}
