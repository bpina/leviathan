package org.leviathan.message

import net.noerd.prequel.DatabaseConfig
import org.msgpack._

abstract class Message {
  protected var _collection = ""
  protected var _owner = ""

  final def store(db: DatabaseConfig) {
    val fields = getFields

    if(fields == null) {
      throw new Exception("no fields present for write")
    }

    val columns = fields.keys
    val values = fields.values
    val columnList = columns reduce {(a, b) => s"$a, $b" }
    val valueList = values reduce {(a, b) => s"$a, ?" }

    db.transaction { t => t.execute(String.format("insert into $_collection ($columnList) values ($valueList)", values)) }
  } 
  
  def getFields : Map[String,String]

}
