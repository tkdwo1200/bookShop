<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<div class="panel panel-default"> <!-- 회색 패널 시작 -->
   <div class="panel-heading">
      <div class="panel panel-default">
         <div class="panel-body" style="padding-left: 20px; padding-right: 20px;"><!-- 제목 패널 시작 -->
            <div class="row">
               <div class="col-md-12 text-right">
                  <ol class="breadcrumb" style="background-color: white; margin-bottom: 0px;">
                    <li><a href="#">관리자메뉴</a></li>
                    <li class="active">상품관리</li>
                  </ol>
               </div>
            </div>
            <div class="row">
               <div class="col-md-12">
                  <h2 style="margin-top: 5px;">GoodsList For Management</h2>
               </div>
            </div>
         </div><!-- 제목 패널 끝 -->
      </div>
      <div class="panel panel-default"><!-- 내용 패널 시작 -->
         <div class="panel-body" style="padding-left: 20px; padding-right: 20px;">
            <div class="panel panel-info">
               <div class="panel-heading">
                  <h3 class="panel-title">상품 정보</h3>
               </div>
               <div class="panel-body">
                  <div class="col-md-12 text-center">
                     <table class="table table-striped">
                        <colgroup>
                           <col width="9%">
                           <col width="*">
                           <col width="10%">
                           <col width="20%">
                           <col width="20%">
                        </colgroup>
                        <thead>
                           <tr>
                              <th>번호</th>
                              <th>상품명</th>
                              <th>등록자</th>
                              <th>등록일</th>
                              <th>판매유무</th>
                           </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${goodsList }" var="goods" varStatus="status">
                           <tr>
                              <td>${status.count }</td>
                              <td>${goods.goodsName }</td>
                              <td>${goods.goodsWriter }</td>
                              <td>${goods.goodsCreateDate }</td>
                              <td>
                           		<div class="row">
                           			<div class="col-md-12">
		                           		<input type="radio" name="statusRadio${status.count}" class="statusRadio" value="1" data-goodsId="${goods.goodsId }"<c:if test="${goods.goodsStatus eq 1 }">checked</c:if>>준비중&nbsp;
 	   		                       		<input type="radio" name="statusRadio${status.count}" class="statusRadio" value="2" data-goodsId="${goods.goodsId }"<c:if test="${goods.goodsStatus eq 2 }">checked</c:if>>판매중
                           			</div>
                           		</div>
                              </td>
                           </tr>
                        </c:forEach>
                        </tbody>
                     </table>
                  </div>
               </div>
            </div>
         </div>
      </div><!-- 내용 패널 끝 -->      
   </div>
</div><!-- 회색 패널 끝 -->
<script src="resources/js/manageItem.js?ver=7"></script>
</body>
</html>