<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	.title{
	   font-weight: bold;
	   color: white;
	   background-color: gray;
	}
	
	.salesMonth:hover{
	   cursor: pointer;
	   text-decoration: underline;
	}
	#salesPerDayDiv1{
		margin-bottom:0px;
	}
	#salesPerDayDiv2 {	
		overflow-y: auto;
		overflow-x: hidden;
		height:420px;
		
	}
	
</style>

</head>
<body>
<div class="col-md-12 text-center">
	<select id="yearSelector">
		<option value="2019">2019</option>
		<option value="2020" selected>2020</option>
		<option value="2021">2021</option>
	</select>
</div>
<div>
	<div class="col-md-6 text-center">
		<table class="table table-striped">
		<colgroup>
			<col width="40%">
			<col width="60%">
		</colgroup>
			<tr>
				<td class="title">월</td>
				<td class="title">매출액</td>
			</tr>
			<tbody id="salesListTbody">
			<c:forEach items="${resultList }" var="result">
				<tr>
					<td class="salesMonth">${result.buyMonth }</td>
					<td>${result.salesPerMonth }</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="col-md-6 text-center" id="salesPerDayDiv">
		<div id="salesPerDayDiv1">
			
		</div>
		<div id="salesPerDayDiv2">
			
		</div>
	</div>
</div>
<script src="resources/js/salesManage.js?ver=77"></script>
</body>
</html>