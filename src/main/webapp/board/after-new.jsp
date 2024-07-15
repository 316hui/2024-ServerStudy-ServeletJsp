<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>게시물 생성 완료</title>
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
		게시물이 성공적으로 생성되었습니다.
	</div>
	<div class="back-button">
        <a href="board-list.do">
            <button>돌아가기</button>
        </a>
    </div>
</body>
</html>