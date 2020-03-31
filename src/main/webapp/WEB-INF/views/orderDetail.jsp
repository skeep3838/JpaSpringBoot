<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員訂單明細</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<h2>${states}</h2>
		<h3>訂單編號: ${orderNo}</h3>
		<div class="bs-docs-example div-height" align='center'>
			<table id='table1' class="table table-hover">
				<thead>
					<tr>
						<th>商品名稱</th>
						<th>商品描述</th>
						<th>價格</th>
						<th>數量</th>
						<th></th>
					</tr>
				</thead>
				<tbody id='main'>
					<c:forEach varStatus="i" var="bean" items="${orderDetail}">
						<tr>
							<td>${bean.item.iname}
							<td>${bean.item.description}
							<td>${bean.item.price}
							<td>${bean.qty}
							<td><input style='float: right;' class='btn btn-danger'
										type='button'onclick="javascript:location.href='${pageContext.request.contextPath}/itemline/delete?seq=${bean.seq}&oid=${orderNo}'"
										value='取消購買' />
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>