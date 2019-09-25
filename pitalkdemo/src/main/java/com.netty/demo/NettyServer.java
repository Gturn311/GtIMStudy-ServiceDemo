package com.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author Gturn
 * @Title: NettyServer
 * @ProjectName Netty—Study
 * @Description: TODO
 * @date 2019/9/24 16:37
 */

public class NettyServer {
    public static void main(String[] args) {
        //两大线程组
        NioEventLoopGroup serverGroup = new NioEventLoopGroup();
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(serverGroup,clientGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel) {
                System.out.println("有新连接进入...");
                nioSocketChannel.pipeline().addLast(new FirstServerHandler());
            }
        });
        bind(serverBootstrap,80);
        serverBootstrap.handler(new ChannelInitializer<NioServerSocketChannel>() {
            @Override
            protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {
                System.out.println("服务端启动中...");
            }
        });
        //给服务端的 channel,自定义属性
        serverBootstrap.attr(AttributeKey.newInstance("serverName"), "nettyServer");
        //给每一条进入的连接的 channel,自定义属性
        serverBootstrap.childAttr(AttributeKey.newInstance("clientKey"), "clientValue");
        //ChannelOption.SO_KEEPALIVE表示是否开启TCP底层心跳机制，true为开启
        //ChannelOption.TCP_NODELAY表示是否开启Nagle算法，true表示关闭，false表示开启
        // ，通俗地说，如果要求高实时性，有数据发送时就马上发送，就关闭，
        // 如果需要减少发送次数减少网络交互，就开启。
        serverBootstrap
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true);

        //设置系统用于临时存放已完成三次握手的请求的队列的最大长度
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);

    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }

}
