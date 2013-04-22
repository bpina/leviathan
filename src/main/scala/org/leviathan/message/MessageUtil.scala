package org.leviathan.message

import org.msgpack.`type`.Value

object MessageUtil {
  def getCollectionName(data: Map[Value,Value]) : String = {
    data("event".asInstanceOf[Value]).asInstanceOf[String] match {
      case "sql.active_record" =>
        "sql_messages"
      case "process_action.action_controller" =>
        "process_action_messages"
      case _ => 
        null 
    }
  }
}
