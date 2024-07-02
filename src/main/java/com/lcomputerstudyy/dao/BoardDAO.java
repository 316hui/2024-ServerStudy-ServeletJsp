package com.lcomputerstudyy.dao;

public class BoardDAO {
	private static BoardDAO dao = null;
	
	public static BoardDAO getInstance(){
		if (dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
}
