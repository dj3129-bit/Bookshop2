<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title></title>

</head>
<body>
	<div>
		<div>
			<h3>회원가입</h3>		
		</div>
		
		<div>
			<form method="post" name="signup_form">
				<div>
					<div><label>아이디:</label></div>
					<div><input type="text" name="id"> 
					<div>
						<button type="button" id="check_id">[동기] 중복확인</button>
						<button type="button" id="check_id_async">[비동기] 중복확인</button>
						<button type="button" id="check_id_fetch">[fetch] 중복확인</button>
					</div>
				</div>
				
				<div class="pwd">
					<div><label>비밀번호:</label></div>
					<div><input type="password" name="password"></div>
					<div><button type="button" class="view">확인</button></div>
				</div>
				
				<div class="pwd">
					<div><label>비밀번호 확인:</label></div>
					<div><input type="password" name="check_password"></div>
					<div><button type="button" class="view">확인</button></div>
				</div>
				
				<div>
					<div><label>성명:</label></div>
					<div><input type="text" name="name"></div>
				</div>
				
				<div>
					<div><label>전화번호:</label></div>
					<div><input type="text" name="tel"></div>
				</div>
				
				<div>
					<div><button type="button" id="signup">회원가입</button></div>
					<div><a href=".."><button type="button">취소</button></a></div>
				</div>
			</form>		
		</div>
	</div>
	
	<script src="${pageContext.request.contextPath}/js/signup.js"></script>
</body>
</html>