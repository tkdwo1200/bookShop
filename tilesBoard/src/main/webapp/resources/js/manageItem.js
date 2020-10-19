/* 페이지 로딩 후 실행 */
$(document).ready(function(){
	
	//클릭 이벤트
	$(document).on('change', '.statusRadio', function() {
		var goodsStatus = $(this).val();
		var goodsId = $(this).attr('data-goodsId');
		
		
		//Ajax 시작
		$.ajax({
		      url: 'updateGoodsStatus.ad', //요청경로
		      type: 'post',
		      data: {'goodsStatus':goodsStatus, 'goodsId':goodsId}, //요청경로로 던질 파라메터. '파레메터명':파라메터
		      success: function(result) { // ajax 통신 성공 시 실행부분. result가 결과 데이터를 가진다.
		    	  if(result != 1){
		    		  alert('실패');
		    	  }
		    	  
		      },
		      error: function(){ //ajax통신 실패 시 실행부분
		    	  alert('오류');
		    	  
		      }
		});
		
	});
});

/* 함수선언 영역*/
(function($){
	//aaa라는 함수선언
	//aaa = function(){
	
	//};
})(jQuery);