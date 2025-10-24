<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 등록</title>
<style>
.error{
	color:red;
}
</style>
</head>
<body>
	<div>
		<div>
			<div><h3>사용자 등록</h3></div>
			
			<div>
				<form method="post">
					<div>
						<div><label>아이디:</label></div>
						<div><form:input path="member.id" type="text" name="id"></form:input></div>
						<div class="error"><form:errors path="member.id"></form:errors></div>
					</div>
					
					<div>
						<div><label>비밀번호:</label></div>
						<div><form:input path="member.password" type="text" name="password"></form:input></div>
						<div class="error"><form:errors path="member.password"></form:errors></div>
					</div>
					
					<div>
						<div><label>성명:</label></div>
						<div><form:input path="member.name" type="text" name="name"></form:input></div>
						<div class="error"><form:errors path="member.name"></form:errors></div>
					</div>
					
					<div>
						<div><label>전화번호:</label></div>
						<div><form:input path="member.tel" type="text" name="tel"></form:input></div>
						<div class="error"><form:errors path="member.tel"></form:errors></div>
					</div>
					
					<div>
						<div><button>등록</button></div>
						<div><a href="list"><button type="button">취소</button></a></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>