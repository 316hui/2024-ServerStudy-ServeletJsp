<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세</title>
</head>
<style>
	table {
		border-collapse:collapse;
	}
	table tr th {
		font-weight:700;
	}
	table tr td, table tr th {
		border:1px solid #818181;
		width:200px;
		text-align:center;
	}
	a{
		text-decoration:none;
		color:#000;
		font-weight:700;
	}
	
	a {
		text-dexoration: none;
		color: #100;
		font-weight:700;
		border:none;
		cursor:pointer;
		padding:10px;
		display:inlin-block;
	}
</style>
<body>

<h1>회원 상세페이지</h1>
<table>
	<tr>
		<td>회원 번호</td>
		<td>${specificUser.u_idx}</td>
	</tr>
	<tr>
		<td>회원 ID</td>
		<td>${specificUser.u_id}</td>
	</tr>
	<tr>
		<td>회원 이름</td>
		<td>${specificUser.u_name}</td>
	</tr>
	<tr>
		<td>회원 전화번호</td>
		<td>${specificUser.u_tel}</td>
	</tr>
	<tr>
		<td>회원 나이</td>
		<td>${specificUser.u_age}</td>
	</tr>
	
	<tr style="height:50px;">
		<td style="border:none;">
			<a href="/lcomputerstudyy/user-edit.do?u_idx=${specificUser.u_idx}" style="width:70%;font-weight:700;background-color:#818181;color:#fff;" >수정</a>
		</td>
		<td style="border:none;">
			<a href= "/lcomputerstudyy/user-delete.do?u_idx=${specificUser.u_idx}" style="width:70%; font-weight:700; background-color:red; color:#fff;">삭제</a>
		</td>
	</tr>
</table>

<a href="/lcomputerstudyy/user-list.do">뒤로가기</a>
</body>
</html>