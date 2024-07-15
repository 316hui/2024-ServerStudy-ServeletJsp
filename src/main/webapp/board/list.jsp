<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트 페이지</title>
	<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
            color: #333;
            font-weight: bold;
        }
        td {
            vertical-align: middle;
        }
        td a {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }
        td a:hover {
            text-decoration: underline;
        }
       
        .back-button {
            margin-bottom: 20px;
        }
        .add-button, .login-button {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="add-button">
        <a href="board-new.do">
            <button>게시물 등록</button>
        </a>
    </div>
    <div class="login-button">
        <a href="user-login.do">
            <button>로그인</button>
        </a>
    </div>
	<table>
		 <caption style="caption-side: top; font-size: 24px; font-weight: bold; margin-bottom: 10px;">게시판</caption>
        <thead>
            <tr>
                <th>No.</th>
                <th>제목</th>
                <th>내용</th>
                <th>조회수</th>
            </tr>
        </thead>
      
			<c:forEach items="${list}" var="board" varStatus="status">
				<tr>
					<td><a href="board-detail.do?b_idx=${board.b_idx}">${board.rownum}</a></td>
					<td>${board.b_title}</td>
					<td>${board.b_content}</td>
					<td>${board.b_views}</td>
				</tr>
				
			</c:forEach>
		
	</table>	
<!---pagination-->
	<div>
		<ul>
			 <c:choose>
				<c:when test="${ pagination.prevPage ge 5}">
					<li>
						<a href="board-list.do?page=${pagination.prevPage}">
							◀
						</a>
					</li>
				</c:when>
			</c:choose> 
			<c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
				
					<c:choose>
						<c:when test="${ pagination.page eq i }">
							<li style="background-color:#ededed;">
								<span>${i}</span>
							</li>
						</c:when>
						<c:when test="${ pagination.page ne i }">
							<li>
								<a href="board-list.do?page=${i}&category=${pagination.search.category}&keyword=${pagination.search.keyword}">${i}</a>
							</li>
						</c:when>
					</c:choose>
			</c:forEach>
			 <c:choose>
				<c:when test="${ pagination.nextPage lt pagination.lastPage }">
					<li style="">
						<a href="board-list.do?page=${pagination.nextPage}">▶</a>
					</li>
				</c:when>
			</c:choose> 
		</ul>
	</div>
</body>
</html>
