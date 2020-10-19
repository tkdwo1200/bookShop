/* 페이지 로딩 후 실행 */
$(document).ready(function(){
	
	//구매상품 수량 변경시
	$(document).on('change', '#yearSelector', function() {
		var salesYear = $('#yearSelector').val();
		selectSalesList(salesYear);
	});
	
	//매출월 클릭시
	$(document).on('click', '.salesMonth', function() {
		var salesMonth= $(this).text();
		selectSalesListPerMonth(salesMonth);
	});
		
});

/* 함수선언 영역*/
(function($){
	selectSalesListPerMonth = function(salesMonth){
		var year = $('#yearSelector').val();
		var yearMonth = year + salesMonth;
		//Ajax 시작
		$.ajax({
		      url: 'selectSalesListPerMonth.ad', //요청경로
		      type: 'post',
		      data: {'yearMonth':yearMonth}, //요청경로로 던질 파라메터. '파레메터명':파라메터
		      success: function(result) { // ajax 통신 성공 시 실행부분. result가 결과 데이터를 가진다.
		    	$('#salesPerDayDiv').empty();
		    	$('#salesPerDayDiv1').empty();
		    	$('#salesPerDayDiv2').empty();
		  		
		  		var str = '';
		  		var str1 = '';
		  		var str2 = '';
		  		
		  		str += '<table class="table table-striped">';
		  		str += '<colgroup>';
		  		str += '<col width="40%">';
		  		str += '<col width="60%">';
		  		str += '</colgroup>';
		  		str1 += '<tr>';
		  		str1 += '<td class="title">일</td>';
		  		str1 += '<td class="title">매출액</td>';
		  		str1 += '</tr>';
		  		
		  		
		  		
		  		str2 += '<tbody>';
		  		
		  		$(result).each(function(index, element){
		  			str2 += '<tr>';
		  			str2 += '<td>' + element.buyDay + '</td>';
		  			str2 += '<td>' + element.salesPerDay + '</td>';
		  			str2 += '</tr>';
		  		});
		  		
		  		str2 += '</tbody>';
		  		str += '</table>';
		  		
		  		$('#salesPerDayDiv').append(str);
		  		$('#salesPerDayDiv1').append(str1);
		  		$('#salesPerDayDiv2').append(str2);
		  		
		      },
		      error: function(){ //ajax통신 실패 시 실행부분
		    	  alert('오류');
		      }
		});
	};
	
	selectSalesList = function(salesYear){
		//Ajax 시작
		$.ajax({
		      url: 'selectYear.ad', //요청경로
		      type: 'post',
		      data: {'salesYear':salesYear}, //요청경로로 던질 파라메터. '파레메터명':파라메터
		      success: function(result) { // ajax 통신 성공 시 실행부분. result가 결과 데이터를 가진다.
		    	  $('#salesListTbody').empty();
		    	  var a = [];
		    	  var b = [];
		    	  var str ='';
		    	  
		    	  for (var i = 0; i < result.length; i++) {
					a[i] = result[i].buyMonth;
					b[i] = result[i].salesPerMonth;
					
					str += `<tr><td class="salesMonth">${a[i]}</td><td>${b[i]}</td></tr>`;
				}
		    	  
		    	  console.log(result);
		    	  $('#salesListTbody').append(str);
		    	  
		      },
		      error: function(){ //ajax통신 실패 시 실행부분
		    	  alert('오류');
		      }
		});
	}
	
})(jQuery);