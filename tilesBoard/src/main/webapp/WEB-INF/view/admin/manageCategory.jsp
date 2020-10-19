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
                    <li><a href="#">관리자메뉴</a></li>
                    <li class="active">카테고리 관리</li>
                  </ol>
               </div>
            </div>
            <div class="row">
               <div class="col-md-12">
                  <h2 style="margin-top: 5px;">Category For Management</h2>
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
                           <col width="10%">
                           <col width="20%">
                           <col width="*">
                           <col width="40%">
                        </colgroup>
                        <thead>
                           <tr>
                              <th>번호</th>
                              <th>카테고리 번호</th>
                              <th>카테고리 이름</th>
                              <th>카테고리 관리</th>
                           </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${categoryList }" var="category" varStatus="status">
                           <tr>
                              <td>${status.count }</td>
                              <td>${category.categoryCode }</td>
                              <td>${category.categoryName }</td>
                              <td>
                           		<div class="row">
                           			<div class="col-md-12">
		                           		<input type="button" class="delBtn" value="삭제" data-categoryCode="${category.categoryCode }">&nbsp;
		                           		<input type="button" class="updateBtn" value="편집" >
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
<span data-toggle="modal" data-target="#addModal">카테고리 추가</span>
<!-- Login Modal -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">카테고리 추가</h4>
				</div>
				<div class="modal-body">
					<form class="form" action="updateCategory.ad" method="post" id="category">
						<div class="form-group">
							<label for="categoryName">카테고리 이름</label>
							<input type="text" class="form-control" id="categoryName" name="categoryName" placeholder="카테고리 이름">
						</div>
						
							<!-- 예쁘게 수정 및 추가기능 넣어야함 -->
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							<input type="submit" class="btn btn-primary" value="추가">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

<script src="resources/js/manageCategory.js?ver=10"></script>
</body>
</html>