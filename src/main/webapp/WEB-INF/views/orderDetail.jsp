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
						<th></th>
					</tr>
				</thead>
				<tbody id='main'>
					<c:forEach varStatus="i" var="bean" items="${orderDetail}">
						<tr>
							<td>${bean.item.iname}
							<td>${bean.item.type}
							<td>${bean.item.price}
							<td>${bean.qty}
							<td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#Modal${bean.seq}">
								 修改數量
								</button>
							<td><input style='float: right;' class='btn btn-danger'
										type='button'onclick="javascript:location.href='${pageContext.request.contextPath}/itemline/delete?seq=${bean.seq}&oid=${orderNo}'"
										value='取消購買' />
						<!-- Modal 修改數量的彈跳視窗-->
						<form method="POST" action="${pageContext.request.contextPath}/itemline/update">
							<div class="modal fade" id="Modal${bean.seq}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
							  <div class="modal-dialog" role="document">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title" id="exampleModalLabel">修改數量</h5>
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							          <span aria-hidden="true">&times;</span>
							        </button>
							      </div>
							      <div class="modal-body">
							 		<div class="row">
								      <div class="col-md-4">編號: ${bean.seq}</div>
								      <div class="col-md-4 ">
								      	<input type="number" class="form-control" name="qty" value="${bean.qty}">
								      	<input type="hidden" name="seq" value="${bean.seq}">
								      </div>
								    </div>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-secondary" data-dismiss="modal">返回</button>
							        <button type="submit" class="btn btn-primary">確定修改</button>
							      </div>
							    </div>
							  </div>
							</div>
						</form>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<!-- Button trigger modal -->


<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>