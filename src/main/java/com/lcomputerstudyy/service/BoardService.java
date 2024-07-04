package com.lcomputerstudyy.service;

import java.util.ArrayList;

import com.lcomputerstudyy.dao.BoardDAO;
import com.lcomputerstudyy.vo.Board;
import com.lcomputerstudyy.vo.Pagination;

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
	public int getPostsCount(Pagination boardPagination) {
		return dao.getPostsCount(boardPagination);
	}
	public ArrayList<Board> getBoards(Pagination boardPagination) {
		return dao.getBoards(boardPagination);
	}
	public Board getBoard(int b_idx) {
		return dao.getBoard(b_idx);
	}
	public String getWriterName(int writerIdx) {
		return dao.getWriterName(writerIdx);
	}
	
}
