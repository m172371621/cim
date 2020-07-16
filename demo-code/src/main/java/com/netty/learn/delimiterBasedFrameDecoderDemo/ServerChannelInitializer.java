package com.netty.learn.delimiterBasedFrameDecoderDemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author: rickiyang
 * @date: 2020/3/15
 * @description:
 */
public class ServerChannelInitializer  extends ChannelInitializer<SocketChannel> {
@Override
protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        ByteBuf delimiter= Unpooled.buffer();
        delimiter.writeBytes("&".getBytes());
        pipeline.addLast(new DelimiterBasedFrameDecoder(1024, true, true, delimiter));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        // 自己的逻辑Handler
        pipeline.addLast("handler", new PeServerHandler());
        }
}
