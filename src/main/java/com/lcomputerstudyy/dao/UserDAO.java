package com.lcomputerstudyy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.lcomputerstudyy.database.DBConnection;
import com.lcomputerstudyy.vo.Pagination;
import com.lcomputerstudyy.vo.Search;
import com.lcomputerstudyy.vo.User;

public class UserDAO {
	private static UserDAO dao = null;
	
	private UserDAO() {
		
	}
	
	public static UserDAO getInstance() {
		if(dao == null) {
			dao = new UserDAO();
		}
		return dao;
	}
	
	public ArrayList<User> getUsers() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> list = null;
		
		try {
			conn = DBConnection.getConnection(); //?
			String query = "select * from user";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			list = new ArrayList<User>();
			
			while(rs.next()) {
				User user = new User();
				user.setU_idx(rs.getInt("u_idx"));
				user.setU_id(rs.getString("u_id"));
				user.setU_name(rs.getString("u_name"));
				user.setU_tel(rs.getString("u_tel"));
				user.setU_age(rs.getString("u_age"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public User getUser(int u_idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = new User();
		
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from user where u_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u_idx);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setU_idx(rs.getInt("u_idx"));
				user.setU_id(rs.getString("u_id"));
				user.setU_name(rs.getString("u_name"));
				user.setU_tel(rs.getString("u_tel"));
				user.setuTels(user.getU_tel().split("-"));
				user.setU_age(rs.getString("u_age"));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public void insertUser(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into user(u_id, u_pw, u_name, u_tel, u_age) values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getU_id());
			pstmt.setString(2, user.getU_pw());
			pstmt.setString(3, user.getU_name());
			pstmt.setString(4, user.getU_tel());
			pstmt.setString(5, user.getU_age());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("SQLException : " + ex.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateUser(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "UPDATE user SET u_id=?, u_pw=?, u_name=?, u_tel=?, u_age=? WHERE u_idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getU_id());
			pstmt.setString(2, user.getU_pw());
			pstmt.setString(3, user.getU_name());
			pstmt.setString(4, user.getU_tel());
			pstmt.setString(5, user.getU_age());
			pstmt.setInt(6, user.getU_idx());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteUser(int u_idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from user where u_idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, u_idx);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public int getUsersCount(Pagination pagination) {
		Connection conn= null; //여러 디비를 사용하는 경우가 있어서 각각 작성하는건가?
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Search search = pagination.getSearch();
		String category = null;
		Boolean isThereSearch = false;
	
		
		try {
			String query = "SELECT COUNT(*) count FROM user ";
			
			if (search.getCategory() != null && !search.getCategory().isEmpty()) {
				if(search.getKeyword() != null && !search.getKeyword().isEmpty()) {
					isThereSearch = true;
					
					switch (search.getCategory()) {
						case "1" : 
							category = "u_id";
							break;
						case "2" :
							category = "u_name";
							break;
					}
					query += "WHERE " + category + " LIKE ?";
					
					System.out.println("logggg : " + "getUsersCount 거침");
				}
			}
			
			conn = DBConnection.getConnection();
			
			pstmt = conn.prepareStatement(query);
			if (isThereSearch == true) {		//sql injection 방지. 키워드는 쿼리에 직접 넣지 않고 주입.
				pstmt.setString(1, "%"+search.getKeyword()+"%");
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("count : "+ count);
		return count;
	}
	
	public ArrayList<User> getUsers(Pagination pagination) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> list = null;
		String category = null;
		Boolean isThereSearch = false;
		
		Search search = pagination.getSearch();
		String where = " ";
		
		//Optional<String> optCategory = Optional.ofNullable(search.getCategory());
		//optCategory.ifPresent((c) -> );
		
		if (search.getCategory() != null && !search.getCategory().isEmpty()) {
			if (search.getKeyword() != null && !search.getKeyword().isEmpty()) {
				isThereSearch = true;
				
				switch(search.getCategory()) {
					case "1" :
						category = "u_id";
						break;
					case "2" :
						category = "u_name";
						break;
				}
				where = "WHERE " + category + " LIKE ? ";
			}
		}
		try {
			conn = DBConnection.getConnection();
			String query = new StringBuilder()
					.append("SELECT 		@ROWNUM := @ROWNUM - 1 AS ROWNUM,\n")
					.append("				ta.*\n")
					.append("FROM 			user ta\n")
					.append("INNER JOIN		(SELECT @ROWNUM := (SELECT	COUNT(*)-?+1 FROM user ta)) tb ON 1=1\n")
					.append(where)
					.append("LIMIT			?, ?\n")
					.toString();
			//검색어가 있는 경우와 없는 경우 쿼리 ? 에 넣는 값을 다르게 함.
			if (isThereSearch == true) {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, pagination.getPageNum());
				pstmt.setString(2, "%"+search.getKeyword()+"%");
				pstmt.setInt(3, pagination.getPageNum());
				pstmt.setInt(4, Pagination.perPage);
			} else {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, pagination.getPageNum());
				pstmt.setInt(2, pagination.getPageNum());
				pstmt.setInt(3, Pagination.perPage);
			}

			rs = pstmt.executeQuery();
			list = new ArrayList<User>();
			
			while(rs.next()) {
				User user = new User();
				user.setRownum(rs.getInt("ROWNUM"));
				user.setU_idx(rs.getInt("u_idx"));
				user.setU_id(rs.getString("u_id"));
				user.setU_name(rs.getString("u_name"));
				user.setU_tel(rs.getString("u_tel"));
				user.setU_age(rs.getString("u_age"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public User loginUser(String idx, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "SELECT * FROM user WHERE u_id=? AND u_pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setU_idx(rs.getInt("u_idx"));
				user.setU_pw(rs.getString("u_pw"));
				user.setU_id(rs.getString("u_id"));
				user.setU_name(rs.getString("u_name"));
			}
		}catch(Exception ex) {
			System.out.println("SQLException : " + ex.getMessage());
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
}
