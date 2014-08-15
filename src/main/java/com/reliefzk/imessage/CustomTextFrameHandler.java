package com.reliefzk.imessage;

import com.google.gson.Gson;
import com.reliefzk.imessage.vo.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * 
 * @author kui.zhouk
 *
 */
public class CustomTextFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	private final static Gson gson = new Gson();

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, TextWebSocketFrame frame) throws Exception {
		String request = frame.text();
		Message m = gson.fromJson(request, Message.class);
		System.err.println(m);
		ctx.channel().writeAndFlush(new TextWebSocketFrame(request.toUpperCase()));
	}
}
