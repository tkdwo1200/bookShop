<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="hidden" value="${sessionScope.loginInfo.memberId }" id="loginId">
<input type="hidden" value="${goodsDetail.goodsId }" id="goodsId">
<div class="panel panel-default"> <!-- 회색 패널 시작 -->
   <div class="panel-heading">
      <div class="panel panel-default">
         <div class="panel-body" style="padding-left: 20px; padding-right: 20px;"><!-- 제목 패널 시작 -->
            <div class="row">
              <div class="col-md-12 text-right">
                  <ol class="breadcrumb" style="background-color: white; margin-bottom: 0px;">
                    <li><a href="#">쇼핑하기</a></li>
                    <li class="active">상세정보</li>
                  </ol>
               </div>
            </div>
            <div class="row">
               <div class="col-md-12">
                  <h2 style="margin-top: 5px;">Book Information</h2>
               </div>
            </div>
         </div><!-- 제목 패널 끝 -->

      </div>
         <div class="panel panel-default"><!-- 내용 패널 시작 -->
         <div class="panel-body" style="padding-left: 20px; padding-right: 20px;">
         <div class="row">
            <div class="col-md-6 text-center">
               <img alt="" width="200px" src="/upload/${goodsDetail.fileName }">
            </div>
            <div class="col-md-5">
               <div style="height: 30px;"></div>
               <div class="row">
                  <div class="form-group input-group-addon">
                     <label class="col-md-2 control-label">
                        <div style="height: 10px"></div>
                        상 품 명
                     </label>
                     <div class="col-md-10">
                        <p class="form-control">${goodsDetail.goodsName }</p>
                     </div>
                  </div>
               </div>
               <div class="row">
                  <div class="form-group input-group-addon">
                     <label class="col-md-2 control-label">
                        <div style="height: 10px"></div>
                        판 매 가
                     </label>
                     <div class="col-md-10">
                        <p class="form-control" id="goodsPrice">${goodsDetail.goodsPrice }</p>
                     </div>
                  </div>
               </div>
               <div class="row">
                  <div class="form-group input-group-addon">
                     <label class="col-md-2 control-label">
                        <div style="height: 10px"></div>
                        배 송 비
                     </label>
                     <div class="col-md-10">
                        <p class="form-control" id="goodsDeliveryPrice">${goodsDetail.goodsDeliveryPrice }</p>
                     </div>
                  </div>
               </div>
               <div class="row">
                  <div class="form-group input-group-addon">
                     <label class="col-md-2 control-label">
                        <div style="height: 10px"></div>
                        수 　량
                     </label>
                     <div class="col-md-10">
                        <input type="number" id="goodsCnt" class="form-control" value="1" min="1" style="text-align: center">
                     </div>
                  </div>
               </div>
               <div style="height: 15px;"></div>
               <div class="row text-center">
                  <input type="submit" class="btn btn-success" id="cartBtn" value="장바구니담기">
                  <input type="button" class="btn btn-info" id="buyBtn" value="바로구매하기">
               </div>
            </div>
         </div>
         <div style="height: 15px;"></div>
         <div class="row">
            <div class="col-md-12 text-center">
               <textarea class="form-control" readonly rows="6" style="resize: none;">${goodsDetail.goodsContent }</textarea>
            </div>
         </div>
         <div style="height: 15px;"></div>
         <div class="row">
            <div class="col-md-12 text-center">
               <c:forEach items="${goodsImageList }" var="list">
                  <img alt="" src="/upload/${list.fileName }"><br><br>
               </c:forEach>
            </div>
         </div>
         </div>
      </div><!-- 내용 패널 끝 -->      
   </div>
</div><!-- 회색 패널 끝 -->

<div class="modal fade" id="buyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog modal-sm">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
            <h4 class="modal-title" id="myModalLabel">구매정보확인</h4>
         </div>
         <div class="modal-body">
         	<div class="form-horizontal">
               <div class="form-group">
                  <label class="col-md-3 control-label">상품명</label>
                  <div class="col-md-9">
                     <p class="form-control-static">${goodsDetail.goodsName }</p>
                  </div>
               </div>
               <div class="form-group">
                  <label class="col-md-3 control-label">가 격</label>
                  <div class="col-md-9">
                     <p class="form-control-static" id="modalPrice">${goodsDetail.goodsPrice }</p>
                  </div>
               </div>
               <div class="form-group">
                  <label class="col-md-3 control-label">주문자</label>
                  <div class="col-md-9">
                     <p class="form-control-static">${sessionScope.loginInfo.memberId }</p>
                  </div>
               </div>
               <div class="form-group">
                  <label class="col-md-3 control-label">주소</label>
                  <div class="col-md-9">
                     <p class="form-control-static">${sessionScope.loginInfo.memberAddr }</p>
                  </div>
               </div>
               <div class="form-group">
                  <label class="col-md-3 control-label">연락처</label>
                  <div class="col-md-9">
                     <p class="form-control-static">${sessionScope.loginInfo.tel1 }</p>
                  </div>
               </div>
               <div class="row">
                  <div class="col-md-6">
                     <input type="button" class="btn btn-primary btn-block" value="구매하기" id="modalBuyBtn">
                  </div>
                  <div class="col-md-6">
                     <input type="button" class="btn btn-default btn-block" data-dismiss="modal" value="취소">
                  </div>
               </div>
           </div>
         </div>
      <!--    <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Join</button>
         </div> -->
      </div>
   </div>
</div>


<script src="resources/js/goodsDetail.js?ver=15"></script>
</body>
</html>