<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員列表</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/body.css" rel="stylesheet" type="text/css">
</head>
<body>

	<div class="container">
		<h2>商品列表</h2>
		<div class="row justify-content-between">
			<h3 class="col-4">客戶ID: ${cid}</h3>
			<a href=<c:url value='/' /> class="col-2 btn btn-link">返回客戶列表</a>
		</div>
		<div class="row">
			<form method="GET" action="${pageContext.request.contextPath}/items" class="col-6">
				<div class="row">
					<select class="custom-select" name="sortItem" style="width:30%">								
						<option value=0 disabled selected hidden>選擇排序項目</option>
						<option value="iid" >商品編碼</option>	
						<option value="iname" >商品名稱</option>
						<option value="type" >商品種類</option>
						<option value="price" >商品價格</option>	
						<option value="lifedate" >保存期限</option>							
					</select>
					<input type="hidden" name="cid" value="${cid}">
					<input type="hidden" name="page" value=0>
					<div class="input-group-append">
						<input type="submit"  class="btn btn-info" value="送出"  id="button-addon2"></button>
					</div>	
				</div>
			</form>
			<div class="input-group mb-3 col-4">
				<input type="text" class="form-control" placeholder="搜尋金額大於的商品" aria-label="搜尋金額大於的商品" 
				  		aria-describedby="button-addon2" id="price">
				<div class="input-group-append">
					<button class="btn btn-info" onclick="itemCount()" id="button-addon2">送出</button>
				</div>
				<div id="showCount"></div>
			</div>
		</div>
		<div class="bs-docs-example div-height" align='center'>
				<table id='table1' class="table table-hover">
					<thead>
						<tr>
							<th>商品編碼</th>
							<th>商品名稱</th>
							<th>商品種類</th>
							<th>保存期限</th>
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
								<td>${bean.type}
								<td><fmt:formatDate value="${bean.lifedate}" pattern="YYYY-MM-dd" />
								<td>${bean.price}
								<td><select class="custom-select" name="qty${bean.iid}" id="${bean.iid}" onchange="getQty(${bean.iid})">								
										<option value=0 disabled selected hidden>購買數量</option>
										<c:forEach var="i" begin="1" end="${10}">
											<option value="${i}">${i}</option>
										</c:forEach>
									</select>			
						</c:forEach>
						
					</tbody>
				</table>
			<div class="container">
				<nav aria-label="Page navigation example">
				  <ul class="pagination">
				  	<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/items?cid=${cid}&page=${0}&sortItem=${sortItem}">首頁</a></li>	
				    <c:forEach var="i" begin="1" end="${totalPages}">
				    	 <li class="page-item">
				    	 	<a class="page-link" href='${pageContext.request.contextPath}/items?cid=${cid}&page=${i-1}&sortItem=${sortItem}'>${i}</a>
				    	 </li>
					</c:forEach>
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/items?cid=${cid}&page=${totalPages-1}&sortItem=${sortItem}">末頁</a></li>
				  </ul>
				</nav>
				<form method="post" action="${pageContext.request.contextPath}/items">
					<input type="hidden" id="cid" name="cid" value="${cid}">
					<input type="hidden" id="orderDetail" name="orderDetail" value="">
		<!-- 			<input type="hidden" id="shopCa" name="shopCa" value=0> -->
					<input type="submit" class="btn btn-primary"  value="確定購買">	
				</form>
			</div>
		</div>
	</div>
<!-- 	自己寫的JS -->
<script src="${pageContext.request.contextPath}/js/itemlist.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>