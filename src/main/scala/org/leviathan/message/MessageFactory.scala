package org.leviathan.message

import org.msgpack.`type`.Value

object MessageFactory {
  def getMessage(data: Map[Value,Value]) : Message = {
    throw new Exception("not implemented")
  }
}
