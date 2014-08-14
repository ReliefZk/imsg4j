package com.reliefzk.imessage;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * A WebSocket Server that respondes to requests at:
 *
 * <pre>
 * http://localhost:8080/websocket
 * </pre>
 *
 * The example differs from many of the other examples in Netty in that is does
 * not have an acomponying client. Instead a html page is provided that
 * interacts with this server. <br>
 * Open up the following file a web browser that supports WebSocket's:
 *
 * <pre>
 * example/src/main/resources/websocketx/html5/websocket.html
 * </pre>
 *
 * The html page is very simple were you simply enter some text and the server
 * will echo the same text back, but in uppercase. You, also see getStatus messages
 * in the "Response From Server" area when client has connected, disconnected
 * etc.
 *
 */
public class ServerBootStrapt {
	private final int port;

	public ServerBootStrapt(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			final ServerBootstrap sb = new ServerBootstrap();
			sb.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(final SocketChannel ch)
								throws Exception {
							ch.pipeline().addLast(
									new HttpRequestDecoder(),
									new HttpObjectAggregator(65536),
									new HttpResponseEncoder(),
									new WebSocketServerProtocolHandler("/websocket"),
									new CustomTextFrameHandler());
						}
					});

			final Channel ch = sb.bind(port).sync().channel();
			System.out.println("Web socket server started at port " + port);

			ch.closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port = 8080;
		new ServerBootStrapt(port).run();
	}
}
