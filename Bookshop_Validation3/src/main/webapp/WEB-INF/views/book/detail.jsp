<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../include/head.jsp"></jsp:include>
<script>
	const bookCode = ${item.code};
</script>
<script src="${pageContext.request.contextPath}/js/book/detail.js"></script>
</head>
<body>
	<div class="container">
		<div>
			<h3>도서 상세 정보</h3>
		</div>
		
		<div>
			<div><label>도서번호:</label></div>
			<div>${item.code}</div>
		</div>
		
		<div>
			<div><label>도서명:</label></div>
			<div>${item.title}</div>
		</div>
		
		<div>
			<div><label>출판사:</label></div>
			<div>${item.publisher}</div>
		</div>
		
		<div>
			<div><label>가격:</label></div>
			<div>${item.price}</div>
		</div>
		
		<div>
			<div><label>출판일:</label></div>
			<div>${item.pubDate}</div>
		</div>
		
		<div>
			<ul>
				<c:if test="${item.attachs.size() < 1}">
					<li>첨부된 이미지가 없습니다.</li>
				</c:if>
				
				<c:forEach var="attach" items="${item.attachs}">
					<li><img src="${pageContext.request.contextPath}/upload/${attach.uuid}_${attach.filename}"></li>
				</c:forEach>
			</ul>
		</div>
		
		<div>
			<div>
				<ul>
					<c:if test="${item.reviews.size() < 1}">
						<li>등록된 서평이 없습니다.</li>
					</c:if>
					
					<c:forEach var="review" items="${item.reviews}">
						<li>${review.contents}</li>
					</c:forEach>
				</ul>
			</div>
			<div>
				<div><label>서평:</label></div>
				<div><textarea rows="5" cols="100" id="review"></textarea></div>
				<div><button id="add_review">등록</button></div>
			</div>
		</div>
		
		<div>
			<div><a href="../list">이전</a></div>
		</div>
	</div>
</body>
</html>