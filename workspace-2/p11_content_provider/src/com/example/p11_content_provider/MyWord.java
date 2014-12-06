package com.example.p11_content_provider;

public class MyWord {
	protected int _id;
	protected String word;
	protected String locale;
	
	public MyWord(){
		_id = 0;
		word = "";
		locale = "";
	}
	
	public MyWord(int _id, String word, String locale){
		this._id = _id;
		this.word = word;
		this.locale = locale;
	}
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
}
