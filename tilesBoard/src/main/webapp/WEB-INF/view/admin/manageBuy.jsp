<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
.searchTable{
   width: 100%;
   vertical-align: middle;
   text-align: center;
}
.searchTable tr, .searchTable td, .searchTable th{
   padding: 5px;
   vertical-align: middle;
}

#searchBtn{
   width: 80px; 
   height: 80px; 
}

/* tr, td{
   border: 1px solid black;
} */
</style>
</head>
<body>
<div class="panel panel-default"> <!-- 회색 패널 시작 -->
   <div class="panel-heading">
      <div class="panel panel-default">
         <div class="panel-body" style="padding-left: 20px; padding-right: 20px;"><!-- 제목 패널 시작 -->
            <div class="row">
               <div class="col-md-12 text-right">
                  <ol class="breadcrumb" style="background-color: white; margin-bottom: 0px;">
                    <li><a href="#">관리자메뉴</a></li>
                    <li class="active">구매관리</li>
                  </ol>
               </div>
            </div>
            <div class="row">
               <div class="col-md-12">
                  <h2 style="margin-top: 5px;">Purchase Management</h2>
               </div>
            </div>
         </div><!-- 제목 패널 끝 -->
      </div>
      <div class="panel panel-default"><!-- 내용 패널 시작 -->
         <div class="panel-body" style="padding-left: 20px; padding-right: 20px;">
            <div class="col-md-12" align="center"><!-- 조건검색 시작 -->
            <form action="manageBuy.ad" method="post">
               <table class="searchTable">
                  <colgroup>
                     <col width="10%">
                     <col width="30%">
                     <col width="30%">
                     <col width="*">
                     <col width="10%">
                 </colgroup>
				  <tr>
				     <td rowspan="2">조건검색</td>
				     <td colspan="3">
				        <div style="width: 100%;">
				           <div class="input-group">
				              <div class="input-group-btn">
				                 <select class="form-control" name="searchKeyword" style="width: 150px; border-right: none; border-top-left-radius: 4px; border-bottom-left-radius: 4px;">
				                      <option value="MEMBER_ID" <c:if test="${searchVO.searchKeyword eq 'MEMBER_ID'}">selected</c:if>>구매자ID</option>
				                      <option value="GOODS_NAME" <c:if test="${searchVO.searchKeyword eq 'GOODS_NAME'}">selected</c:if>>상품명</option>
				                      <option value="ORDER_ID" <c:if test="${searchVO.searchKeyword eq 'ORDER_ID'}">selected</c:if>>주문ID</option>
				                    </select>
				                </div>
				             <input type="text" class="form-control" name="searchValue" value="${searchVO.searchValue }">
				           </div>
				        </div>
				     </td>
				     <td rowspan="2">
				        <input type="submit" value="검색" id="searchBtn" class="btn btn-default" placeholder="검색어">
				     </td>
				  </tr>
				  <tr>
				     <td>
				        <div class="form-inline text-left">
				           <div class="form-group">
				              <label class="control-label" for="">From. </label>
				              <input type="date" name="fromDate" class="form-control" value="${fromDate}">
				             </div>
				        </div>
				     </td>
				     <td>
				        <div class="form-inline text-left">
				           <div class="form-group">
				              <label class="control-label" for="">To. </label>
				              <input type="date" name="toDate" class="form-control" value="${toDate}">
				             </div>
				        </div>
				     </td>
				     <td>
				        <input type="radio" name="isConfirm" value="N" <c:if test="${searchVO.isConfirm eq 'N' or empty searchVO.isConfirm }">checked</c:if>>전체&nbsp;&nbsp;
				        <input type="radio" name="isConfirm" value="Y" <c:if test="${searchVO.isConfirm eq 'Y'}">checked</c:if>>구매확인
                    </td>
                 </tr>
              </table>
         	 </form>
           </div><!-- 조건검색 끝 -->
            <div class="col-md-12" style="height: 30px;"></div>
               <div class="col-md-12">
                  <table class="table table-striped">
                     <thead>
                        <tr>
                           <th>번호</th>
                           <th>주문ID</th>
                           <th>구매자</th>
                           <th>구매일</th>
                           <th>상품명</th>
                           <th>구매금액</th>
                           <th>주문확인</th>
                        </tr>
                     </thead>
                     <tbody>
                     <c:forEach items="${buyList }" var="buy">
                        <tr>
                           <td>${buy.orderNum }</td>
                           <td>${buy.orderId }</td>
                           <td>${buy.memberId }</td>
                           <td>${buy.buyDate }</td>
                           <td>${buy.goodsName }</td>
                           <td>${buy.orderPrice }</td>
                           <td>
								<c:choose>
									<c:when test="${buy.isConfirm eq 'N' }">
										<input type="button" class="btn btn-info btn-block confirmOrderBtn" value="주문확인" data-orderNum="${buy.orderNum }">
									</c:when>
									<c:otherwise>
										<span style="color:red;">확인완료</span>
									</c:otherwise>
								</c:choose>
                           </td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </div><!-- 구매 목록 끝 -->
         </div>
      </div><!-- 내용 패널 끝 -->      
   </div>
</div><!-- 회색 패널 끝 -->
<script src="resources/js/manageBuy.js?ver=25"></script>
</body>
</html>