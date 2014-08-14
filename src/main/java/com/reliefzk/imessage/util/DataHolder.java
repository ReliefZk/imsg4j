package com.reliefzk.imessage.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.reliefzk.imessage.domain.Message;

public class DataHolder {
	private static ConcurrentHashMap<String, List<Message>> messages = new ConcurrentHashMap<String, List<Message>>();

	
	public static void putMessage(String key, Message value){
		List<Message> list = messages.get(key);
		if(list == null){
			list = new ArrayList<Message>();
		}
		list.add(value);
		messages.put(key, list);
	}
	
	public static List<Message> getMessages(String key){
		return messages.get(key);
	}
}
