package org.leviathan.message

import org.msgpack.`type`.Value

object MessageFactory {
  def getMessage(data: Map[Value,Value]) : Message = {
    MessageUtil.getCollectionName(data) match {
      case "sql_messages" =>
        new SqlMessage(data)
      case "process_action_messages" =>
        new ProcessActionMessage(data)
      case _ =>
        null
    }
  }
}
