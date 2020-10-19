<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="hidden" name="loginId" id="loginId" value="${sessionScope.loginInfo.memberId }">
<input type="hidden" name="loginPassword" id="loginPassword" value="${sessionScope.loginInfo.memberPassword }">
<div style="height: 50px"></div>
<div>
   <div class="form-group inputDiv">
      <div class="col-sm-5 col-md-offset-3">
         <input type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="비밀번호를 입력하세요.">
      </div>
   </div>
   <div class="btnDiv">
      <input type="button" id="deleteMemberBtn" class="btn btn-default" value="탈퇴하기">
   </div>
</div>
<script src="resources/js/deleteMember.js?ver=21"></script>
</body>
</html>