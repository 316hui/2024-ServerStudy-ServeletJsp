package com.lcomputerstudyy.vo;

public class Board {
	int b_idx;
	String b_title;
	String b_content;
	String b_writer;
	String b_date;
	int b_views;
	int u_idx; //유저아이디
	
	public String getB_Title() {
		return b_title;
	}
	public void setB_Title(String title) {
		this.b_title = title;
	}
	public String getB_Content() {
		return b_content;
	}
	public void setB_Content(String content) {
		this.b_content = content;
	}
	public int getB_Views() {
		return b_views;
	}
	public void setB_Views(int views) {
		this.b_views = views;
	}
	public String getB_Writer() {
		return b_writer;
	}
	public void setB_Writer(String writer) {
		this.b_writer = writer;
	}
	public String getB_Date() {
		return b_date;
	}
	public void setB_Date(String date) {
		this.b_date = date;
	}
	public int getB_idx() {
		return b_idx;
	}
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}
	
	
	
	public int getU_idx() {
		return u_idx;
	}
	public void setU_idx(int u_idx) {
		this.u_idx = u_idx;
	}
}