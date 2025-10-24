<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>도서 정보 변경</title>
<jsp:include page="../include/head.jsp"></jsp:include>

<script src="${pageContext.request.contextPath}/js/book/update.js"></script>
</head>
<body>
	<div>
		<div>
			<div><h3>도서 정보 변경</h3></div>
			
			<div>
				<form method="post" enctype="multipart/form-data">
					<div class="row mb-2">
						<div><label>도서명:</label></div>
						<div><input type="text" name="title" value="${item.title}"></div>
					</div>
					<div class="row mb-2">
						<div><label>출판사:</label></div>
						<div><input type="text" name="publisher" value="${item.publisher}"></div>
					</div>
					<div class="row mb-2">
						<div><label>가격:</label></div>
						<div><input type="number" name="price" value="${item.price}"></div>
					</div>
					<div class="row mb-2">
						<div><label>출판일자:</label></div>
						<div><input type="date" name="pubDate" value="${item.pubDate}"></div>
					</div>
					<div class="row mb-2">
						<div><label>이미지:</label><div class="btn btn-primary btn-sm" id="image_add">추가</div></div>
						<div><input type="file" name="uploadFile" class="form-control form-control-sm"></div>
						<ul id="files"></ul>
					</div>
					
					
					<div class="row mb-2">
						<ul>
							<c:if test="${item.attachs.size() < 1}">
								<li>첨부된 이미지가 없습니다.</li>
							</c:if>
							
							<c:forEach var="attach" items="${item.attachs}">
								<li>
									<img width="150" height="100" src="${pageContext.request.contextPath}/upload/${attach.uuid}_${attach.filename}">
									<button class="btn btn-danger btn-sm delete" data-code="${attach.code}">삭제</div>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="row mb-2">
						<div><button>변경</button></div>
						<div><a href="list"><button>취소</button></a></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>