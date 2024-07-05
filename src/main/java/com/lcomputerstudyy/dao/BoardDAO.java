package com.lcomputerstudyy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.lcomputerstudyy.database.DBConnection;
import com.lcomputerstudyy.vo.Board;
import com.lcomputerstudyy.vo.Pagination;
import com.lcomputerstudyy.vo.User;

public class BoardDAO {
	private static BoardDAO dao = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public static BoardDAO getInstance(){
		if (dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	public int getPostsCount(Pagination boardPagination) {
		int count = 0;
		
		try {
			String sql = "SELECT COUNT(*) count FROM board";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("count"); //COUNT(*) 의 가상이름
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	public ArrayList<Board> getBoards(Pagination boardPagination) {
		ArrayList<Board> boardList = null;
		try {
			String sql = new StringBuilder()
					.append("SELECT 		@ROWNUM := @ROWNUM - 1 AS ROWNUM,\n")
					.append("				ta.*\n")
					.append("FROM 			board ta\n")
					.append("INNER JOIN		(SELECT @ROWNUM := (SELECT	COUNT(*)-?+1 FROM board ta)) tb ON 1=1\n")
					.append("LIMIT			?, ?\n")
					.toString();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardPagination.getPageNum());
			pstmt.setInt(2, boardPagination.getPageNum());
			pstmt.setInt(3, Pagination.perPage);
			System.out.println(boardPagination.getPageNum() + " + " + Pagination.perPage);
			rs = pstmt.executeQuery();
			
			boardList = new ArrayList<Board>();
			while(rs.next()) {
				Board board = new Board();
				System.out.println(rs.getInt("b_idx"));
				java.sql.Timestamp timestamp = rs.getTimestamp("b_date");
				LocalDateTime dateTime = timestamp.toLocalDateTime();
				board.setB_date(dateTime);
				board.setB_title(rs.getString("b_title"));
				board.setB_content(rs.getString("b_content"));
				board.setB_views(rs.getInt("b_views"));
				board.setB_idx(rs.getInt("b_idx")); //글 올린 유저
				board.setRownum(rs.getInt("ROWNUM"));
				
				//System.out.println(board.getB_Title());
				boardList.add(board);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return boardList;
	}
	
	public Board getBoard(int b_idx) {
		Board specificBoard = new Board();
		
		try {
			conn = DBConnection.getConnection();
			String sql = "SELECT * FROM board ta LEFT JOIN user tb "
					+ "ON ta.u_idx = tb.u_idx WHERE b_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_idx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				specificBoard.setB_idx(rs.getInt("b_idx"));
				specificBoard.setB_title(rs.getString("b_title"));
				specificBoard.setB_content(rs.getString("b_content"));
				java.sql.Timestamp timestamp = rs.getTimestamp("b_date");
				LocalDateTime dateTime = timestamp.toLocalDateTime();
				specificBoard.setB_date(dateTime);
				specificBoard.setB_views(rs.getInt("b_views"));
				specificBoard.setU_idx(rs.getInt("u_idx"));
				
				User user = new User();
				user.setU_name(rs.getString("u_name"));
				specificBoard.setUser(user);
				
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(rs != null) pstmt.close();
				if(pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return specificBoard;
	}
	
	public void deleteBoard(int b_idx) {
		try {
			conn = DBConnection.getConnection();
			String sql = "DELETE FROM board WHERE b_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_idx);
			rs = pstmt.executeQuery();
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(rs != null) pstmt.close();
				if(pstmt!= null) pstmt.close();
				if(conn!= null) conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateBoard(Board board) {
		try {
			conn = DBConnection.getConnection();
			String sql = "UPDATE board SET b_idx=?, b_title=?, b_content=?, b_date=?, b_views=?, u_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_idx());
			pstmt.setString(2, board.getB_title());
			pstmt.setString(3, board.getB_content());
			java.sql.Timestamp date = java.sql.Timestamp.valueOf(board.getB_date());
			pstmt.setTimestamp(4, date);
			pstmt.setInt(5, board.getB_views());
			pstmt.setInt(6, board.getUser().getU_idx());
			pstmt.executeUpdate();
		}catch (SQLException ex) {
			ex.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void increaseViews(Board board) {
		try {
			conn = DBConnection.getConnection();
			String sql = "UPDATE ? SET b_views = b_views+1";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch (SQLException ex) {
			ex.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
