<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 삭제</title>
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 20px;
            text-align: center;
        }
        .message {
            margin-top: 50px;
            font-size: 24px;
            color: #333;
        }
        .back-button {
            margin-top: 20px;
        }
        .back-button button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 4px;
            font-size: 16px;
        }
        .back-button button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
	<div class="message">
		게시물이 성공적으로 삭제되었습니다.
	</div>
	<div class="back-button">
		<button onclick="redirectToController()">뒤로가기</button>
	</div>
	<script>
		function redirectToController() {
			window.location.href = "/board-list.do";
		}
	</script>
</body>
</html>