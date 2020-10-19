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
<div class="panel panel-default"> <!-- 회색 패널 시작 -->
   <div class="panel-heading">
      <div class="panel panel-default">
         <div class="panel-body" style="padding-left: 20px; padding-right: 20px;"><!-- 제목 패널 시작 -->
            <div class="row">
               <div class="col-md-12 text-right">
                  <ol class="breadcrumb" style="background-color: white; margin-bottom: 0px;">
                    <li><a href="#">관리자</a></li>
                    <li class="active">IT/인터넷</li>
                  </ol>
               </div>
            </div>
            <div class="row">
               <div class="col-md-12">
                  <h2 style="margin-top: 5px;">ShOpING LIST</h2>
               </div>
            </div>
         </div><!-- 제목 패널 끝 -->
      </div>
      <div class="panel panel-default"><!-- 내용 패널 시작 -->
         <div class="panel-body" style="padding-left: 20px; padding-right: 20px;">
			<div class="row text-center">
					<c:forEach items="${goodsList }" var="goods" varStatus="status">
						<div class="col-md-3">
							<img alt="" src="/upload/${goods.fileName }" style="width:90%; cursor: pointer;" onclick="location.href='goodsDetail.sh?goodsId=${goods.goodsId }';">
							<div>${goods.goodsName }</div>
							<div>${goods.goodsPrice }</div>
						</div>
						<c:if test="${(status.index + 1) % 4 eq 0 }">
							</div>
							<div class="row text-center">
						</c:if>
					</c:forEach>
			</div>
         </div>
      </div><!-- 내용 패널 끝 -->      
   </div>
</div><!-- 회색 패널 끝 -->

</body>
</html>