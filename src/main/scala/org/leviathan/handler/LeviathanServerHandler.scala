package org.leviathan.handler

import scala.collection.immutable.HashMap
import org.leviathan.message._
import net.noerd.prequel.DatabaseConfig
import org.jboss.netty.channel._
import org.jboss.netty.buffer.ChannelBuffer
import org.msgpack._
import org.msgpack.ScalaMessagePack._

class LeviathanServerHandler(database: DatabaseConfig) extends SimpleChannelUpstreamHandler {
  override def messageReceived(ctx: ChannelHandlerContext, e: MessageEvent) {
    val buffer = e.getMessage.asInstanceOf[ChannelBuffer]
    val length = buffer.readableBytes
    val bytes = buffer.readBytes(length).array
    val data = readAsValue(bytes).toValueMap

    val message = MessageFactory.getMessage(data)

    if (message != null) message.store(database)
    else throw new Exception("Message could not be parsed")
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, e: ExceptionEvent) {
    System.out.println(e.getCause.getMessage)
    System.out.flush
  }
  
}
