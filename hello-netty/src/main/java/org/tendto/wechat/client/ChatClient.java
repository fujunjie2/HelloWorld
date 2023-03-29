package org.tendto.wechat.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ChatClient {


    public static void main(String[] args) {


        NioEventLoopGroup worker = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap
                .group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                    }
                });

    }
}
