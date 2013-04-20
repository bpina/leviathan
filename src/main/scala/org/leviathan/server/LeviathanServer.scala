package org.leviathan.server

import java.net.InetSocketAddress
import java.util.concurrent.Executors
import org.leviathan.handler._
import org.jboss.netty.channel._
import org.jboss.netty.channel.socket.nio._
import org.jboss.netty.bootstrap.ConnectionlessBootstrap
import org.jboss.netty.buffer.ChannelBuffer

object LeviathanServer {
  def main(args: Array[String]) {
    val bootstrap = new ConnectionlessBootstrap(new NioDatagramChannelFactory)
    bootstrap.setPipelineFactory(new ChannelPipelineFactory {
      def getPipeline : ChannelPipeline = {
        Channels.pipeline(new LeviathanServerHandler)
      }
    });

    bootstrap.bind(new InetSocketAddress(8080))
  }
}
