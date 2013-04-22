package org.leviathan.message

import net.noerd.prequel.DatabaseConfig
import org.msgpack._
import org.msgpack.`type`.Value

class Message(data: Map[Value,Value]) {
  var appId = ""
  var host = ""
  var ip = ""
  var duration = ""

  final def store(db: DatabaseConfig) {
    val collection = MessageUtil.getCollectionName(data)
    val fields = getFields

    if(fields == null) {
      throw new Exception("no fields present for write")
    }

    val columns = fields.keys
    val values = fields.values
    val columnList = columns reduce {(a, b) => s"$a, $b" }
    val valueList = values reduce {(a, b) => s"$a, ?" }

    db.transaction { t => t.execute(String.format("insert into $collection ($columnList) values ($valueList)", values)) }
  } 
  
  def getFields : Map[String,String] = {
    Map[String,String](
        "app_id" -> appId,
        "host" -> host,
        "ip" -> ip
    )
  }
}
