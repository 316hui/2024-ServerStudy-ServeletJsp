package com.lcomputerstudyy.service;

import com.lcomputerstudyy.dao.BoardDAO;

public class BoardService {
	
	private static BoardService service = null;
	private static BoardDAO dao = null;
	
	public static BoardService getInstance() {
		if(service == null) {
			service = new BoardService();
			dao = BoardDAO.getInstance();
		}
		return service;
	}
	
}
