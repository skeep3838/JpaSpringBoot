<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員列表</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<h2>商品列表</h2>
		<h3>客戶ID: ${cid}</h3>
	
		<div class="bs-docs-example div-height" align='center'>
			<form method="post" action="${pageContext.request.contextPath}/items">
				<table id='table1' class="table table-hover">
					<thead>
						<tr>
							<th>商品編碼</th>
							<th>商品名稱</th>
							<th>商品描述</th>
							<th>商品價格</th>
							<th>購買數量</th>
							<th></th>
						</tr>
					</thead>
					<tbody id='main'>
						<c:forEach varStatus="i" var="bean" items="${itemList}">
							<tr>
								<td>${bean.iid}
								<td>${bean.iname}
								<td>${bean.description}
								<td>${bean.price}
								<td><input type="number" class="form-control" id="qty${bean.iid}" name="qty${bean.iid}" value=0>			
						</c:forEach>
					</tbody>
				</table>
				<input type="hidden" id="cid" name="cid" value="${cid}">
	<!-- 			<input type="hidden" id="shopCa" name="shopCa" value=0> -->
				<input type="submit"  value="確定購買">	
			</form>
		</div>
	</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>