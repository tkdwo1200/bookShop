<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
span:hover {
	cursor: pointer;
}

a:hover {
	cursor: pointer;
	
}
.titleShop {
	cursor: crosshair;
	color: activeborder;

}


</style>
</head>
<body>
	<div class="row">
		<c:choose>
			<c:when test="${not empty sessionScope.loginInfo.memberId }">
				<div class="col-md-4 col-md-offset-9 text-right">
					<c:if test="${sessionScope.loginInfo.memberType eq 1 }">
						<b style="color: gold;">곧VIP☆</b>
					</c:if>
					<c:if test="${sessionScope.loginInfo.memberType eq 2 }">
						<b style="color: gold;">VIP☆</b>
					</c:if>
					<c:if test="${sessionScope.loginInfo.memberType eq 4 }">
						<b style="color: gold;">Administrator☆</b>
					</c:if>
					${sessionScope.loginInfo.memberName }님 환영.&nbsp;&nbsp;&nbsp;<a href="selectCartList.sh">CartList</a>/<span id="logoutSpan">Logout</span>
				</div>
			</c:when>
			<c:otherwise>
				<div class="col-md-1 col-md-offset-11 text-right">
					<span data-toggle="modal" data-target="#loginModal">Login</span>&nbsp;&nbsp;<span data-toggle="modal" data-target="#joinModal">Join</span>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="row">
		<div class="col-md-12" id="titleShop">
			<h1><a href="shopList.sh">S h O p </a></h1>
		</div>
	</div>
	<div class="row">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<!-- ctrl+shift+F 자동정렬 -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Brand</a>
				</div>

             <!-- Collect the nav links, forms, and other content for toggling -->
         <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
               <li <c:if test="${goodsVO.mainMenu eq 'shopping'}">class="active"</c:if>><a href="shopList.sh?mainMenu=shopping&subMenu=allCategory">쇼핑하기 <span class="sr-only">(current)</span></a></li>
               <li <c:if test="${memberVO.mainMenu eq 'member'}">class="active"</c:if>><a href="selectMember.me?mainMenu=member&subMenu=selectMember">내정보관리</a></li>
               <c:if test="${sessionScope.loginInfo.memberType eq 4 }">
                  <li <c:if test="${memberVO.mainMenu eq 'admin'}">class="active"</c:if>><a href="manageBuy.ad?mainMenu=admin&subMenu=manageBuy">관리자메뉴</a></li>
               </c:if>
            </ul>
            <form class="navbar-form navbar-right" role="search">
               <div class="form-group">
                  <input type="text" class="form-control" placeholder="Search">
               </div>
               <button type="submit" class="btn btn-default">Submit</button>
            </form>
         </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
   </nav>
</div>

	<!-- Login Modal -->
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Login</h4>
				</div>
				<div class="modal-body">
					<form class="form" action="login.me" method="post" id="login">
						<div class="form-group">
							<label for="inputId1">I D</label>
							<input type="text" class="form-control" id="inputId1" name="memberId" placeholder="아이디">
							<label for="inputPassword1">P W</label>
							<input type="password" class="form-control" id="inputPassword1" name="memberPassword" placeholder="비밀번호">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							<input type="submit" class="btn btn-primary" value="Login">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- join Modal -->
	<div class="modal fade" id="joinModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Join</h4>
				</div>

				<form class="form" action="insertMember.me" method="post" id="joinForm">
					<div class="modal-body">
						<div class="form-group">
							<label for="memberId">I D</label>
							<input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디" autocomplete="on" required>
						</div>
						<div class="form-group">
							<label for="memberPassword">P W</label>
							<input type="password" class="form-control" id="memberPassword" name="memberPassword" placeholder="비밀번호" autocomplete="off" required>
						</div>
						<div class="form-group">
							<label for="memberPassword1">P W 확인</label>
							<input type="password" class="form-control" id="memberPassword1" name="memberPassword1" placeholder="비밀번호" autocomplete="off" required>
						</div>
						<div class="form-group">
							<label for="memberName">NAME</label> <input type="text" class="form-control" id="memberName" name="memberName" placeholder="이름">
						</div>
						<div class="form-group">
							<label for="memberGender">GENDER</label><p>
							<label class="radio-inline">
								<input type="radio" id="memberGender" name="memberGender" placeholder="성별" value="male" checked> 남
							</label>
							<label class="radio-inline">
								<input type="radio" id="memberGender" name="memberGender" placeholder="성별" value="female"> 여
							</label>
						</div>

						<div class="form-group">
							<label for="tel1">PHONE1</label>
							<input type="text" class="form-control" id="tel1" name="tel1" placeholder="연락처1(-없이 숫자만 입력하세요.)">
						</div>
						<div class="form-group">
							<label for="tel2">PHONE2</label>
							<input type="text" class="form-control" id="tel2" name="tel2" placeholder="연락처2(-없이 숫자만 입력하세요.)">
						</div>
						<div class="form-group">
							<label for="smsYn">SMS-Massage R/S</label>
							<p></p>
								<label class="radio-inline">
									<input type="radio" id="smsYn" name="smsYn" value="y" checked> 네
								</label>
								<label class="radio-inline">
									<input type="radio" id="smsYn" name="smsYn" value="n"> 아니오
								</label>
						</div>
						<div class="form-group">
							<label for="email">E-mail</label>
								<input type="email" class="form-control" id="email" name="email" placeholder="이메일">
						</div>
						<!-- div 줘야되나 확인중 -->
						<div class="form-group">
							<label for="emailYn">E-mail R/S</label><br>
							<label class="radio-inline">
								<input type="radio" id="emailYn" name="emailYn" value="y" checked> 네
							</label>
							<label class="radio-inline">
								<input type="radio" id="emailYn" name="emailYn" value="n"> 아니오
							</label>
						</div>

						<div class="form-group">
							<label for="birthdayYear" class="col-sm-2 control-label">생년월일</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="birthdayYear" name="birthdayYear" placeholder="연도">
							</div>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="birthdayMonth" name="birthdayMonth" placeholder="월">
							</div>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="birthdayDay" name="birthdayDay" placeholder="일">
							</div>
						</div>
						<div class="form-group">
							<label for="memberAddr">Address</label>
							<input type="text" class="form-control" id="memberAddr" name="memberAddr" placeholder="주소">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<input type="submit" class="btn btn-primary btn-block" value="Join">
					</div>
				</form>
			</div>
		</div>
	</div>
	
		<!-- Logout Modal -->
<!-- 	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Logout</h4>
				</div>
				<div class="modal-body">
					안전하게 로그아웃 되었습니다!
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-warning" data-dismiss="modal" value="창닫기" onclick="location.href='logout.me';">
				</div>
			</div>
		</div>
	</div>  -->
	
	
	
<script src="resources/js/menu.js?ver=129"></script>
</body>
</html>