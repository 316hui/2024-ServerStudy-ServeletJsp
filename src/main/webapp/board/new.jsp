<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 등록</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        margin: 20px;
        text-align: center;
    }
    .form-container {
        width: 50%;
        margin: 0 auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    .form-container h2 {
        margin-bottom: 20px;
        color: #333;
    }
    .form-group {
        margin-bottom: 20px;
    }
    .form-group label {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
    }
    .form-group input[type="text"],
    .form-group textarea {
        width: 100%;
        padding: 8px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }
    .form-group textarea {
        resize: vertical;
        min-height: 150px;
    }
    .form-group button {
        padding: 10px 20px;
        background-color: #007bff;
        color: white;
        border: none;
        cursor: pointer;
        border-radius: 4px;
        font-size: 16px;
    }
    .form-group button:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
	<div class="form-container">
		<h2>게시물 등록</h2>
		<form action="/board-new.do" method="POST">
			<div class="form-group">
				<div class="form-group">
                <label for="title">제목</label>
                <input type="text" id="b_title" name="title" required>
            </div>
            <div class="form-group">
                <label for="content">내용</label>
                <textarea id="b_content" name="content" required></textarea>
            </div>
            <div class="form-group">
            	<button type="submit">등록 완료</button>
            </div>
		</form>
	</div>
</body>
</html>