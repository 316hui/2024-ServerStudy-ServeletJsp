<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정</title>
</head>
<body>
	<h1>회원 정보 수정</h1>
	<form action="user-editProcess.do" name="user" method="post">
			<input type="hidden" name="u_idx" value="${specificUser.u_idx}">
			<p> 아이디 : <input type="text" name="edit_id" value="${specificUser.u_id}"></p>
	   		<p> 비밀번호 : <input type="password" name="edit_password" value="${specificUser.u_pw}"></p>
			<p> 이름 : <input type="text" name="edit_name" value="${specificUser.u_name}"></p>
			<p> 연락처 : <input type="text" maxlength="4" size="4" name="edit_tel1" value="${specificUser.uTels[0]}"> -
	   				   <input type="text" maxlength="4" size="4" name="edit_tel2" value="${specificUser.uTels[1]}"> -
	   				   <input type="text" maxlength="4" size="4" name="edit_tel3" value="${specificUser.uTels[2]}"> 
	   		</p>
	   		<p> 나이 : <input type="text" name="edit_age" value="${specificUser.u_age}"></p>
	   		<p> <input type="submit" value="수정완료"></p>
	</form>
</body>
</html>