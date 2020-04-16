<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工列表</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/body.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="container">
		<h2>員工列表</h2>
		<div class="row justify-content-between">
			<h3 class="col-4">${status}</h3>
			<a href=<c:url value='/' /> class="col-2 btn btn-link">返回客戶列表</a>
		</div>
		<div class="bs-docs-example div-height" align='center'>
			<table id='table1' class="table table-hover">
				<thead>
					<tr>
						<th>序號</th>
						<th>員工id</th>
						<th>名稱</th>
						<th>訂單id</th>
						<th>下單時間</th>
					</tr>
				</thead>
				<tbody id='main'>
					<c:forEach varStatus="i" var="bean" items="${employee}">
						<tr>
							<td>${bean.seq}
							<td>${bean.eid}
							<td>${bean.ename}
							<td>${bean.oid}
							<td>${bean.createdate}
										
					</c:forEach>
				</tbody>
			</table>
		</div>
		<button class="btn btn-secondary" onclick="javascript:location.href='<c:url value='left' />'">Left Join</button>
		<button class="btn btn-success" onclick="javascript:location.href='<c:url value='right' />'">Right Join</button>
		<button class="btn btn-danger" onclick="javascript:location.href='<c:url value='inner' />'">Inner Join</button>
		<button class="btn btn-warning" onclick="javascript:location.href='<c:url value='fullOuter' />'">Full Outer Join</button>
	</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>