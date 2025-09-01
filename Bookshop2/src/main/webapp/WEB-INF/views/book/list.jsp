<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 목록</title>
</head>
<body>
	<div>
		<div><h3>도서 목록</h3></div>
		
		<div>
			<form>
				<div>
					<select name="search">
						<option value="1">도서번호</option>
						<option value="2">도서명</option>
						<option value="3">출판사</option>
					</select>
				</div>
				
				<div>
					<input type="text" name="keyword">
				</div>
				
				<div>
					<button>검색</button>
				</div>
			</form>
		</div>
		
		<div>
			<table border="1">
				<thead>
					<tr>
						<th>도서번호</th>
						<th>도서명</th>
						<th>출판사</th>
						<th>가격</th>
						<th>출판일</th>
						<th>관리</th>
					</tr>
				</thead>
				
				<tbody>
					<c:if test="${list.size() < 1}">
						<tr>
							<td colspan="5">검색된 도서가 없습니다</td>
						</tr>
					</c:if>
					
					<c:forEach var="item" items="${list}">
						<tr>
							<td>${item.code}</td>
							<td>${item.title}</td>
							<td>${item.publisher}</td>
							<td>${item.price}</td>
							<td>${item.pubDate}</td>
							<td><a href="update/${item.code}">변경</a> <a href="delete/${item.code}">삭제</a></td>
						</tr>
					</c:forEach>
				</tbody>
				
				<tfoot>
					<tr>
						<td colspan="6">
							<div>
								<div><a href="?page=1${pager.query}">처음</a></div>
								<div><a href="?page=${pager.prev}${pager.query}">이전</a></div>
								
								<c:forEach var="page" items="${pager.list}">
									<div><a href="?page=${page}${pager.query}">${page}</a></div>
								</c:forEach>
								
								<div><a href="?page=${pager.next}${pager.query}">다음</a></div>
								<div><a href="?page=${pager.last}${pager.query}">마지막</a></div>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		
		<div>
			<div><a href="dummy">대량 등록</a></div>
			<div><a href="init">초기화</a></div>
			<div><a href="add">등록</a></div>
			<div><a href="..">이전</a></div>
		</div>
	</div>
</body>
</html>