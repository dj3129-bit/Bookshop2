<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 목록</title>
</head>
<body>
	<div>
		<div><h3>사용자 목록</h3></div>
		
		<div>
			<table border="1">
				<thead>
					<tr>
						<th>아이디</th>
						<th>비밀번호</th>
						<th>성명</th>
						<th>전화번호</th>
						<th>관리</th>
					</tr>
				</thead>
				
				<tbody>
					<c:if test="${list.size() < 1}">
						<tr>
							<td colspan="5">검색된 사용자가 없습니다</td>
						</tr>
					</c:if>
					
					<c:forEach var="item" items="${list}">
						<tr>
							<td>${item.id}</td>
							<td>${item.password}</td>
							<td>${item.name}</td>
							<td>${item.tel}</td>
							<td><a href="update/${item.id}">변경</a> <a href="delete/${item.id}">삭제</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div>
			<div><a href="add">등록</a></div>
			<div><a href="..">이전</a></div>
		</div>
	</div>
</body>
</html>