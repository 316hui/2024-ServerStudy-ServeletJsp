package com.lcomputerstudyy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcomputerstudyy.service.BoardService;
import com.lcomputerstudyy.service.UserService;
import com.lcomputerstudyy.vo.Pagination;
import com.lcomputerstudyy.vo.Search;
import com.lcomputerstudyy.vo.User;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String view = null;
		
		int usercount = 0;
		int page = 1;
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		UserService userService;
		int u_idx;
		User specificUser;
		int count = 0;
		
		String pw = null;
		String idx = null;
		HttpSession session;
		command = checkSession(request, response, command);
		
		/*-------------------------------------------------------------게시물 관련변수들*/
		BoardService boardService;
		
		
		
		switch (command) {
		case "/user-list.do":
			
			Search search = new Search();
			search.setCategory(request.getParameter("category"));
			search.setKeyword(request.getParameter("keyword"));
			
			String reqPage = request.getParameter("page");
			if (reqPage != null)
				page = Integer.parseInt(reqPage);
			
			userService = UserService.getInstance();
			
			Pagination pagination = new Pagination();
			pagination.setPage(page);
			pagination.setSearch(search);
			count = userService.getUsersCount(pagination);//카테고리, 키워드 넣은 서치 포함시키기
			pagination.setCount(count); //abc
			pagination.build();
		
			
			System.out.println("유저 수 : " + count);
			ArrayList<User> list = userService.getUsers(pagination);
			System.out.println("유저 리스트 : " + list);
			
			request.setAttribute("list", list);
			request.setAttribute("pagination", pagination);
			
			view = "user/list";
			break;
			
		case "/user-insert.do":
			view = "user/insert";
			break;
			
		case "/user-insert-process.do":
			User user = new User();
			user.setU_id(request.getParameter("id"));
			user.setU_pw(request.getParameter("password"));
			user.setU_name(request.getParameter("name"));
			user.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + request.getParameter("tel3"));
			user.setU_age(request.getParameter("age"));
			
			userService = UserService.getInstance();
			userService.insertUser(user);
			
			view = "user/insert-result"; //값 보낼 뷰 설정
			break;
			
		case "/user-detail.do" :
			u_idx = Integer.parseInt(request.getParameter("u_idx"));
			userService = UserService.getInstance();
			specificUser = userService.getUser(u_idx);
			view = "user/detail";
			request.setAttribute("specificUser", specificUser);
			break;
			
		case "/user-edit.do" :
			u_idx = Integer.parseInt(request.getParameter("u_idx"));
			userService = UserService.getInstance();
			specificUser = userService.getUser(u_idx);
			view = "user/edit";
			request.setAttribute("specificUser", specificUser);
			break;
			
		case "/user-editProcess.do" :
			u_idx = Integer.parseInt(request.getParameter("u_idx"));
			specificUser = new User();
			specificUser.setU_idx(u_idx);
			specificUser.setU_id(request.getParameter("edit_id"));
			specificUser.setU_pw(request.getParameter("edit_password"));
			specificUser.setU_name(request.getParameter("edit_name"));
			specificUser.setU_age(request.getParameter("edit_age"));
			specificUser.setU_tel(request.getParameter("edit_tel1") + "-" + request.getParameter("edit_tel2") + "-" + request.getParameter("edit_tel3"));
	
			userService = UserService.getInstance();
			userService.updateUser(specificUser);
			view = "user/editProcess";
			request.setAttribute("specificUser", specificUser); //detail로 돌아가기 위해서 idx
			break;
			
		case "/user-delete.do" :
			u_idx = Integer.parseInt(request.getParameter("u_idx")); //list에서 삭제 눌렀을 시 오는 idx
			userService = UserService.getInstance();
			userService.deleteUser(u_idx);
			
			view = "user/delete";
			break;
			
		case "/user-login.do" :
			view = "user/login";
			break;
		
		case "/user-login-process.do" :
			idx = request.getParameter("login_id");
			pw = request.getParameter("login_password");
			
			userService = UserService.getInstance();
			user = userService.loginUser(idx, pw);
			
			if(user != null) {
				session = request.getSession();
				session.setAttribute("user", user);
				
				view = "user/login-result";
			}else {
				view = "user/login-fail";
			}
			break;
		
		case "/logout.do":
			session = request.getSession();
			session.invalidate(); //세션을 무효화
			view = "user/login";
			break;
		
		case "/access-denied.do":
			view = "user/access-denied";
			break;
			
		/*---------------------------------------------------------------게시판 관련 컨트롤러 -----*/
		
		case "/board-list.do" :
			boardService = BoardService.getInstance();
			
			int boardPage =
			int boardCount = 
			Pagination boardPagi = new Pagination();
			boardPagi.setPage(page);
			boardPagi.setCount(count);
			boardPagi.build();
			
			
			view = "board/list";
			break;
		}	
		RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
		rd.forward(request, response);
	}
	
	String checkSession(HttpServletRequest request, HttpServletResponse response, String command) {
		HttpSession session = request.getSession();
		
		String[] authList = {
				"/user-list.do"
				,"/user-insert.do" 
				,"/user-insert-process.do"
				,"/user-detail.do"
				,"/user-edit.do"
				,"/user-edit-process.do"
				,"/logout.do"
		};
		for (String item : authList) {
			if (item.equals(command)) {
				if (session.getAttribute("user") == null) {
					command = "/access-denied.do";
				}
			}
		}
		return command;
	}
	
	
}