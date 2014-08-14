package com.reliefzk.imessage.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
	private String fromWho;
	private String toWho;
	private String message;
	private String date;
	
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public Message(){
		this.date = sdf.format(new Date());
	}

	public String getFromWho() {
		return fromWho;
	}

	public void setFromWho(String fromWho) {
		this.fromWho = fromWho;
	}

	public String getToWho() {
		return toWho;
	}

	public void setToWho(String toWho) {
		this.toWho = toWho;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((fromWho == null) ? 0 : fromWho.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((toWho == null) ? 0 : toWho.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (fromWho == null) {
			if (other.fromWho != null)
				return false;
		} else if (!fromWho.equals(other.fromWho))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (toWho == null) {
			if (other.toWho != null)
				return false;
		} else if (!toWho.equals(other.toWho))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [fromWho=" + fromWho + ", toWho=" + toWho
				+ ", message=" + message + ", date=" + date + "]";
	}
	
	

}
