package com.model;

import java.io.Serializable;

public class GrammerInputModel  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	private String text;
	
	private String key;

}
