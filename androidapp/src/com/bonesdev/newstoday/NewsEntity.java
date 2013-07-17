package com.bonesdev.newstoday;

import java.io.Serializable;
import java.lang.reflect.Field;

class NewsEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String title;
	private String author;
	private String time;
	private String teaser;
	
	public NewsEntity () {
		
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public void setTeaser(String teaser) {
		this.teaser = teaser;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getTime() {
		return this.time;
	}
	
	public String getTeaser() {
		return this.teaser;
	}
	
	public NewsEntity(String title, String author, String time, String teaser) {
		this.title = title;
		this.author = author;
		this.time = time;
		this.teaser = teaser;
	}
	
	public String getUUID() {
		return this.title + this.author;
	}
	
	
	public String get(String name) {
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field f : fields) {
			String n = f.getName();
			if (n.equals(name)) {
				try {
					return (String)f.get(this);
				}
				catch (IllegalAccessException e) {
					return "";
				}
			}
		}
		return "";
	}
}
