<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
th, td{
   text-align: center;
}
.table > tbody > tr > td {
   vertical-align: middle;
}
.table > thead > tr > th {
   font-weight: bold;
}
</style>
</head>
<body>
	<form id="buyForm" action="insertCart.sh" method="post">
		<input type="hidden" value="${sessionScope.loginInfo.memberId }" id="loginId">
		<input type="hidden" value="${goodsDetail.goodsId }" id="goodsId">	
		<div class="panel panel-default">
			<!-- 회색 패널 시작 -->
			<div class="panel-heading">
				<div class="panel panel-default">
					<div class="panel-body"
						style="padding-left: 20px; padding-right: 20px;">
						<!-- 제목 패널 시작 -->
						<div class="row">
							<div class="col-md-12 text-right">
								<ol class="breadcrumb" style="background-color: white; margin-bottom: 0px;">
									<li><a href="#">장바구니</a></li>
									<li class="active">구매정보</li>
								</ol>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<h2 style="margin-top: 5px;">장바구니</h2>
							</div>
						</div>
					</div>
					<!-- 제목 패널 끝 -->
				</div>
				<div class="panel panel-default">
					<!-- 내용 패널 시작 -->
					<div class="panel-body"
						style="padding-left: 20px; padding-right: 20px;">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">장바구니 정보</h3>
							</div>
							<div class="panel-body">
								<div class="col-md-12 text-center">
									<table class="table table-striped">
										<colgroup>
											<col width="3%">
											<col width="8%">
											<col width="11%">
											<col width="*">
											<col width="15%">
											<col width="13%">
											<col width="9%">
											<col width="9%">
											<col width="10%">
										</colgroup>
										<thead>
											<tr>
												<th><input type="checkbox" id="checkAll" checked></th>
												<th>No.</th>
												<th>상품이미지</th>
												<th>상품명</th>
												<th>구매자(이름)</th>
												<th>구매일</th>
												<th>가 격</th>
												<th>수 량</th>
												<th>총가격</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${cartList }" var="cartList" varStatus="status">
											<tr>
												<td><input type="checkbox" class="chk" value="${cartList.cartId }" checked></td>
												<td>${status.count }</td>
												<td><img style="width:100px; height:120px;" src="/upload/${cartList.fileName }" width="95%;"></td>
												<td>${cartList.goodsName }</td>
												<td>${cartList.memberName }&nbsp;(${cartList.memberId })</td>
												<td>${cartList.createDate}</td>
												<td>${cartList.goodsPrice }</td>
												<td><input type="number" id="goodsCnt" name="orderGoodsCnt" class="goodsCnt" value="${cartList.goodsCnt}" data-cartId="${cartList.cartId }" min="1" style="width: 70%; text-align: center;"></td>
												<td>${cartList.totalPrice }</td>
											</tr>
										</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">전체 가격</h3>
							</div>
							<div class="panel-body">
								<div class="form-horizontal text-right">
									<h4>
										총가격 : <span style="font-weight: bold; color: blue;" class="totalPriceSpan"></span>원
									</h4>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 text-center">
								<input type="button" class="btn btn-default" value="이전 화면" onclick="history.back()">
								<input type="button" class="btn btn-info" id="deleteCartBtn" value="장바구니 비우기">
								<input type="button" id="cartBuyBtn" class="btn btn-success" value="바로 구매하기">
							</div>
						</div>
					</div>
				</div>
				<!-- 내용 패널 끝 -->
			</div>
		</div>
		<!-- 회색 패널 끝 -->
	</form>
	<script src="resources/js/cartList.js?ver=65"></script>
</body>
</html>