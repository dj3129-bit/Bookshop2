<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>도서 등록</title>
<jsp:include page="../include/head.jsp"></jsp:include>

<script src="${pageContext.request.contextPath}/js/book/add.js"></script>
</head>
<body>
	<div>
		<div class="container">
			<div class="mt-4 mb-4"><h3>도서 등록</h3></div>
			
			<div class="row">
				<div class="col">
					<form method="post" enctype="multipart/form-data">
						<div class="row mb-2">
							<div class="col-3"><label>도서명:</label></div>
							<div class="col"><input type="text" name="title" class="form-control form-control-sm"></div>
						</div>
						<div class="row mb-2">
							<div class="col-3"><label>출판사:</label></div>
							<div class="col"><input type="text" name="publisher" class="form-control form-control-sm"></div>
						</div>
						<div class="row mb-2">
							<div class="col-3"><label>가격:</label></div>
							<div class="col"><input type="text" name="price"></div>
						</div>
						<div class="row mb-4">
							<div class="col-3"><label>출판일:</label></div>
							<div class="col"><input type="date" name="pubDate"></div>
						</div>
						<div class="row mb-4">
							<div class="col-4"><label>이미지:</label><div class="btn btn-primary btn-sm" id="image_add">추가</div></div>
							<div class="col"><input type="file" name="uploadFile" class="form-control form-control-sm"></div>
							<ul id="files"></ul>
						</div>
						<div class="row mb-2">
							<div class="col d-grid"><button class="btn btn-primary btn-sm">등록</button></div>
							<div class="col d-grid"><a href="list" class="btn btn-secondary btn-sm">취소</a></div>
							<div class="col-10"></div>
						</div>
					</form>
				</div>
				<div class="col-9"></div>
			</div>
		</div>
	</div>
</body>
</html>