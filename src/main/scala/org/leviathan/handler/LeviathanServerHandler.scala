package org.leviathan.handler

import scala.collection.immutable.HashMap

import org.jboss.netty.channel._
import org.jboss.netty.buffer.ChannelBuffer
import org.msgpack._
import org.msgpack.ScalaMessagePack._

class LeviathanServerHandler extends SimpleChannelUpstreamHandler {
  override def messageReceived(ctx: ChannelHandlerContext, e: MessageEvent) {
    val buffer = e.getMessage.asInstanceOf[ChannelBuffer]
    val length = buffer.readableBytes
    val bytes = buffer.readBytes(length).array
    val message = read[Map[String,String]](bytes)

    message.foreach((x: (String, String)) => { System.out.println(x._1 + ": " + x._2) })    
    System.out.flush
  }
}
