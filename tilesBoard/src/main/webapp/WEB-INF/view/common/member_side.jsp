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

<ul class="nav nav-pills nav-stacked">
  <li role="presentation" <c:if test="${memberVO.subMenu eq 'selectBuyList' }">class="active"</c:if>><a href="selectBuyList.me?mainMenu=member&subMenu=selectBuyList">구매목록 조회</a></li>
  <li role="presentation" <c:if test="${memberVO.subMenu eq 'selectMember' }">class="active"</c:if>><a href="selectMember.me?mainMenu=member&subMenu=selectMember">내정보조회</a></li>
  <li role="presentation" <c:if test="${memberVO.subMenu eq 'updateMember' }">class="active"</c:if>><a href="updateMember.me?mainMenu=member&subMenu=updateMember">내정보변경</a></li>
  <li role="presentation" <c:if test="${memberVO.subMenu eq 'deleteMember' }">class="active"</c:if>><a href="deleteMember.me?mainMenu=member&subMenu=deleteMember">회원탈퇴</a></li>
</ul>



</body>
</html>