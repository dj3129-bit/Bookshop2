<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 목록</title>
<jsp:include page="../include/head.jsp"></jsp:include>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

</head>
<body>
	<div class="container">
		<div><h3>도서 목록</h3></div>
		
		<div class="mb-2">
			<form class="row">
				<div class="col-5"></div>
				<div class="col-2">
					<select name="search" class="form-select form-select-sm">
						<option value="1">도서번호</option>
						<option value="2">도서명</option>
						<option value="3">출판사</option>
					</select>
				</div>
				
				<div class="col"> 
					<input type="text" name="keyword" class="form-control form-control-sm">
				</div>
				
				<div class="col-1 d-grid">
					<button class="btn btn-primary btn-sm">검색</button>
				</div>
			</form>
		</div>
		
		<div>
			<table border="1" class="table table-sm">
				<thead class="table-dark">
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
							<td><a href="detail/${item.code}">${item.title}</a></td>
							<td>${item.publisher}</td>
							<td>${item.price}</td>
							<td>${item.pubDate}</td>
							<td><a href="update/${item.code}" class="btn btn-warning btn-sm"><i class="bi bi-pen"></i>변경</a> 
								<a href="delete/${item.code}" class="btn btn-danger btn-sm"><i class="bi bi-trash3"></i>삭제</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				
				<tfoot>
					<tr>
						<td colspan="6">
							<div class="pagination pagination-sm justify-content-center">
								<div class="page-item"><a href="?page=1${pager.query}" class="page-link">처음</a></div>
								<div class="page-item"><a href="?page=${pager.prev}${pager.query}" class="page-link">이전</a></div>
								
								<c:forEach var="page" items="${pager.list}">
									<div class="page-item ${pager.page == page ? 'active' : ''}"><a href="?page=${page}${pager.query}" class="page-link">${page}</a></div>
								</c:forEach>
								
								<div class="page-item"><a href="?page=${pager.next}${pager.query}" class="page-link">다음</a></div>
								<div class="page-item"><a href="?page=${pager.last}${pager.query}" class="page-link">마지막</a></div>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		
		<div class="row">
<!-- 		<div class="d-flex gap-2"> -->
				<div class="col d-grid"><a href="dummy" class="btn btn-info btn-sm">대량 등록</a></div>
				<div class="col d-grid"><a href="init" class="btn btn-danger btn-sm">초기화</a></div>
				<div class="col d-grid"><a href="add" class="btn btn-primary btn-sm">등록</a></div>
				<div class="col d-grid"><a href=".." class="btn btn-secondary btn-sm">이전</a></div>
			<div class="col-6"></div>
		</div>
	</div>
</body>
</html>