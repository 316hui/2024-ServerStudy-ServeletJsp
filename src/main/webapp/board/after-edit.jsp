<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정 완료</title>
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
    </style>
    <script>
    	function redirectAfterDelay(){
    		setTimeout(function() {
    			window.history.back();
    		}, 5000);
    	}
    </script>
</head>
<body>
	<div class="message">
		게시물이 성공적으로 수정되었습니다.
	</div>
</body>
</html>