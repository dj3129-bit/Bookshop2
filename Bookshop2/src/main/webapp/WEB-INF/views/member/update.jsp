<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			<div><h3>사용자 정보 변경</h3></div>
			
			<div>
				<form method="post">
					<div>
						<div><label>아이디:</label></div>
						<div><input type="text" name="id" value="${item.id}"></div>
					</div>
					<div>
						<div><label>비밀번호:</label></div>
						<div><input type="text" name="password" value="${item.password}"></div>
					</div>
					<div>
						<div><label>성명:</label></div>
						<div><input type="text" name="name" value="${item.name}"></div>
					</div>
					<div>
						<div><label>전화번호:</label></div>
						<div><input type="text" name="tel" value="${item.tel}"></div>
					</div>
					<div>
						<div><button>등록</button></div>
						<div><a href="../list"><button type="button">취소</button></a></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>