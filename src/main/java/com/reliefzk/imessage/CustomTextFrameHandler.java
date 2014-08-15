package com.reliefzk.imessage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import com.google.gson.Gson;
import com.reliefzk.imessage.vo.Message;

/**
 * 
 * @author kui.zhouk
 *
 */
public class CustomTextFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	private final static Gson gson = new Gson();
	private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		channels.add(ctx.channel());
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, TextWebSocketFrame frame) throws Exception {
		String request = frame.text();
		Message m = gson.fromJson(request, Message.class);

		for (Channel c : channels) {
			String msg = null;
			if (c != ctx.channel()) {
				msg = "[" + m.getToWho() + "] " + m.getMessage();
			} else {
				msg = ("[you] " + m.getMessage() + '\n');
			}
			c.writeAndFlush(new TextWebSocketFrame(msg));
		}
	}
}
